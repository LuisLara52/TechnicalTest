import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConsultarComponent } from './Tarjeta/consultar/consultar.component';
import { CrearComponent } from './Tarjeta/crear/crear.component';
import { EliminarComponent } from './Tarjeta/eliminar/eliminar.component';
import { EnrolarComponent } from './Tarjeta/listarT/enrolar.component';
import { ListarComponent } from './Tarjeta/listar/listar.component';

const routes: Routes = [
  {path:'consultar', component:ConsultarComponent},
  {path:'listarT', component:EnrolarComponent},
  {path:'eliminar', component:EliminarComponent},
  //{path:'enrolar', component:EnrolarComponent},
  {path:'listar', component:ListarComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
