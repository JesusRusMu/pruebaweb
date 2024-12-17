
/*

import { Component, OnInit } from '@angular/core';
import { RegisterPatientsService } from '../services/register-patients.service';

@Component({
  selector: 'app-register-patients',
  standalone: false,
  templateUrl: './register-patients.component.html',
  styleUrls: ['./register-patients.component.css'], 
})
export class RegisterPatientsComponent implements OnInit {
  pacientes: any[] = []; // Lista de pacientes
  medicoId: number | null = null; // Cambiar por el ID del médico actual

  constructor(private registerPatientsService: RegisterPatientsService) {}

  ngOnInit(): void {
    this.medicoId = Number(localStorage.getItem('medicoId')); // Obtén el ID del médico de localStorage
    console.log('Medico ID:', this.medicoId);
    if (this.medicoId) {
      this.cargarPacientes();
    } else {
      console.error('No se encontró el ID del médico en localStorage');
    }
  }

  cargarPacientes(): void {
    this.registerPatientsService.obtenerPacientesPorMedico(this.medicoId!).subscribe(
      (data) => {
        this.pacientes = data;
      },
      (error) => {
        console.error('Error al cargar los pacientes:', error);
      }
    );
  }

  verDetalles(pacienteId: number): void {
    console.log('Detalles del paciente con ID:', pacienteId);
    // Aquí podrías navegar a otra página o mostrar más información
  }
  
}


 
import { Component, OnInit } from '@angular/core';
import { RegisterPatientsService } from '../services/register-patients.service'; // Servicio para obtener datos
import { RegisterParametersService } from '../services/register-parameters.service'; // Servicio para actualizar parámetros

@Component({
  selector: 'app-register-patients',
  templateUrl: './register-patients.component.html',
  styleUrls: ['./register-patients.component.css'],
  standalone : false
})
export class RegisterPatientsComponent implements OnInit {
  pacientes: any[] = []; // Lista de pacientes
  pacienteSeleccionado: any = null; // Paciente actual al que se ven los detalles
  condicionMedica: string = ''; // Condición médica del paciente
  parametros: any[] = []; // Campos dinámicos según la condición médica

  // Objeto para almacenar valores dinámicos de los parámetros
  parametrosDinamicos: { [key: string]: any } = {};

  constructor(
    private registerPatientsService: RegisterPatientsService,
    private registerParametersService: RegisterParametersService
  ) {}

  ngOnInit(): void {
    this.cargarPacientes();
  }

  cargarPacientes(): void {
    const medicoId = Number(localStorage.getItem('medicoId'));
    if (medicoId) {
      this.registerPatientsService.obtenerPacientesPorMedico(medicoId).subscribe(
        (data) => {
          this.pacientes = data;
        },
        (error) => {
          console.error('Error al cargar los pacientes:', error);
        }
      );
    } else {
      console.error('No se encontró el ID del médico en localStorage');
    }
  }

  verDetalles(pacienteId: number): void {
    this.pacienteSeleccionado = this.pacientes.find(p => p.id === pacienteId);
    if (this.pacienteSeleccionado) {
      this.cargarCondicionMedica();
    }
  }

  cargarCondicionMedica(): void {
    this.registerParametersService.obtenerCondicionMedica(this.pacienteSeleccionado.id).subscribe({
      next: (response: { condicionMedica: string }) => {
        this.condicionMedica = response.condicionMedica;
        this.actualizarPantalla();
      },
      error: (error) => {
        console.error('Error al obtener la condición médica:', error);
      },
    });
  }

  actualizarPantalla(): void {
    // Configuración de parámetros basada en la condición médica
    if (this.condicionMedica === 'Diabetes') {
      this.parametros = [
        { label: 'Glucosa (mg/dL)', type: 'number', name: 'nivelGlucosa' },
        { label: 'Nivel de Actividad Física', type: 'text', name: 'nivelActividadFisica' },
      ];
    } else if (this.condicionMedica === 'EPOC') {
      this.parametros = [
        { label: 'Frecuencia Respiratoria (rpm)', type: 'number', name: 'frecuenciaRespiratoria' },
        { label: 'Nivel de Saturación de O2 (%)', type: 'number', name: 'saturacionO2' },
      ];
    } else if (this.condicionMedica === 'Hipertension') {
      this.parametros = [
        { label: 'Presión Arterial (mmHg)', type: 'text', name: 'presionArterial' },
        { label: 'Frecuencia Cardíaca (bpm)', type: 'number', name: 'frecuenciaCardiaca' },
      ];
    }

    // Inicializa el objeto de parámetros dinámicos
    this.parametrosDinamicos = {};
    this.parametros.forEach(param => {
      this.parametrosDinamicos[param.name] = null;
    });

    console.log('Parámetros actualizados:', this.parametros);
  }

  onSubmit(): void {
    if (!this.pacienteSeleccionado) {
      console.error('No hay paciente seleccionado.');
      return;
    }

    console.log('Parámetros a enviar:', this.parametrosDinamicos);

    this.registerParametersService
      .actualizarParametros(this.pacienteSeleccionado.id, this.parametrosDinamicos)
      .subscribe({
        next: (response) => {
          console.log('Parámetros actualizados correctamente:', response);
        },
        error: (err) => {
          console.error('Error al actualizar parámetros:', err);
        },
      });
  }
}


import { Component, OnInit } from '@angular/core';
import { RegisterPatientsService } from '../services/register-patients.service';
import { RegisterParametersService } from '../services/register-parameters.service'; // Servicio para obtener parámetros

@Component({
  selector: 'app-register-patients',
  standalone: false,
  templateUrl: './register-patients.component.html',
  styleUrls: ['./register-patients.component.css'], 

})
export class RegisterPatientsComponent implements OnInit {
  pacientes: any[] = []; // Lista de pacientes
  pacienteSeleccionado: any = null; // Paciente actual al que se ven los detalles
  condicionMedica: string = ''; // Condición médica del paciente
  parametros: any[] = []; // Campos dinámicos según la condición médica
  valoresParametros: { [key: string]: any } = {}; // Valores actuales de los parámetros

  constructor(
    private registerPatientsService: RegisterPatientsService,
    private registerParametersService: RegisterParametersService
  ) {}

  ngOnInit(): void {
    this.cargarPacientes();
  }

  cargarPacientes(): void {
    const medicoId = Number(localStorage.getItem('medicoId'));
    if (medicoId) {
      this.registerPatientsService.obtenerPacientesPorMedico(medicoId).subscribe(
        (data) => {
          this.pacientes = data;
        },
        (error) => {
          console.error('Error al cargar los pacientes:', error);
        }
      );
    } else {
      console.error('No se encontró el ID del médico en localStorage');
    }
  }

  verDetalles(pacienteId: number): void {
    this.pacienteSeleccionado = this.pacientes.find((p) => p.id === pacienteId);
    if (this.pacienteSeleccionado) {
      this.cargarCondicionMedica();
    }
  }

  cargarCondicionMedica(): void {
    this.registerParametersService.obtenerCondicionMedica(this.pacienteSeleccionado.id).subscribe({
      next: (response: { condicionMedica: string }) => {
        this.condicionMedica = response.condicionMedica;
        this.cargarParametros();
      },
      error: (error) => {
        console.error('Error al obtener la condición médica:', error);
      },
    });
  }

  cargarParametros(): void {
    this.registerParametersService.obtenerParametros(this.pacienteSeleccionado.id).subscribe({
      next: (response: { [key: string]: any }) => {
        this.valoresParametros = response;
        this.actualizarPantalla();
      },
      error: (error) => {
        console.error('Error al obtener parámetros del paciente:', error);
      },
    });
  }

  actualizarPantalla(): void {
    if (this.condicionMedica === 'Diabetes') {
      this.parametros = [
        { label: 'Glucosa (mg/dL)', value: this.valoresParametros['nivelGlucosa'] },
        { label: 'Nivel de Actividad Física', value: this.valoresParametros['nivelActividadFisica'] },
      ];
    } else if (this.condicionMedica === 'EPOC') {
      this.parametros = [
        { label: 'Frecuencia Respiratoria (rpm)', value: this.valoresParametros['frecuenciaRespiratoria'] },
        { label: 'Nivel de Saturación de O2 (%)', value: this.valoresParametros['saturacionO2'] },
      ];
    } else if (this.condicionMedica === 'Hipertension') {
      this.parametros = [
        { label: 'Presión Arterial (mmHg)', value: this.valoresParametros['presionArterial'] },
        { label: 'Frecuencia Cardiaca (bpm)', value: this.valoresParametros['frecuenciaCardiaca'] },
      ];
    }

    console.log('Parámetros cargados:', this.parametros);
  }
}





import { Component, OnInit } from '@angular/core';
import { RegisterPatientsService } from '../services/register-patients.service'; // Servicio para obtener datos
import { RegisterParametersService } from '../services/register-parameters.service'; // Servicio para obtener parámetros

@Component({
  selector: 'app-register-patients',
  templateUrl: './register-patients.component.html',
  styleUrls: ['./register-patients.component.css'],
  standalone : false
})
export class RegisterPatientsComponent implements OnInit {
  pacientes: any[] = []; // Lista de pacientes
  pacienteSeleccionado: any = null; // Paciente actual al que se ven los detalles
  condicionMedica: string = ''; // Condición médica del paciente
  parametros: any[] = []; // Campos dinámicos según la condición médica

  // Objeto para almacenar los valores de los parámetros
  parametrosDinamicos: { [key: string]: any } = {};

  constructor(
    private registerPatientsService: RegisterPatientsService,
    private registerParametersService: RegisterParametersService
  ) {}

  ngOnInit(): void {
    this.cargarPacientes();
  }

  cargarPacientes(): void {
    const medicoId = Number(localStorage.getItem('medicoId'));
    if (medicoId) {
      this.registerPatientsService.obtenerPacientesPorMedico(medicoId).subscribe(
        (data) => {
          this.pacientes = data;
        },
        (error) => {
          console.error('Error al cargar los pacientes:', error);
        }
      );
    } else {
      console.error('No se encontró el ID del médico en localStorage');
    }
  }

  verDetalles(pacienteId: number): void {
    this.pacienteSeleccionado = this.pacientes.find(p => p.id === pacienteId);
    if (this.pacienteSeleccionado) {
      this.cargarCondicionMedica();
    }
  }

  cargarCondicionMedica(): void {
    this.registerParametersService.obtenerCondicionMedica(this.pacienteSeleccionado.id).subscribe({
      next: (response: { condicionMedica: string }) => {
        this.condicionMedica = response.condicionMedica;
        this.cargarParametrosPaciente();
      },
      error: (error) => {
        console.error('Error al obtener la condición médica:', error);
      },
    });
  }

  cargarParametrosPaciente(): void {
    // Aquí cargas los parámetros médicos del paciente
    this.registerParametersService.obtenerParametros(this.pacienteSeleccionado.id).subscribe({
      next: (response: any) => {
        this.parametrosDinamicos = response; // Asignamos los valores obtenidos del backend
        this.actualizarPantalla();
      },
      error: (error) => {
        console.error('Error al obtener los parámetros del paciente:', error);
      }
    });
  }

  actualizarPantalla(): void {
    // Configuración de parámetros basada en la condición médica
    if (this.condicionMedica === 'Diabetes') {
      this.parametros = [
        { label: 'Glucosa (mg/dL)', name: 'nivelGlucosa' },
        { label: 'Nivel de Actividad Física', name: 'nivelActividadFisica' },
      ];
    } else if (this.condicionMedica === 'EPOC') {
      this.parametros = [
        { label: 'Frecuencia Respiratoria (rpm)', name: 'frecuenciaRespiratoria' },
        { label: 'Nivel de Saturación de O2 (%)', name: 'saturacionO2' },
      ];
    } else if (this.condicionMedica === 'Hipertension') {
      this.parametros = [
        { label: 'Presión Arterial (mmHg)', name: 'presionArterial' },
        { label: 'Frecuencia Cardíaca (bpm)', name: 'frecuenciaCardiaca' },
      ];
    }
  }

  // Método para mostrar el valor de cada parámetro
  obtenerValorParametro(param: string): any {
    return this.parametrosDinamicos[param];
  }
}


*/

