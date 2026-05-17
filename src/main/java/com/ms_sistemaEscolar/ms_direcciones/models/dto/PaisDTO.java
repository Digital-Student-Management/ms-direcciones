package com.ms_sistemaEscolar.ms_direcciones.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaisDTO {

    private int id_pais;

    @NotBlank
    private String nombre;
}
