import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: false,

  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  showHeader: boolean = true;
  rol: string | null = null;

  constructor(
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object // Inyección del identificador de plataforma
  ) {
    // Escucha los cambios de la ruta activa
    this.router.events.subscribe(() => {
      this.showHeader = this.router.url !== '/login';
    });
  }

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) { // Verifica si el entorno es un navegador
      this.rol = localStorage.getItem('rol');
      console.log('Rol del usuario:', this.rol);

      if (!this.rol) {
        console.error('Rol no definido. Redirigiendo al login...');
        this.router.navigate(['/login']);
      }
    } else {
      console.warn('Este código se está ejecutando en un entorno sin acceso a localStorage.');
    }
  }

  logout(): void {
    try {
      localStorage.clear();
    } catch (error) {
      console.error('Error al intentar limpiar localStorage:', error);
    }
    this.router.navigate(['/login']);
  }
}
