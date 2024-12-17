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

import com.example.demo.model.Alerta;
import com.example.demo.service.AlertaService;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {
    @Autowired
    private AlertaService alertaService;

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Alerta>> obtenerAlertasPorPaciente(@PathVariable Long pacienteId) {
        List<Alerta> alertas = alertaService.obtenerAlertasPorPaciente(pacienteId);
        return ResponseEntity.ok(alertas);
    }

    @PostMapping
    public ResponseEntity<Alerta> crearAlerta(@RequestBody Alerta alerta) {
        Alerta nuevaAlerta = alertaService.generarAlerta(alerta.getPaciente(), alerta.getMensaje());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAlerta);
    }
}


