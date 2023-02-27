import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConsultarComponent } from './Tarjeta/consultar/consultar.component';
import { CrearComponent } from './Tarjeta/crear/crear.component';
import { EnrolarComponent } from './Tarjeta/listarT/enrolar.component';
import { EliminarComponent } from './Tarjeta/eliminar/eliminar.component';
import { ListarComponent } from './Tarjeta/listar/listar.component';
import{ FormsModule} from '@angular/forms'
import {ServiceService} from '../app/Service/service.service'
import {HttpClientModule} from '@angular/common/http'

@NgModule({
  declarations: [
    AppComponent,
    ConsultarComponent,
    CrearComponent,
    EnrolarComponent,
    EliminarComponent,
    ListarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
