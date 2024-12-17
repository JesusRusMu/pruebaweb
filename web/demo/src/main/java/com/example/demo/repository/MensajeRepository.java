package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByPacienteIdAndMedicoId(Long pacienteId, Long medicoId);
    List<Mensaje> findByMedicoIdAndPacienteId(Long medicoId, Long pacienteId);
}

