package com.ms_sistemaEscolar.ms_direcciones.controller;

import com.ms_sistemaEscolar.ms_direcciones.models.dto.RegionDTO;
import com.ms_sistemaEscolar.ms_direcciones.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    // GET http://localhost:8080/region
    @GetMapping
    public List<RegionDTO> listar() {
        return regionService.listar();
    }

    // GET http://localhost:8080/region/1
    @GetMapping("/{id}")
    public RegionDTO buscarPorId(@PathVariable int id) {
        return regionService.buscarPorId(id);
    }

    // POST http://localhost:8080/region
    @PostMapping
    public RegionDTO guardar(@RequestBody RegionDTO dto) {
        return regionService.guardar(dto);
    }

    // DELETE http://localhost:8080/region/1
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        regionService.eliminar(id);
    }
}
