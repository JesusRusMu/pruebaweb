import { Component, OnInit } from '@angular/core';
import { RegisterParametersService } from '../services/register-parameters.service'; // Ajusta la ruta si es necesario

@Component({
  selector: 'app-register-parameters',
  standalone: false,
  templateUrl: './register-parameters.component.html',
  styleUrls: ['./register-parameters.component.css'], // Asegúrate de usar styleUrls correctamente
})
export class RegisterParametersComponent implements OnInit {
  condicionMedica: string = ''; // Se obtendrá del backend
  parametros: any[] = []; // Campos dinámicos del formulario
  pacienteId: number | null = null; // ID del paciente seleccionado


  nombre: string = '';
  apellido: string = '';
  username: string = '';
  password: string = '';


  // Para los parámetros médicos de cada condición
  nivelGlucosa: number | null = null;
  nivelActividadFisica: number | null = null;
  saturacionO2: number | null = null;
  frecuenciaRespiratoria: number | null = null;
  presionArterial: string | null = null;
  frecuenciaCardiaca: number | null = null;

  constructor(private registerParametersService: RegisterParametersService) {}

  ngOnInit(): void {
    // Obtener el ID del paciente desde el localStorage
    // this.pacienteId = Number(localStorage.getItem('pacienteId'));
    this.pacienteId = Number(localStorage.getItem('pacienteId'));
    /* */
    // Verificar si el ID del paciente es válido
    if (this.pacienteId) {
      this.cargarCondicionMedica();
    } else {
      console.error('No se pudo obtener el ID del paciente del localStorage.');
    }
  }

  cargarCondicionMedica(): void {
    if (this.pacienteId) {
      this.registerParametersService.obtenerCondicionMedica(this.pacienteId).subscribe({
        next: (response: { condicionMedica: string }) => {
          this.condicionMedica = response.condicionMedica; // Accede a la propiedad condicionMedica
          this.actualizarPantalla();
        },
        error: (error) => {
          console.error('Error al obtener la condición médica:', error);
        },
      });
    } else {
      console.error('No se ha seleccionado un paciente.');
    }
  }

  

  // Actualizar los campos del formulario según la condición médica
  actualizarPantalla(): void {
    if (this.condicionMedica === 'Diabetes') {
      this.parametros = [
        { label: 'Glucosa (mg/dL)', type: 'number', name: 'glucosa', required: true },
        { label: 'Nivel de Actividad Física', type: 'text', name: 'actividad', required: true },
      ];
    } else if (this.condicionMedica === 'EPOC') {
      this.parametros = [
        { label: 'Frecuencia Respiratoria (rpm)', type: 'number', name: 'frecuenciaRespiratoria', required: true },
        { label: 'Nivel de Saturación de O2 (%)', type: 'number', name: 'saturacion', required: true },
      ];
    } else if (this.condicionMedica === 'Hipertensión'){
      this.parametros = [
        { label: 'Presión Arterial (mmHg)', type: 'text', name: 'presion', required: true },
        { label: 'Frecuencia Cardiaca (bpm)', type: 'number', name: 'frecuenciaCardiaca', required: true },
      ];
    }
    console.log('Parámetros actualizados:', this.parametros); // <-- Verifica los datos
  }
  

  onSubmit(): void {
    // Verifica si el pacienteId no es null antes de continuar
    if (this.pacienteId === null) {
      console.error('El pacienteId es obligatorio y no puede ser null.');
      return; // O maneja el caso de error de otra manera
    }
  
    // Crear un objeto con solo los parámetros médicos actualizados
    const parametrosActualizados = {
      nivelGlucosa: this.nivelGlucosa ?? null, // Asigna null si no se ha introducido
      nivelActividadFisica: this.nivelActividadFisica ?? null,
      saturacionO2: this.saturacionO2 ?? null,
      frecuenciaRespiratoria: this.frecuenciaRespiratoria ?? null,
      presionArterial: this.presionArterial ?? null,
      frecuenciaCardiaca: this.frecuenciaCardiaca ?? null
    };
  
    console.log('Parámetros actualizados:', parametrosActualizados); // Verifica los datos
  
    // Llamar al servicio para actualizar los parámetros
    this.registerParametersService.actualizarParametros(this.pacienteId, parametrosActualizados).subscribe({
      next: (response) => {
        console.log('Respuesta del backend:', response);
      },
      error: (error) => {
        console.error('Error al actualizar parámetros médicos:', error);
      }
    });
  }
  
  
  
  
  
  
  
  
}  