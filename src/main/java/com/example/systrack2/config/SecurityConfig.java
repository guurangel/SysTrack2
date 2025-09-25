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
                    .roles(usuario.getRole()) // apenas ADMIN ou USER
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // H2 console liberado
                        .requestMatchers("/h2-console/**").permitAll()

                        // Usuário comum pode apenas visualizar GET
                        .requestMatchers(HttpMethod.GET, "/patios/**", "/motos/**").hasAnyRole("ADMIN", "USER")

                        // Métodos POST, PUT, DELETE apenas ADMIN
                        .requestMatchers(HttpMethod.POST, "/patios/**", "/motos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/patios/**", "/motos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/patios/**", "/motos/**").hasRole("ADMIN")

                        // Usuários apenas ADMIN
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")

                        // qualquer outro endpoint precisa de login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")            // rota do login Thymeleaf
                        .defaultSuccessUrl("/motos")    // redireciona após login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
