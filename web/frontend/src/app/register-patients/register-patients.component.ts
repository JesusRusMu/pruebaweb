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
