package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByPacienteId(Long pacienteId);
}

