package com.miempresa.apirestempresa.dto;

import lombok.Data;

@Data
public class EmpleadoRequest {
    private String nombre;
    private String apellidos;
    private int departamentoId;
}