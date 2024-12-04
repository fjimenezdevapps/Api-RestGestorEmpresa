package com.miempresa.apirestempresa.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "empleados")
public class Empleados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "Nacionalidad", nullable = false, length = 50)
    private String nacionalidad;

    @Column(name = "HorasTrabajadas", nullable = false)
    private int horasTrabajadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Departamento", referencedColumnName = "Id")
    private Departamentos departamento;

    @JsonProperty("departamento")
    public String getDepartamentoNombre() {
        return departamento != null ? departamento.getNombreDepart() : "";
    }
}