import { Component, OnInit } from '@angular/core';
import { RegisterPatientsService } from '../services/register-patients.service'; // Servicio para obtener datos
import { RegisterParametersService } from '../services/register-parameters.service'; // Servicio para obtener parámetros

@Component({
  selector: 'app-register-patients',
  templateUrl: './register-patients.component.html',
  styleUrls: ['./register-patients.component.css'],
  standalone: false
})
export class RegisterPatientsComponent implements OnInit {
  pacientes: any[] = []; // Lista de pacientes
  pacienteSeleccionado: any = null; // Paciente actual al que se ven los detalles
  condicionMedica: string = ''; // Condición médica del paciente
  parametros: any[] = []; // Campos dinámicos según la condición médica

  // Objeto para almacenar los valores de los parámetros
  parametrosDinamicos: { [key: string]: any } = {};

  constructor(
    private registerPatientsService: RegisterPatientsService,
    private registerParametersService: RegisterParametersService
  ) {}

  ngOnInit(): void {
    this.cargarPacientes();
  }

  cargarPacientes(): void {
    const medicoId = Number(localStorage.getItem('medicoId'));
    if (medicoId) {
      this.registerPatientsService.obtenerPacientesPorMedico(medicoId).subscribe(
        (data) => {
          this.pacientes = data;
        },
        (error) => {
          console.error('Error al cargar los pacientes:', error);
        }
      );
    } else {
      console.error('No se encontró el ID del médico en localStorage');
    }
  }

