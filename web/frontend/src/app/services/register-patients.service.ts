import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegisterPatientsService {
  private baseUrl = `${environment.baseUrl}/medicos`; // revisar si /medicos es buena opcion o /pacientes-por-medicos

  constructor(private http: HttpClient) {}

  obtenerPacientesPorMedico(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${id}/pacientes`);
  }
}
