package com.aluracursos.VolMedic.Repository;

import com.aluracursos.VolMedic.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    UserDetails findByUsername(String username);
}
