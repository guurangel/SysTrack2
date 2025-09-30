package com.example.systrack2.controller;

import com.example.systrack2.DTO.Request.MotoFiltroDTO;
import com.example.systrack2.DTO.Request.MotoRequestDTO;
import com.example.systrack2.DTO.Response.MotoResponseDTO;
import com.example.systrack2.domain.enums.Status;
import com.example.systrack2.service.MotoService;
import com.example.systrack2.service.PatioService;
import com.example.systrack2.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService motoService;
    private final UsuarioService usuarioService;
    private final PatioService patioService;

    @GetMapping("/gerenciar")
    @PreAuthorize("hasRole('ADMIN')")
    public String gerenciarPage(@ModelAttribute("filtro") MotoFiltroDTO filtro, Model model) {

        List<MotoResponseDTO> motos = motoService.filtrar(
                filtro.getModelo(),
                filtro.getAno(),
                filtro.getQuilometragemExata(),
                filtro.getQuilometragemMin(),
                filtro.getQuilometragemMax(),
                filtro.getStatus()
        );

        model.addAttribute("motos", motos);
        return "motos/gerenciar";
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String listarPage(@ModelAttribute("filtro") MotoFiltroDTO filtro, Model model) {

        List<MotoResponseDTO> motos = motoService.filtrar(
                filtro.getModelo(),
                filtro.getAno(),
                filtro.getQuilometragemExata(),
                filtro.getQuilometragemMin(),
                filtro.getQuilometragemMax(),
                filtro.getStatus()
        );

        model.addAttribute("motos", motos);

        return "motos/listar";
    }


    @GetMapping("/criar")
    @PreAuthorize("hasRole('ADMIN')")
    public String criarPage(Model model) {
        model.addAttribute("moto", new MotoRequestDTO());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("patios", patioService.listar());
        return "motos/criar";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String criar(MotoRequestDTO dto, Model model) {
        try {
            motoService.criar(dto);
            return "redirect:/motos/gerenciar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("moto", dto);
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("patios", patioService.listar());
            model.addAttribute("erro", e.getMessage());
            return "motos/criar";
        }
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editarPage(@PathVariable Long id, Model model) {
        MotoResponseDTO moto = motoService.buscarPorId(id);
        model.addAttribute("moto", moto);
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("patios", patioService.listar());
        return "motos/editar";
    }

    @PostMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String atualizar(@PathVariable Long id, MotoRequestDTO dto, Model model) {
        try {
            motoService.atualizar(id, dto);
            return "redirect:/motos/gerenciar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("moto", dto);
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("patios", patioService.listar());
            model.addAttribute("erro", e.getMessage());
            return "motos/editar";
        }
    }

    @PostMapping("/excluir/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String excluir(@PathVariable Long id) {
        motoService.excluir(id);
        return "redirect:/motos/gerenciar";
    }
}
