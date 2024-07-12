package com.aluracursos.VolMedic.Service;

import com.aluracursos.VolMedic.DTO.ActualizarMedico;
import com.aluracursos.VolMedic.DTO.DatoRegistroMedico;
import com.aluracursos.VolMedic.DTO.DatosListadoMedico;
import com.aluracursos.VolMedic.Entity.MedicoEntity;
import com.aluracursos.VolMedic.Repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;
    public List<DatosListadoMedico> listadoMedico(){
        return medicoRepository.findAll().stream()
                .map(DatosListadoMedico::new)
                .collect(Collectors.toList());
    }
    public Page<DatosListadoMedico> listadoMedicos(Pageable pageable){
        return medicoRepository.findAllByActivoTrue(pageable).map(DatosListadoMedico::new);
    }
    public ResponseEntity<String> registrarMedico(DatoRegistroMedico datoRegistroMedico){
        medicoRepository.save(new MedicoEntity(datoRegistroMedico));
        return ResponseEntity.ok(datoRegistroMedico.toString());
    }
    @Transactional
    public ResponseEntity<String> modificarMedico(ActualizarMedico actualizarMedico){
        MedicoEntity medico=medicoRepository.getReferenceById(actualizarMedico.id());
        medico.actualizarDatos(actualizarMedico);
        return ResponseEntity.ok("Actualizado");
    }
    @Transactional
    public ResponseEntity<String> eliminarMedico(Long id){
        MedicoEntity medico=medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.ok("Desactivado");
    }
}
