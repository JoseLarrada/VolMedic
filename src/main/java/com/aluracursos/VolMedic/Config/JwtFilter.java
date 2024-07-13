package com.aluracursos.VolMedic.Config;

import com.aluracursos.VolMedic.Repository.UsuarioRepository;
import com.aluracursos.VolMedic.Service.TokenServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final TokenServices tokenServices;
    private final UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener token de los header
        var authHeader=request.getHeader("Authorization");
        if (authHeader!=null){
            var token=authHeader.replace("Bearer ","");
            var subject=tokenServices.getSubject(token);
            if (subject!=null){
                //Token valido
                var usuario=usuarioRepository.findByUsername(subject);
                var authentication= new UsernamePasswordAuthenticationToken(usuario,null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request,response);

    }
}
