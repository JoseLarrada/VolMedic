package com.aluracursos.VolMedic.Entity;

import com.aluracursos.VolMedic.DTO.ActualizarMedico;
import com.aluracursos.VolMedic.DTO.DatoRegistroMedico;
import com.aluracursos.VolMedic.DTO.Especialidad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicos")
@NoArgsConstructor
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //un hashCode de id que dice que si viene un objeto con un id ya
//existente se hara referencia a ese id que ya existe
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private DireccionEntity direccion;

    public MedicoEntity(DatoRegistroMedico datoRegistroMedico){
        this.activo=true;
        this.nombre=datoRegistroMedico.nombre();
        this.email=datoRegistroMedico.email();
        this.documento=datoRegistroMedico.documento();
        this.especialidad=datoRegistroMedico.especialidad();
        this.telefono=datoRegistroMedico.telefono();
        this.direccion=new DireccionEntity(datoRegistroMedico.datosDireccion());
    }

    public void actualizarDatos(ActualizarMedico actualizarMedico) {
        if (!actualizarMedico.nombre().isEmpty()){
            this.nombre= actualizarMedico.nombre();
        }
        if (actualizarMedico.direccion()!=null){
           direccion.actualizarInformacion(actualizarMedico.direccion());
        }
        if (!actualizarMedico.documento().isEmpty()){
            this.documento=actualizarMedico.documento();
        }
    }
    public void desactivarMedico(){
        this.activo=false;
    }
}
