package com.ms_sistemaEscolar.ms_direcciones.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComunaDTO {

    private int id_comuna;

    @NotBlank
    private String nombre;

    @NotNull
    private int id_ciudad;
}
