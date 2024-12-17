import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { RegisterParametersComponent } from './register-parameters/register-parameters.component';
import { AppointmentsComponent } from './appointments/appointments.component';
import { MensajesComponent } from './messaging/messaging.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { HomeMedicoComponent } from './home-medico/home-medico.component';
import { RegisterPatientsComponent } from './register-patients/register-patients.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterParametersComponent,
    AppointmentsComponent,
    MensajesComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    HomeMedicoComponent,
    RegisterPatientsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule
  ],
  providers: [
    provideClientHydration(withEventReplay()),
    provideAnimationsAsync(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
