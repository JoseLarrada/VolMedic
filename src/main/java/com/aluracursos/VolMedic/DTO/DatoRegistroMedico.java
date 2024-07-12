package com.aluracursos.VolMedic.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatoRegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{8,10}")
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{7,10}")
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        DatosDireccion datosDireccion
) {
}
