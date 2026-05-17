package com.ms_sistemaEscolar.ms_direcciones.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CiudadDTO {

    private int id_ciudad;

    @NotBlank
    private String nombreCiudad;

    @NotNull
    private int id_region;
}
