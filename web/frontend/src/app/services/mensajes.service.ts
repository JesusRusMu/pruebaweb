import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MensajesService {
  private apiUrl = 'http://localhost:3000/mensajes'; // URL del backend

  constructor(private http: HttpClient) {}

  enviarMensaje(mensaje: { emisor_id: number; receptor_id: number; contenido: string,  }): Observable<any> {
    return this.http.post(this.apiUrl, mensaje);
  }

  obtenerMensajes(emisor: number, receptor: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}?emisor=${emisor}&receptor=${receptor}`);
  }
}
