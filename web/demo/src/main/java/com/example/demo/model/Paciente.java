package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DNI también usará este campo
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    private String condicionMedica; // Diabetes, Hipertension, EPOC

    // Campos de monitorización
    private Double nivelGlucosa; // Para Diabetes
    private Integer nivelActividadFisica; // Para Diabetes

    private Double saturacionO2; // Para EPOC
    private Integer frecuenciaRespiratoria; // Para EPOC

    private String presionArterial; // Para Hipertension
    private Integer frecuenciaCardiaca; // Para Hipertensio

    @ManyToOne
    @JoinColumn(name = "medico_id")
    @JsonBackReference // Evita la serialización inversa
    private Medico medico;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCondicionMedica() {
        return condicionMedica;
    }

    public void setCondicionMedica(String condicionMedica) {
        this.condicionMedica = condicionMedica;
    }

    public Double getNivelGlucosa() {
        return nivelGlucosa;
    }

    public void setNivelGlucosa(Double nivelGlucosa) {
        this.nivelGlucosa = nivelGlucosa;
    }

    public Integer getNivelActividadFisica() {
        return nivelActividadFisica;
    }

    public void setNivelActividadFisica(Integer nivelActividadFisica) {
        this.nivelActividadFisica = nivelActividadFisica;
    }

    public Double getSaturacionO2() {
        return saturacionO2;
    }

    public void setSaturacionO2(Double saturacionO2) {
        this.saturacionO2 = saturacionO2;
    }

    public Integer getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(Integer frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
