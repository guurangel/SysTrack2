package com.example.systrack2.config;

import com.example.systrack2.domain.Usuario;
import com.example.systrack2.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioRepository usuarioRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
            return org.springframework.security.core.userdetails.User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getSenha())
                    .roles(usuario.getRole()) // atribui o papel: 'USER' ou 'ADMIN'
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Permitir o acesso à página de login e à consola do H2 sem autenticação
                        .requestMatchers("/login", "/css/**", "/js/**", "/h2-console/**").permitAll()

                        // Acesso à Home para usuários e administradores
                        .requestMatchers("/home").hasAnyRole("USER", "ADMIN")

                        // Listagem de motos e pátios para USER e ADMIN
                        .requestMatchers(HttpMethod.GET, "/motos/**", "/patios/**").hasAnyRole("USER", "ADMIN")

                        // Admins têm permissão para CRUD completo em motos, pátios e usuários
                        .requestMatchers(HttpMethod.POST, "/motos/**", "/patios/**", "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/motos/**", "/patios/**", "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/motos/**", "/patios/**", "/usuarios/**").hasRole("ADMIN")

                        // Qualquer outro endpoint requer autenticação
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")            // Página de login personalizada
                        .defaultSuccessUrl("/home")     // Redireciona para /home após login bem-sucedido
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")           // URL de logout
                        .logoutSuccessUrl("/login?logout") // Redireciona para login após logout
                        .permitAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Ignora CSRF para a consola H2
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Permite a utilização do H2 no navegador
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
