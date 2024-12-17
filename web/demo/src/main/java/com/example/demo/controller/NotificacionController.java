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

import com.example.demo.model.Notificacion;
import com.example.demo.service.NotificacionService;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.obtenerNotificacionesPorPaciente(id));
    }

    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@RequestBody Notificacion notificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacionService.crearNotificacion(notificacion));
    }
}