  verDetalles(pacienteId: number): void {
    this.pacienteSeleccionado = this.pacientes.find(p => p.id === pacienteId);
    if (this.pacienteSeleccionado) {
      this.cargarCondicionMedica();
    }
  }

  cargarCondicionMedica(): void {
    this.registerParametersService.obtenerCondicionMedica(this.pacienteSeleccionado.id).subscribe({
      next: (response: { condicionMedica: string }) => {
        this.condicionMedica = response.condicionMedica;
        this.cargarParametrosPaciente();
      },
      error: (error) => {
        console.error('Error al obtener la condición médica:', error);
      },
    });
  }

  cargarParametrosPaciente(): void {
    // Aquí cargas los parámetros médicos del paciente
    this.registerParametersService.obtenerParametros(this.pacienteSeleccionado.id).subscribe({
      next: (response: any) => {
        this.parametrosDinamicos = response; // Asignamos los valores obtenidos del backend
        this.actualizarPantalla();
      },
      error: (error) => {
        console.error('Error al obtener los parámetros del paciente:', error);
      }
    });
  }

  actualizarPantalla(): void {
    // Configuración de parámetros basada en la condición médica
    if (this.condicionMedica === 'Diabetes') {
      this.parametros = [
        { label: 'Glucosa (mg/dL)', name: 'nivelGlucosa' },
        { label: 'Nivel de Actividad Física', name: 'nivelActividadFisica' },
      ];
    } else if (this.condicionMedica === 'EPOC') {
      this.parametros = [
        { label: 'Frecuencia Respiratoria (rpm)', name: 'frecuenciaRespiratoria' },
        { label: 'Nivel de Saturación de O2 (%)', name: 'saturacionO2' },
      ];
    } else if (this.condicionMedica === 'Hipertension') {
      this.parametros = [
        { label: 'Presión Arterial (mmHg)', name: 'presionArterial' },
        { label: 'Frecuencia Cardíaca (bpm)', name: 'frecuenciaCardiaca' },
      ];
    }
  }

  // Método para mostrar el valor de cada parámetro
  obtenerValorParametro(param: string): any {
    return this.parametrosDinamicos[param];
  }
}
