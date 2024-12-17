package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Medicacion;

@Repository
public interface MedicacionRepository extends JpaRepository<Medicacion, Long> {
    List<Medicacion> findByPacienteId(Long pacienteId);
}
