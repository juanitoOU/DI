import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { HomeComponent } from './home/home.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NavComponent } from './nav/nav.component';



import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { HttpClientModule } from '@angular/common/http';
import { InMemoryDataService }  from './in-memory-data.service';
import { ArticulosComponent } from './articulos/articulos.component';

import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatTabsModule } from '@angular/material/tabs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { VistaArticulosComponent } from './vista-articulos/vista-articulos.component';
import { QuienesSomosComponent } from './quienes-somos/quienes-somos.component';
import { DondeEstamosComponent } from './donde-estamos/donde-estamos.component';
import { ContactoComponent } from './contacto/contacto.component';
import { CatalogoComponent } from './catalogo/catalogo.component';
import { ComentariosComponent } from './comentarios/comentarios.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavComponent,
    ArticulosComponent,
    VistaArticulosComponent,
    QuienesSomosComponent,
    DondeEstamosComponent,
    ContactoComponent,
    CatalogoComponent,
    ComentariosComponent,

    
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
  
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { dataEncapsulation: false }
    ),
    MatInputModule,
    MatButtonModule,
    MatRadioModule,
    MatFormFieldModule,
    AppRoutingModule,
    MDBBootstrapModule.forRoot(),
    BrowserAnimationsModule,
    
    FormsModule,
    ReactiveFormsModule,
    
  ],
  
  providers: [],

  bootstrap: [AppComponent]
})
export class AppModule { }
