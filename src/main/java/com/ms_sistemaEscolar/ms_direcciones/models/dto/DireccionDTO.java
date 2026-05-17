package com.ms_sistemaEscolar.ms_direcciones.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DireccionDTO {

    private int id_direccion;

    @NotBlank
    private String calle;

    @NotNull
    private int numero;

    private String departamentoVilla;

    @NotNull
    private int id_comuna;
}
