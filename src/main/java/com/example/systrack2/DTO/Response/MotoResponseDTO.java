package com.example.systrack2.DTO.Response;

import lombok.Data;

@Data
public class MotoResponseDTO {
    private Long id;
    private String placa;
    private String modelo;
    private Long usuarioId;     // ADICIONADO
    private String nomeUsuario;
    private Long patioId;       // ADICIONADO
    private String nomePatio;
}
