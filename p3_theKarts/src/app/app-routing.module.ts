import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component'
import {ArticulosComponent} from './articulos/articulos.component'
import { VistaArticulosComponent } from './vista-articulos/vista-articulos.component';
import { DondeEstamosComponent } from './donde-estamos/donde-estamos.component';
import { QuienesSomosComponent } from './quienes-somos/quienes-somos.component';
import { ContactoComponent } from './contacto/contacto.component';
import { CatalogoComponent } from './catalogo/catalogo.component';





const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home' , component: HomeComponent},
  { path: 'articulos/:id', component: ArticulosComponent },
  { path: 'vista-articulos', component: VistaArticulosComponent },
  { path: 'donde-estamos', component: DondeEstamosComponent },
  { path: 'quienes-somos', component: QuienesSomosComponent },
  { path: 'contacto', component: ContactoComponent },
  { path: 'catalogo', component: CatalogoComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
