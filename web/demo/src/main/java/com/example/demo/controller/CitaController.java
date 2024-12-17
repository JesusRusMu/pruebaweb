package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Cita;
import com.example.demo.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public List<Cita> listarCitas() {
        return citaService.listarCitas();
    }

    @PostMapping
    public Cita agregarCita(@RequestBody Cita cita) {
        return citaService.guardarCita(cita);
    }

    // Método para actualizar una cita por ID
    @PutMapping("/{id}")
    public Cita actualizarCita(@PathVariable Long id, @RequestBody Cita citaActualizada) {
        return citaService.actualizarCita(id, citaActualizada);
    }

    // Método para borrar una cita por ID
    @DeleteMapping("/{id}")
    public void borrarCita(@PathVariable Long id) {
        citaService.borrarCita(id);
    }

}
