package com.ms_sistemaEscolar.ms_direcciones.services;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.PaisDTO;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Pais;
import com.ms_sistemaEscolar.ms_direcciones.repositories.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<PaisDTO> listar() {
        return paisRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public PaisDTO buscarPorId(int id) {
        Pais pais = paisRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "País no encontrado."));
        return toDTO(pais);
    }

    public PaisDTO guardar(PaisDTO dto) {
        Pais pais = new Pais();
        pais.setNombre(dto.getNombre());
        return toDTO(paisRepository.save(pais));
    }

    public void eliminar(int id) {
        if (!paisRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "País no encontrado.");
        }
        paisRepository.deleteById(id);
    }

    private PaisDTO toDTO(Pais pais) {
        PaisDTO dto = new PaisDTO();
        dto.setId_pais(pais.getId_pais());
        dto.setNombre(pais.getNombre());
        return dto;
    }
}
