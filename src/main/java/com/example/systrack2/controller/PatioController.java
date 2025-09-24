package com.example.systrack2.controller;

import com.example.systrack2.DTO.Request.PatioRequestDTO;
import com.example.systrack2.DTO.Response.PatioResponseDTO;
import com.example.systrack2.service.PatioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patios")
@RequiredArgsConstructor
public class PatioController {

    private final PatioService patioService;

    @PostMapping
    public ResponseEntity<PatioResponseDTO> criar(@RequestBody PatioRequestDTO dto) {
        return ResponseEntity.ok(patioService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PatioResponseDTO>> listar() {
        return ResponseEntity.ok(patioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(patioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatioResponseDTO> atualizar(@PathVariable Long id,
                                                      @RequestBody PatioRequestDTO dto) {
        return ResponseEntity.ok(patioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        patioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
