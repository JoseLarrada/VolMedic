package com.aluracursos.VolMedic.controller;

import com.aluracursos.VolMedic.DTO.DatosAutenticacionUsuarios;
import com.aluracursos.VolMedic.Service.LoginService;
import com.aluracursos.VolMedic.Service.TokenServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AutenticationController {
    private final LoginService manager;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacionUsuarios datos){
        return manager.autenticarUsuarios(datos);
    }
}
