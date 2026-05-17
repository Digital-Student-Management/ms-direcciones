package com.ms_sistemaEscolar.ms_direcciones.repositories;

import com.ms_sistemaEscolar.ms_direcciones.models.entities.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
}
