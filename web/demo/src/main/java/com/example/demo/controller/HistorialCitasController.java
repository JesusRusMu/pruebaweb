package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.HistorialCitas;
import com.example.demo.service.HistorialCitasService;

@RestController
@RequestMapping("/api/historial-citas")
public class HistorialCitasController {

    @Autowired
    private HistorialCitasService historialCitasService;

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<HistorialCitas>> obtenerHistorialPorPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(historialCitasService.obtenerHistorialPorPaciente(id));
    }

    @PostMapping
    public ResponseEntity<HistorialCitas> guardarHistorial(@RequestBody HistorialCitas historial) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historialCitasService.guardarHistorial(historial));
    }
}

