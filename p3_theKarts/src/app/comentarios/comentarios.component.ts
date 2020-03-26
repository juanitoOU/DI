import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Comentario } from '../comentario';
import { Articulo } from '../articulo';
import { ArticuloService } from '../articulo.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css']
})
export class ComentariosComponent implements OnInit {

  usuario: String;
  correo: String;
  contenido: String;

  myForm: FormGroup;

comentario: Comentario
  coments: Comentario[];
  listaComents: String[];
  comentarios: Comentario[];

  articulo: Articulo;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,


    private articuloService: ArticuloService,
   

    
  ) { }

  ngOnInit() {

    
  }



  

}


