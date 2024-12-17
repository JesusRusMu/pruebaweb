import { Component, OnInit } from '@angular/core';
import { MensajesService } from '../services/mensajes.service';

@Component({
  selector: 'app-messaging',
  standalone: false,
  
  templateUrl: './messaging.component.html',
  styleUrls: ['./messaging.component.css' ]
})
export class MensajesComponent implements OnInit {
  mensajes: any[] = [];
  nuevoMensaje: string = '';
  usuarioId: number = 1; // ID del usuario actual
  receptorId: number = 2; // ID del receptor

  constructor(private mensajesService: MensajesService) {}

  ngOnInit(): void {
    this.obtenerMensajes();

    // Simular un mensaje recibido después de 5 segundos
    setTimeout(() => {
      const mensajeRecibido = {
        emisor_id: this.receptorId,
        receptor_id: this.usuarioId,
        contenido: '¡Hola! Este es un mensaje recibido.'
      };
      this.mensajes.push(mensajeRecibido);
    }, 5000);
  }

  obtenerMensajes() {
    this.mensajesService.obtenerMensajes(this.usuarioId, this.receptorId).subscribe((data: any) => {
      this.mensajes = data;
    });
  }

  enviarMensaje() {
    if (this.nuevoMensaje.trim()) {
      const mensaje = {
        emisor_id: this.usuarioId,
        receptor_id: this.receptorId,
        contenido: this.nuevoMensaje
      };
      // Agregar el mensaje localmente para que se muestre en pantalla
      this.mensajes.push(mensaje);
      this.nuevoMensaje = ''; // Limpiar el input
    }
  }
}
