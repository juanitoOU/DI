import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.css']
})
export class ContactoComponent implements OnInit {

  myForm: FormGroup;
  

  constructor(
    private fb: FormBuilder,
    private messageService: MessageService,
  ) { }

  ngOnInit() {
    this.reactiveForm();
  }

  private reactiveForm() {
    this.myForm = this.fb.group({
      usuario: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]],
      correo: ['', [Validators.required, Validators.minLength(1)]],
      contenido: ['', Validators.required]
    });
  }

  saveComentario ( usuario: string, correo: string, contenido: string ){
    console.log(this.myForm.value);
    usuario = this.myForm.get('usuario').value;
    correo = this.myForm.get('correo').value;
    contenido = this.myForm.get('contenido').value;
    console.log(usuario, correo, contenido)
  }



}
