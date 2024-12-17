import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  standalone: false,
  
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  onSubmit() {
    console.log('Enviando credenciales:', this.username, this.password);

    this.loginService.authenticate(this.username, this.password).subscribe({
      next: (response) => {
        console.log('Respuesta del backend:', response);
        // Verifica que el backend devuelva el mensaje esperado
        if (response.message === 'Login exitoso') {
          console.log('Acceso concedido');
          const rol = response.user.rol; // rol del usuario
          localStorage.setItem('rol', rol);
          if (rol === 'Medico') {
            this.router.navigate(['/home-medico']); // home médico
            const medicoId = response.user.medicoId;
            localStorage.setItem('medicoId', medicoId);
            localStorage.removeItem('pacienteId');
          } else if (rol === 'Paciente') {
            this.router.navigate(['/home']); // home paciente
            const pacienteId = response.user.pacienteId;
            localStorage.setItem('pacienteId', pacienteId);
            
            localStorage.removeItem('medicoId');
          } 
        } else {
            this.errorMessage = 'Error inesperado en el login';
          }
        }, 
      error: (err) => {
        console.error('Error en el login:', err);
        this.errorMessage = 'Usuario o contraseña incorrectos';
      },
    });
  }
}