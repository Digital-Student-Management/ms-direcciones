package com.ms_sistemaEscolar.ms_direcciones.controller;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.CiudadDTO;
import com.ms_sistemaEscolar.ms_direcciones.services.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ciudad")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    // GET http://localhost:8080/ciudad
    @GetMapping
    public List<CiudadDTO> listar() {
        return ciudadService.listar();
    }

    // GET http://localhost:8080/ciudad/1
    @GetMapping("/{id}")
    public CiudadDTO buscarPorId(@PathVariable int id) {
        return ciudadService.buscarPorId(id);
    }

    // POST http://localhost:8080/ciudad
    @PostMapping
    public CiudadDTO guardar(@RequestBody CiudadDTO dto) {
        return ciudadService.guardar(dto);
    }

    // DELETE http://localhost:8080/ciudad/1
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        ciudadService.eliminar(id);
    }
}
