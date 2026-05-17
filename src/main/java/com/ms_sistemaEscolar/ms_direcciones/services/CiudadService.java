package com.ms_sistemaEscolar.ms_direcciones.services;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.CiudadDTO;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Ciudad;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Region;
import com.ms_sistemaEscolar.ms_direcciones.repositories.CiudadRepository;
import com.ms_sistemaEscolar.ms_direcciones.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private RegionRepository regionRepository;

    public List<CiudadDTO> listar() {
        return ciudadRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public CiudadDTO buscarPorId(int id) {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad no encontrada."));
        return toDTO(ciudad);
    }

    public CiudadDTO guardar(CiudadDTO dto) {
        Region region = regionRepository.findById(dto.getId_region())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "La Región especificada no existe."));

        Ciudad ciudad = new Ciudad();
        ciudad.setNombre_ciudad(dto.getNombreCiudad());
        ciudad.setRegion(region);
        return toDTO(ciudadRepository.save(ciudad));
    }

    public void eliminar(int id) {
        if (!ciudadRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad no encontrada.");
        }
        ciudadRepository.deleteById(id);
    }

    private CiudadDTO toDTO(Ciudad ciudad) {
        CiudadDTO dto = new CiudadDTO();
        dto.setId_ciudad(ciudad.getId_ciudad());
        dto.setNombreCiudad(ciudad.getNombre_ciudad());
        dto.setId_region(ciudad.getRegion().getId_region());
        return dto;
    }
}
