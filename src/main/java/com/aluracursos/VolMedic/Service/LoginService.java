package com.aluracursos.VolMedic.Service;

import com.aluracursos.VolMedic.DTO.DatosAutenticacionUsuarios;
import com.aluracursos.VolMedic.DTO.RespuestaToken;
import com.aluracursos.VolMedic.Entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final TokenServices tokenServices;
    public ResponseEntity autenticarUsuarios(DatosAutenticacionUsuarios datosAutenticacionUsuarios){
        Authentication AuthToken=new UsernamePasswordAuthenticationToken(datosAutenticacionUsuarios.usuario(),
                datosAutenticacionUsuarios.contraseña());
        var usuarioAutenticado=authenticationManager.authenticate(AuthToken);
        var jwtToken=tokenServices.generarToken((UsuarioEntity) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new RespuestaToken(jwtToken));
    }
}
