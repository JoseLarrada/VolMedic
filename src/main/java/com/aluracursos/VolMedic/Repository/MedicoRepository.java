package com.aluracursos.VolMedic.Repository;

import com.aluracursos.VolMedic.Entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity,Long> {
}
