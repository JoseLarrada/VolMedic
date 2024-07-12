package com.aluracursos.VolMedic.DTO;

import jakarta.validation.constraints.NotNull;

public record ActualizarMedico(
        @NotNull
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {
}
