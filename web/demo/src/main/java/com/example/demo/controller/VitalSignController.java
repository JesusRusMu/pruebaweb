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

import com.example.demo.model.VitalSign;
import com.example.demo.service.VitalSignService;

@RestController
@RequestMapping("/api/vital-signs")
public class VitalSignController {

    @Autowired
    private VitalSignService vitalSignService;

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<VitalSign>> obtenerPorPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(vitalSignService.obtenerPorPaciente(id));
    }

    @PostMapping
    public ResponseEntity<VitalSign> guardarVitalSign(@RequestBody VitalSign vitalSign) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vitalSignService.guardarVitalSign(vitalSign));
    }
}

