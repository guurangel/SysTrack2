package com.example.systrack2.DTO.Request;

import lombok.Data;

@Data
public class MotoRequestDTO {
    private String placa;
    private String modelo;
    private Long usuarioId;
    private Long patioId;
}