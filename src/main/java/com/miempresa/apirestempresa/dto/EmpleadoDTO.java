package com.miempresa.apirestempresa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
    private String nombre;
    private String apellidos;
    private String nacionalidad;
    private int horasTrabajadas;
    private int departamentoId;
}