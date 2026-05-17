package com.ms_sistemaEscolar.ms_direcciones.controller;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.DireccionDTO;
import com.ms_sistemaEscolar.ms_direcciones.services.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("direccion")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    // GET http://localhost:8080/direccion
    @GetMapping
    public List<DireccionDTO> listar() {
        return direccionService.listar();
    }

    // GET http://localhost:8080/direccion/1
    @GetMapping("/{id}")
    public DireccionDTO buscarPorId(@PathVariable int id) {
        return direccionService.buscarPorId(id);
    }

    // POST http://localhost:8080/direccion
    @PostMapping
    public DireccionDTO guardar(@RequestBody DireccionDTO dto) {
        return direccionService.guardar(dto);
    }

    // DELETE http://localhost:8080/direccion/1
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        direccionService.eliminar(id);
    }
}
