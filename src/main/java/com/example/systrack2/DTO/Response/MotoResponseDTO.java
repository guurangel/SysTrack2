package com.example.systrack2.DTO.Response;

import lombok.Data;

@Data
public class MotoResponseDTO {
    private Long id;
    private String placa;
    private String modelo;
    private String nomeUsuario;
    private String nomePatio;
}
