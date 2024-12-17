import { Component } from '@angular/core';

@Component({
  selector: 'app-appointments',
  standalone: false,
  
  templateUrl: './appointments.component.html',
  styleUrl: './appointments.component.css'
})
export class AppointmentsComponent {
  mesActual: string = '';
  anoActual: number = 0;
  semanas: any[] = []; 
  dias: string[] = ['L', 'M', 'X', 'J', 'V', 'S', 'D']; 
  mesIndex: number = 0;

  ngOnInit() {
    const today = new Date();
    this.mesActual = today.toLocaleString('default', { month: 'long' }); 
    this.anoActual = today.getFullYear(); 
    this.updateMesActual();
    this.generateCalendar(today.getMonth(), today.getFullYear());
  }

  updateMesActual() {
    const date = new Date(this.anoActual, this.mesIndex, 1);
    this.mesActual = date.toLocaleString('default', { month: 'long' });
  }

  generateCalendar(month: number, year: number) {
    const dUno = new Date(year, month, 1); //primer dia del mes
    const dUltimo = new Date(year, month + 1, 0); //ultimo dia del mes

    const daysInMonth = dUltimo.getDate();
    const startDayOfWeek =  (dUno.getDay() + 6) % 7;

    let currentDay = 1;
    const weeks = [];

    for (let i = 0; i < 6; i++) {
      const week = [];
      for (let j = 1; j <= 7; j++) {
        if (i === 0 && j < startDayOfWeek) {
          week.push(null); // Espacios en blanco antes del inicio del mes
        } else if (currentDay > daysInMonth) {
          week.push(null); // Espacios en blanco después del final del mes
        } else {
          week.push(currentDay);
          currentDay++;
        }
      }
      weeks.push(week);
      if (currentDay > daysInMonth) break; // salir si ya se generaron todos los días
    }

    this.semanas = weeks;
  }
  prevMonth() {
    if (this.mesIndex === 0) {
      this.mesIndex = 11; // Retrocede a diciembre del año anterior
      this.anoActual--;
    } else {
      this.mesIndex--;
    }
    this.updateMesActual();
    this.generateCalendar(this.mesIndex, this.anoActual);
  }

  nextMonth() {
    if (this.mesIndex === 11) {
      this.mesIndex = 0; // Avanza a enero del siguiente año
      this.anoActual++;
    } else {
      this.mesIndex++;
    }
    this.updateMesActual();
    this.generateCalendar(this.mesIndex, this.anoActual);
  }
}
