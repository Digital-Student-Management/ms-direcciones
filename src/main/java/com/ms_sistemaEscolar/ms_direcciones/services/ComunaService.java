package com.ms_sistemaEscolar.ms_direcciones.services;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.ComunaDTO;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Ciudad;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Comuna;
import com.ms_sistemaEscolar.ms_direcciones.repositories.CiudadRepository;
import com.ms_sistemaEscolar.ms_direcciones.repositories.ComunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<ComunaDTO> listar() {
        return comunaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public ComunaDTO buscarPorId(int id) {
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comuna no encontrada."));
        return toDTO(comuna);
    }

    public ComunaDTO guardar(ComunaDTO dto) {
        Ciudad ciudad = ciudadRepository.findById(dto.getId_ciudad())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "La Ciudad especificada no existe."));

        Comuna comuna = new Comuna();
        comuna.setNombre(dto.getNombre());
        comuna.setCiudad(ciudad);
        return toDTO(comunaRepository.save(comuna));
    }

    public void eliminar(int id) {
        if (!comunaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comuna no encontrada.");
        }
        comunaRepository.deleteById(id);
    }

    private ComunaDTO toDTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setId_comuna(comuna.getId_comuna());
        dto.setNombre(comuna.getNombre());
        dto.setId_ciudad(comuna.getCiudad().getId_ciudad());
        return dto;
    }
}
