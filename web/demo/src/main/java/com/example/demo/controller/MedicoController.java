package com.example.demo.controller;

import com.example.demo.model.Medico;
import com.example.demo.service.MedicoService;
import com.example.demo.model.Paciente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // Obtener todos los médicos
    @GetMapping
    public List<Medico> obtenerTodos() {
        return medicoService.obtenerTodos();
    }

    // Obtener un médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable Long id) {
        return medicoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener pacientes asignados a un médico
    @GetMapping("/{id}/pacientes")
    public ResponseEntity<List<Paciente>> obtenerPacientes(@PathVariable Long id) {
        return medicoService.obtenerPorId(id)
                .map(medico -> ResponseEntity.ok(medico.getPacientes()))
                .orElse(ResponseEntity.notFound().build());
    }


    // Guardar o actualizar un médico
    @PostMapping
    public ResponseEntity<Medico> guardar(@RequestBody Medico medico) {
        try {
            Medico medicoGuardado = medicoService.guardar(medico);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicoGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Método para actualizar un médico por ID
    @PutMapping("/{id}")
    public ResponseEntity<Medico> actualizarMedico(@PathVariable Long id, @RequestBody Medico medico) {
        try {
            Medico medicoActualizado = medicoService.actualizarMedico(id, medico);
            return ResponseEntity.ok(medicoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }    

    // Eliminar un médico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            medicoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
