import { EnvironmentInjector, Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Empleado } from "../models/empleado";
import { Observable } from "rxjs";

import { environment } from "src/environments/environment";


@Injectable({
    providedIn: 'root'
})

export class EmpleadoService{

  constructor(private http:HttpClient){
      console.log('Servicio http');
  }
  //SE IMPLEMENTA EL PATRON OBSERVABLE QUE RECIBE UN OBJETO Y ENVIA EL OBJETO EMPLEADO
//RETORNA METODO POST PEGANDOLE AL ENDPOINT QUE FIGURA Y HACE REFERENCIA AL QUE ESTA EN LA API
//LE ENVIO EN LA REQUEST EL OBJETO EMPLEADO  
  crearEmpleado(request: Empleado) :Observable<Object>{
        let endpoint = 'api/guardarEmpleado';
         return this.http.post(environment.apiEmpleados + endpoint, request);
  }

  mostrarEmpleados() :Observable<Empleado[]>{
    let endpoint = 'api/listarEmpleado';
    return this.http.get<Empleado[]>(environment.apiEmpleados + endpoint);

  }
}
