package com.aluracursos.VolMedic.DTO;

import com.aluracursos.VolMedic.Entity.MedicoEntity;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public DatosListadoMedico(MedicoEntity medico){
        this(medico.getId(),medico.getNombre(),medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());

    }
}
