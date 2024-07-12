package com.aluracursos.VolMedic.Entity;

import com.aluracursos.VolMedic.DTO.DatosDireccion;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DireccionEntity {
    private String calle;
    private String distrito;
    private String ciudad;
    private String numero;
    private String complemento;

    public DireccionEntity(DatosDireccion datosDireccion){
        this.calle=datosDireccion.calle();
        this.distrito=datosDireccion.distrito();
        this.ciudad=datosDireccion.ciudad();
        this.numero=datosDireccion.numero();
        this.complemento=datosDireccion.complemento();
    }

    public void actualizarInformacion(DatosDireccion direccion) {
        this.calle=direccion.calle();
        this.distrito=direccion.distrito();
        this.ciudad=direccion.ciudad();
        this.numero=direccion.numero();
        this.complemento=direccion.complemento();
    }
}
