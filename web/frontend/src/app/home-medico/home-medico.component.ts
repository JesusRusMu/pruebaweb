import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-medico',
  standalone: false,
  
  templateUrl: './home-medico.component.html',
  styleUrl: './home-medico.component.css'
})
export class HomeMedicoComponent {
  constructor(private router: Router) {} // Inyecta el Router
  
    navigateTo(route: string) {
      this.router.navigate([`/${route}`]); // Navega a la ruta especificada
    }

}
