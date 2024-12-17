package com.example.demo.controller;

import com.example.demo.model.Paciente;
import com.example.demo.service.PacienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes") // Base para todos los endpoints de este controlador
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Obtener todos los pacientes
    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.obtenerPacientes(); // Devuelve todos los pacientes
    }

    // Obtener un paciente por ID
    @GetMapping("/{id}")
    public Optional<Paciente> obtenerPacientePorId(@PathVariable Long id) {
        return pacienteService.obtenerPacientePorId(id); // Devuelve un paciente específico por ID
    }




    @GetMapping("/{id}/condicion")
    public ResponseEntity<Object> obtenerCondicionMedica(@PathVariable Long id) {
        // Obtener el paciente usando el servicio
        Optional<Paciente> pacienteOpt = pacienteService.obtenerPacientePorId(id);
        
        if (pacienteOpt.isPresent()) {
            // Obtener la condición médica del paciente utilizando el método getCondicionMedica()
            String condicionMedica = pacienteOpt.get().getCondicionMedica();
            
            // Verificar si la condición médica no es nula
            if (condicionMedica != null) {
                // Devolver la condición médica como un objeto JSON
                return ResponseEntity.ok("{\"condicionMedica\":\"" + condicionMedica + "\"}");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

/* 

   // Obtener condición médica de un paciente
   @GetMapping("/{id}/condicion")
   public ResponseEntity<Object> obtenerCondicionMedica(@PathVariable Long id) {
       // Obtener la condición médica del paciente usando el servicio
       String condicionMedica = pacienteService.obtenerCondicionMedica(id);
       
       if (condicionMedica != null) {
           // Devolver la condición médica como un mapa en formato JSON
           return ResponseEntity.ok().body("{\"condicionMedica\":\"" + condicionMedica + "\"}");
       } else {
           // Si no se encuentra la condición médica, retornar 404 Not Found
           return ResponseEntity.notFound().build();
       }
   }


    @GetMapping("/{id}/condicion")
    public ResponseEntity<String> obtenerCondicionMedicaPorId(@PathVariable Long id) {
        Optional<Paciente> pacienteOpt = pacienteService.obtenerPacientePorId(id);
        if (pacienteOpt.isPresent()) {
            String condicionMedica = pacienteOpt.get().getCondicionMedica();
            return ResponseEntity.ok(condicionMedica); // Devolvemos la condición médica como respuesta
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
        }
    }

*/

    // Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<?> crearPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    /*
    // Método para actualizar un paciente por ID
    @PutMapping("/{id}")
    public Paciente actualizarPaciente(@PathVariable Long id, @RequestBody Paciente pacienteActualizado) {
        return pacienteService.actualizarPaciente(id, pacienteActualizado);
    }

    */

    // Eliminar un paciente
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
    }
    /*
    @PostMapping("/{id}/parametros")
    public ResponseEntity<String> actualizarParametros(
        @PathVariable Long id,
        @RequestBody Map<String, Object> parametros // Recibimos los parámetros directamente
    ) {
        Optional<Paciente> pacienteOpt = pacienteService.obtenerPacientePorId(id);

        if (pacienteOpt.isPresent()) {
            Paciente paciente = pacienteOpt.get();
            String condicionMedica = paciente.getCondicionMedica();

            // Actualizar los parámetros según la condición médica
            if ("Diabetes".equals(condicionMedica)) {
                // Si el parámetro está presente, actualizar el campo correspondiente
                if (parametros.containsKey("nivelGlucosa")) {
                    paciente.setNivelGlucosa((Double) parametros.get("nivelGlucosa"));
                }
                if (parametros.containsKey("nivelActividadFisica")) {
                    paciente.setNivelActividadFisica((Integer) parametros.get("nivelActividadFisica"));
                }
            } else if ("EPOC".equals(condicionMedica)) {
                if (parametros.containsKey("saturacionO2")) {
                    paciente.setSaturacionO2((Double) parametros.get("saturacionO2"));
                }
                if (parametros.containsKey("frecuenciaRespiratoria")) {
                    paciente.setFrecuenciaRespiratoria((Integer) parametros.get("frecuenciaRespiratoria"));
                }
            } else if ("Hipertension".equals(condicionMedica)) {
                if (parametros.containsKey("presionArterial")) {
                    paciente.setPresionArterial((String) parametros.get("presionArterial"));
                }
                if (parametros.containsKey("frecuenciaCardiaca")) {
                    paciente.setFrecuenciaCardiaca((Integer) parametros.get("frecuenciaCardiaca"));
                }
            }
            

            // Guardar el paciente actualizado
            pacienteService.guardarPaciente(paciente);

            return ResponseEntity.ok("Parámetros actualizados con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado.");
        }
    }

    */


    @PutMapping("/{id}/actualizarParametros")
    public Paciente actualizarParametros(@PathVariable Long id, @RequestBody Paciente pacienteActualizado) {
        return pacienteService.actualizarParametros(id, pacienteActualizado);
    }
}

