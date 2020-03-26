import { Component, OnInit } from '@angular/core';
import { Articulo } from '../articulo';
import { ArticuloService } from '../articulo.service';
import { PageEvent } from '@angular/material/paginator';


@Component({
  selector: 'app-vista-articulos',
  templateUrl: './vista-articulos.component.html',
  styleUrls: ['./vista-articulos.component.css']
})
export class VistaArticulosComponent implements OnInit {

  articulos: Articulo[] = [];
  x: number = 0;
  y: number = 8;


  constructor(private articuloService: ArticuloService) { }

  ngOnInit() {
    this.getArticulos();
  }


  getArticulos(): void {
    this.articuloService.getArticulos().subscribe(articulos => this.articulos = articulos);
  }



 
  /* nextPage(): void {
    if (this.y != 40) {
      this.x += 8;
      this.y += 8;
      this.getArticulos(this.x, this.y);
    }
    else {
      this.getArticulos(31, 40);
    }
  }



  lastPage(): void {

    if (this.x != 0) {
      this.x -= 8;
      this.y -= 8;
      this.getArticulos(this.x, this.y);
    }
    else {
      this.getArticulos(1, 8);
    }

  }
 */



}

