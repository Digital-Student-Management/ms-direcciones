package com.ms_sistemaEscolar.ms_direcciones.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ciudad;

    private String nombre_ciudad;

    @ManyToOne
    @JoinColumn(name = "id_region", nullable = false)
    private Region region;
}
