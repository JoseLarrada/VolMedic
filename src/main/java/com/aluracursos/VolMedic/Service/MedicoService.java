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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<DatosListadoMedico> buscarPorId(Long id){
        Optional<MedicoEntity> medico=medicoRepository.findById(id);
        return ResponseEntity.ok(new DatosListadoMedico(medico.get()));
    }
    public ResponseEntity<DatosListadoMedico> registrarMedico(DatoRegistroMedico datoRegistroMedico,
                                                  UriComponentsBuilder uriComponentsBuilder){
        MedicoEntity medico=medicoRepository.save(new MedicoEntity(datoRegistroMedico));
        URI url=uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosListadoMedico(medico));
    }
    @Transactional
    public ResponseEntity<DatosListadoMedico> modificarMedico(ActualizarMedico actualizarMedico){
        MedicoEntity medico=medicoRepository.getReferenceById(actualizarMedico.id());
        medico.actualizarDatos(actualizarMedico);
        return ResponseEntity.ok(new DatosListadoMedico(medico));
    }
    @Transactional
    public ResponseEntity<String> eliminarMedico(Long id){
        MedicoEntity medico=medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.notFound().build();
    }
}
