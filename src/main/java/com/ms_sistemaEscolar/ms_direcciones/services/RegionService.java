package com.ms_sistemaEscolar.ms_direcciones.services;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.RegionDTO;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Pais;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Region;
import com.ms_sistemaEscolar.ms_direcciones.repositories.PaisRepository;
import com.ms_sistemaEscolar.ms_direcciones.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private PaisRepository paisRepository;

    public List<RegionDTO> listar() {
        return regionRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public RegionDTO buscarPorId(int id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Región no encontrada."));
        return toDTO(region);
    }

    public RegionDTO guardar(RegionDTO dto) {
        Pais pais = paisRepository.findById(dto.getId_pais())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El País especificado no existe."));

        Region region = new Region();
        region.setNombre(dto.getNombre());
        region.setPais(pais);
        return toDTO(regionRepository.save(region));
    }

    public void eliminar(int id) {
        if (!regionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Región no encontrada.");
        }
        regionRepository.deleteById(id);
    }

    private RegionDTO toDTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId_region(region.getId_region());
        dto.setNombre(region.getNombre());
        dto.setId_pais(region.getPais().getId_pais());
        return dto;
    }
}
