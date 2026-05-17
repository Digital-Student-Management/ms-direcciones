package com.ms_sistemaEscolar.ms_direcciones.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_region;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;
}
