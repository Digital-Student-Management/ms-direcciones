package com.ms_sistemaEscolar.ms_direcciones.repositories;

import com.ms_sistemaEscolar.ms_direcciones.models.entities.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
}
