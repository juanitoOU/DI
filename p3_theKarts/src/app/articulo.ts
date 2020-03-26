import { Url } from 'url';
import {Comentario} from './comentario'

export class Articulo {
    id: number;
    titulo: string;
    resumen: string;
    contenido: string;
    imagen: string;
    autor: string;
    fecha: string;
    comentarios: Comentario[];
}

