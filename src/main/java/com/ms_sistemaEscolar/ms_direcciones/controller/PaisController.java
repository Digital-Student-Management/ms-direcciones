package com.ms_sistemaEscolar.ms_direcciones.controller;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.PaisDTO;
import com.ms_sistemaEscolar.ms_direcciones.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    // GET http://localhost:8080/pais
    @GetMapping
    public List<PaisDTO> listar() {
        return paisService.listar();
    }

    // GET http://localhost:8080/pais/1
    @GetMapping("/{id}")
    public PaisDTO buscarPorId(@PathVariable int id) {
        return paisService.buscarPorId(id);
    }

    // POST http://localhost:8080/pais
    @PostMapping
    public PaisDTO guardar(@RequestBody PaisDTO dto) {
        return paisService.guardar(dto);
    }

    // DELETE http://localhost:8080/pais/1
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        paisService.eliminar(id);
    }
}
