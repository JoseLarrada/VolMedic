package com.aluracursos.VolMedic.Repository;

import com.aluracursos.VolMedic.Entity.MedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity,Long> {
    Page<MedicoEntity> findAllByActivoTrue(Pageable pageable);
}
