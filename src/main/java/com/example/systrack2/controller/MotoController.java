package com.example.systrack2.controller;

import com.example.systrack2.DTO.Request.MotoRequestDTO;
import com.example.systrack2.DTO.Response.MotoResponseDTO;
import com.example.systrack2.service.MotoService;
import com.example.systrack2.service.PatioService;
import com.example.systrack2.service.UsuarioService;
import lombok.RequiredArgsConstructor;
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

    // ================== THYMELEAF PAGES ==================

    @GetMapping
    public String listarPage(Model model) {
        List<MotoResponseDTO> motos = motoService.listar();
        model.addAttribute("motos", motos);
        return "motos/listar";
    }

    @GetMapping("/criar")
    public String criarPage(Model model) {
        model.addAttribute("moto", new MotoRequestDTO());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("patios", patioService.listar());
        return "motos/criar";
    }

    @PostMapping
    public String criar(MotoRequestDTO dto, Model model) {
        try {
            motoService.criar(dto);
            return "redirect:/motos";
        } catch (IllegalArgumentException e) {
            model.addAttribute("moto", dto);
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("patios", patioService.listar());
            model.addAttribute("erro", e.getMessage());
            return "motos/criar";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarPage(@PathVariable Long id, Model model) {
        MotoResponseDTO moto = motoService.buscarPorId(id);
        model.addAttribute("moto", moto);
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("patios", patioService.listar());
        return "motos/editar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, MotoRequestDTO dto, Model model) {
        try {
            motoService.atualizar(id, dto);
            return "redirect:/motos";
        } catch (IllegalArgumentException e) {
            model.addAttribute("moto", dto);
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("patios", patioService.listar());
            model.addAttribute("erro", e.getMessage());
            return "motos/editar";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        motoService.excluir(id);
        return "redirect:/motos";
    }
}
