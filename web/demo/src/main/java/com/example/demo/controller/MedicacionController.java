package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Medicacion;
import com.example.demo.service.MedicacionService;

@RestController
@RequestMapping("/api/medicaciones")
public class MedicacionController {
    @Autowired
    private MedicacionService medicacionService;

    @PostMapping
    public ResponseEntity<Medicacion> crearMedicacion(@RequestBody Medicacion medicacion) {
        Medicacion nuevaMedicacion = medicacionService.crearMedicacion(medicacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMedicacion);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Medicacion>> obtenerMedicacionesPorPaciente(@PathVariable Long pacienteId) {
        List<Medicacion> medicaciones = medicacionService.obtenerMedicacionesPorPaciente(pacienteId);
        return ResponseEntity.ok(medicaciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicacion> actualizarMedicacion(@PathVariable Long id, @RequestBody Medicacion medicacion) {
        Medicacion medicacionActualizada = medicacionService.actualizarMedicacion(id, medicacion);
        return ResponseEntity.ok(medicacionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMedicacion(@PathVariable Long id) {
        medicacionService.eliminarMedicacion(id);
        return ResponseEntity.noContent().build();
    }
}
