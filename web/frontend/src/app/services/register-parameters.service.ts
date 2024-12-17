import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegisterParametersService {
  private baseUrl = `${environment.baseUrl}/pacientes`; // Asegúrate de que la URL sea correcta

  constructor(private http: HttpClient) {}

  // Obtener la condición médica de un paciente por ID
  obtenerCondicionMedica(id: number): Observable<{ condicionMedica: string }> {
    return this.http.get<{ condicionMedica: string }>(`${this.baseUrl}/${id}/condicion`);
  }

  /*
  actualizarParametros(id: number, parametros: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/${id}/parametros`, parametros);
  }

  */
 
  enviarParametros(id: number, pacienteActualizado: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}/actualizarParametros`, pacienteActualizado);
  }

  actualizarParametros(id: number, parametros: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}/actualizarParametros`, parametros);
  }

  obtenerParametros(pacienteId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${pacienteId}/parametros`);
  }
}
