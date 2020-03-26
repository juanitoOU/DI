import { Injectable } from '@angular/core';
import { Articulo } from './articulo';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';
import { Comentario } from './comentario';

@Injectable({
  providedIn: 'root'
})
export class ArticuloService {
  private articulosUrl = 'api/articulos';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  private page : number;
  constructor(private http: HttpClient,
    private messageService: MessageService) { }



  /** GET articulos from the server */
  getArticulos(): Observable<Articulo[]> {
    return this.http.get<Articulo[]>(this.articulosUrl)
      .pipe(
        tap(_ => this.log('fetched players')),
        catchError(this.handleError<Articulo[]>('getArticulos', []))
      );
  }


  /** GET player by id. Will 404 if id not found */
  getArticulo(id: number): Observable<Articulo> {
    const url = `${this.articulosUrl}/${id}`;
    return this.http.get<Articulo>(url).pipe(
      tap(_ => this.log(`fetched articulo id=${id}`)),
      catchError(this.handleError<Articulo>(`getArticulo id=${id}`))
    );
  }
getComentarios(id:number):Observable<Comentario[]> {
 const url = `${this.articulosUrl}/${id}`;
  return this.http.get<Articulo["comentarios"]>(url)
    .pipe(
      tap(_ => this.log('fetched comentarios')),
      catchError(this.handleError<Comentario[]>('getComentarios', []))
    );
}


  /** PUT: update the player on the server */
  /* updateComentarios(articulo: Articulo | number): Observable<Articulo> {
    const url = `${this.articulosUrl}/${articulo.id}`;
        return this.http.put(this.articulosUrl, articulo.comentarios, this.httpOptions).pipe(
      tap(_ => this.log(`updated coment=${comentarios}`)),
      catchError(this.handleError<any>('updateComments'))
    );
  } */

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`ArticuloService: ${message}`);
  }


 

}
