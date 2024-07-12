package com.aluracursos.VolMedic.controller;

import com.aluracursos.VolMedic.DTO.ActualizarMedico;
import com.aluracursos.VolMedic.DTO.DatoRegistroMedico;
import com.aluracursos.VolMedic.DTO.DatosListadoMedico;
import com.aluracursos.VolMedic.Entity.MedicoEntity;
import com.aluracursos.VolMedic.Repository.MedicoRepository;
import com.aluracursos.VolMedic.Service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
@RequiredArgsConstructor
public class medicoController {
    private final MedicoService medicoService;
    @PostMapping
    public ResponseEntity<String> registrarMedico(@Valid  @RequestBody DatoRegistroMedico datoRegistroMedico){
        return medicoService.registrarMedico(datoRegistroMedico);
    }
    @GetMapping
    public Page<DatosListadoMedico> obtenerDatos(@PageableDefault(size = 1,sort = "nombre") Pageable pageable){
        return medicoService.listadoMedicos(pageable);
    }
    @PutMapping
    public ResponseEntity<String> modificarMedico(ActualizarMedico actualizarMedico){
        return medicoService.modificarMedico(actualizarMedico);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMedico(@PathVariable Long id){
        return medicoService.eliminarMedico(id);
    }
}
