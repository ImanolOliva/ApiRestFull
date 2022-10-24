import { EnvironmentInjector, Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { environment } from "src/environments/environment";
import { Jornada, JornadaResponse } from '../models/jornada';
import {Observable , Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JornadaService {
  form: any;

  constructor(private http:HttpClient){
    console.log('Dentro de la jornada laboral')
  }
  

  crearJornadaLaboral(request: Jornada) :Observable<Object>{
    let endpoint = 'api/guardarJornada';
    return this.http.post(environment.apiEmpleados + endpoint, request);
  }

  
}
