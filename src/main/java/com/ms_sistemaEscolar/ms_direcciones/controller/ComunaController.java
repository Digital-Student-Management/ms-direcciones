package com.ms_sistemaEscolar.ms_direcciones.controller;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.ComunaDTO;
import com.ms_sistemaEscolar.ms_direcciones.services.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comuna")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    // GET http://localhost:8080/comuna
    @GetMapping
    public List<ComunaDTO> listar() {
        return comunaService.listar();
    }

    // GET http://localhost:8080/comuna/1
    @GetMapping("/{id}")
    public ComunaDTO buscarPorId(@PathVariable int id) {
        return comunaService.buscarPorId(id);
    }

    // POST http://localhost:8080/comuna
    @PostMapping
    public ComunaDTO guardar(@RequestBody ComunaDTO dto) {
        return comunaService.guardar(dto);
    }

    // DELETE http://localhost:8080/comuna/1
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        comunaService.eliminar(id);
    }
}
