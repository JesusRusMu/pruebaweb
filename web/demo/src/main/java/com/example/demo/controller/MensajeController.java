package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Mensaje;
import com.example.demo.service.MensajeService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;

    // Endpoint para enviar un mensaje
    @PostMapping
    public ResponseEntity<?> enviarMensaje(@RequestBody Mensaje mensaje) {
        try {
            Mensaje nuevoMensaje = mensajeService.enviarMensaje(mensaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMensaje);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint para obtener mensajes entre paciente y médico
    @GetMapping
    public ResponseEntity<List<Mensaje>> obtenerMensajes(
            @RequestParam Long pacienteId,
            @RequestParam Long medicoId) {
        List<Mensaje> mensajes = mensajeService.obtenerMensajes(pacienteId, medicoId);
        return ResponseEntity.ok(mensajes);
    }
}
