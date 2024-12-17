package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.VitalSign;
import com.example.demo.repository.VitalSignRepository;

@Service
public class VitalSignService {

    @Autowired
    private VitalSignRepository vitalSignRepository;

    public List<VitalSign> obtenerPorPaciente(Long pacienteId) {
        return vitalSignRepository.findByPacienteId(pacienteId);
    }

    public VitalSign guardarVitalSign(VitalSign vitalSign) {
        vitalSign.setFechaMedicion(LocalDateTime.now());
        return vitalSignRepository.save(vitalSign);
    }
}

