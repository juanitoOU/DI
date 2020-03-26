import { Component, OnInit, Input } from '@angular/core';
import { Articulo } from '../articulo';
import { ArticuloService } from '../articulo.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Comentario } from '../comentario';


@Component({
  selector: 'app-articulos',
  templateUrl: './articulos.component.html',
  styleUrls: ['./articulos.component.css']
})
export class ArticulosComponent implements OnInit {
  
  articulo: Articulo;
  articulos: Articulo[];

  usuario: String;
  correo: String;
  contenido: String;

  myForm: FormGroup;

comentario: Comentario
  coments: Comentario[];
  listaComents: String[];
  comentarios: Comentario[];


  
  


  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private articuloService: ArticuloService,
    private location: Location,
   
  ) { }

  ngOnInit() {
    this.getArticulo();
    this.reactiveForm();
    

    
/*   this.getComentarios(this.articulo);  */
    
  }

  private reactiveForm() {
    this.myForm = this.fb.group({
      usuario: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]],

      correo: ['', [Validators.required, Validators.minLength(1)]],
      contenido: ['', Validators.required]
    });
  }
  
  getArticulo(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.articuloService.getArticulo(id).subscribe(articulo => this.articulo = articulo);
    
  }

  saveComentario(usuario: string, correo: string, contenido: string) {
    console.log(this.myForm.value);
    console.log(this.articulo.comentarios);
    usuario = this.myForm.get('usuario').value;
    correo = this.myForm.get('correo').value;
    contenido = this.myForm.get('contenido').value;


    this.comentario = new Comentario();
    this.comentario = { usuario: usuario, correo: correo, contenido: contenido }


    this.articulo.comentarios.push(this.comentario);
    console.log(this.articulo.comentarios);

    this.coments = this.articulo.comentarios;

  }

  
 





  goBack(): void {
    this.location.back();
  }

}
