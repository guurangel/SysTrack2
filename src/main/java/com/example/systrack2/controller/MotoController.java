package com.example.systrack2.controller;

import com.example.systrack2.DTO.Request.MotoRequestDTO;
import com.example.systrack2.DTO.Response.MotoResponseDTO;
import com.example.systrack2.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoService motoService;

    @PostMapping
    public ResponseEntity<MotoResponseDTO> criar(@RequestBody MotoRequestDTO dto) {
        return ResponseEntity.ok(motoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MotoResponseDTO>> listar() {
        return ResponseEntity.ok(motoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoResponseDTO> atualizar(@PathVariable Long id,
                                                     @RequestBody MotoRequestDTO dto) {
        return ResponseEntity.ok(motoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        motoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
