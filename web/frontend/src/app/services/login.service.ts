import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root', // Hace que el servicio esté disponible en toda la aplicación
})
export class LoginService {
  private apiUrl =  `${environment.baseUrl}/usuarios/login`; // URL 

  constructor(private http: HttpClient) {}

  // Método para autenticar al usuario
  authenticate(username: string, password: string): Observable<any> {
    const credentials = { username, password };
    return this.http.post(this.apiUrl, credentials);
  }
}

