package com.ms_sistemaEscolar.ms_direcciones.services;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.DireccionDTO;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Comuna;
import com.ms_sistemaEscolar.ms_direcciones.models.entities.Direccion;
import com.ms_sistemaEscolar.ms_direcciones.repositories.ComunaRepository;
import com.ms_sistemaEscolar.ms_direcciones.repositories.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    public List<DireccionDTO> listar() {
        return direccionRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public DireccionDTO buscarPorId(int id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dirección no encontrada."));
        return toDTO(direccion);
    }

    public DireccionDTO guardar(DireccionDTO dto) {
        Comuna comuna = comunaRepository.findById(dto.getId_comuna())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "La Comuna especificada no existe."));

        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setDepartamento_villa(dto.getDepartamentoVilla());
        direccion.setComuna(comuna);
        return toDTO(direccionRepository.save(direccion));
    }

    public void eliminar(int id) {
        if (!direccionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dirección no encontrada.");
        }
        direccionRepository.deleteById(id);
    }

    private DireccionDTO toDTO(Direccion direccion) {
        DireccionDTO dto = new DireccionDTO();
        dto.setId_direccion(direccion.getId_direccion());
        dto.setCalle(direccion.getCalle());
        dto.setNumero(direccion.getNumero());
        dto.setDepartamentoVilla(direccion.getDepartamento_villa());
        dto.setId_comuna(direccion.getComuna().getId_comuna());
        return dto;
    }
}
