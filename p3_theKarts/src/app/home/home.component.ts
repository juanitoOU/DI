import { Component, OnInit } from '@angular/core';
import { Articulo } from '../articulo';
import { ArticuloService } from '../articulo.service';
import { Button } from 'protractor';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

articulos : Articulo[] = [];
x: number = 0;
y: number = 8;
toret: Boolean = true;

  constructor(private articuloService: ArticuloService) { }

  ngOnInit() {
    this.getArticulos(this.x, this.y);
  }

  getArticulos(x: number, y: number): void {
    this.articuloService.getArticulos().subscribe(articulos => this.articulos = articulos.slice(this.x, this.y));
  }

  nextPage() { 
    if (this.y != 40) {
      this.x += 8;
      this.y += 8;
      this.getArticulos(this.x, this.y);
      this.subir();
    }
    else {
      this.getArticulos(31, 40);
      this.subir();
      
    }
  }

return():void{
    if (this.x != 0) {
      this.toret = true;
      this.x -= 8;
      this.y -= 8;
      this.getArticulos(this.x, this.y);
      this.subir();
    }
    else {
      this.getArticulos(1, 8);
      this.subir()
    }
  }

  lastPage(): void {
    this.x = 31;
    this.y = 40;
    this.getArticulos(this.x , this.y);
    window.scroll(0,0);
    this.subir();
  }

  firstPage():void {
    this.x = 0;
    this.y = 8;
    this.getArticulos(this.x , this.y);
    this.subir();
  }

  isFirst(): boolean {
    if(this.x==0){
      return true;
    } else {
      return false;
    }
  }

  isLast(): boolean {
    if(this.y==40){
      return true;
    } else {
      return false;
    }
  }

subir():void {
  window.scroll(0,0);
}

desactivarBoton(){
  
}

}
