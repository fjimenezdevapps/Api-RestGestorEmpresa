package com.miempresa.apirestempresa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "departamentos")
public class Departamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "NombreDepart", nullable = false, length = 100)
    private String nombreDepart;
    
}
