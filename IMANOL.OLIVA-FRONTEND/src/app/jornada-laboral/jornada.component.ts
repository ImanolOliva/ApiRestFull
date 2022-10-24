import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Observable, throwError } from 'rxjs';

import { pipe} from 'rxjs';
import { Observer} from 'rxjs';
import { json, response } from 'express';
import { Jornada} from './models/jornada';
import { JornadaService } from './services/jornada.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-jornada-laboral',
  templateUrl: './jornada-laboral.component.html',
  styleUrls: ['./jornada-laboral.component.css']
})
export class JornadaLaboralComponent implements OnInit {
  //FORM: IMPLEMENTA VALIDACIONES QUE VAN A SER UTILIZADAS EN EL DOCUMENTO HTML
  form = this.fb.group({
    fechaDeEntrada: ['',Validators.required],
    horaDeEntrada:  ['',Validators.required],
    horaDeSalida:  ['',Validators.required],
    dni: ['',Validators.minLength(6)],
    idJornadaLaboral: ['']
  })
//SE INYECTA EL FORMULARIO REACTIVO Y EL SERVICIO JORNADA 
  constructor(private fb: FormBuilder,private jornadaService: JornadaService){
   }
 
    ngOnInit(): void {}
   //METODO QUE SE UTILIZA PARA GUARDAR JORNADA LABORAL*/ 
  guardarJornada(){
    let jornadaLaboral = new Jornada();
   
    jornadaLaboral.fechaDeEntrada =this.form.get('fechaDeEntrada')?.value!;
    jornadaLaboral.horaDeEntrada =  this.form.get('horaDeEntrada')?.value!;
    jornadaLaboral.horaDeSalida =  this.form.get('horaDeSalida')?.value!;
    jornadaLaboral.dni =  this.form.get('dni')?.value!;
    jornadaLaboral.idJornadaLaboral = this.form.get('idJornadaLaboral')?.value!;
    //SI EL ID ES =4 | =5 UTILIZO PROPIEDAD DISABLE PARA ANULAR LOS CAMPOS: HS ENTRADA Y HS SALIDA 
    //LES SETEO SUS VALORES EN 00:00:00
    if(jornadaLaboral.idJornadaLaboral == '4' || jornadaLaboral.idJornadaLaboral == '5' ){
      jornadaLaboral.horaDeEntrada = '00:00:00';
      jornadaLaboral.horaDeSalida = '00:00:00';

    }
  //SE UTILIZA EL SUBSCRIBE, EL CUAL PUEDE ACCEDER AL OBSERVABLE: CREAR JORNADA
    //QUE SE INYECTA MEDIANTE EL SERVICIO 
   this.jornadaService.crearJornadaLaboral(jornadaLaboral).subscribe
    ( 
     {
         //LAS CLASES SWAL.FIRE SE UTILIZAN PARA CAPTURAR LOS ERRORES Y DARLE ESTILO A LOS ERRORES 
        //COMO ASI TAMBIEN A LOS OBJETIVOS CUMPLIDOS  
        next: res =>{
          if(res == null){
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'No existe empleado con tal DNI!'
            })
          }else{
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Jornada laboral cargada',
              showConfirmButton: false,
              timer: 1500
            })    
            this.form.reset();
          }
        },
          error: err =>{
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Verifique los datos ingresados!',
            })
          },
          complete: () =>{
            console.log("Recurso utilizado")
          } 
     }
    )
 }
 //SE UTILIZA EL METODO INAVILITAR EL EL HTML EL CUAL RESIVE UN EVENT Y ME PERMITE
 //DESABILITAR LOS CAMPOS 4 Y 5  EN TIEMPO REAL

  inabilitar(event: any)
  {
    debugger
    console.log(event);
   /** */ let jornadaLaboral = new Jornada();
    //jornadaLaboral.horaDeEntrada =  this.form.get('horaDeEntrada')?.disable()!;
   //jornadaLaboral.horaDeSalida =  this.form.get('horaDeSalida')?.disable()!; 
  
   if(event.target.value == '4' || event.target.value == '5'){

      jornadaLaboral.horaDeEntrada = '00:00:00';
      this.form.get('horaDeEntrada')?.disable();
      jornadaLaboral.horaDeSalida = '00:00:00';
      this.form.get('horaDeSalida')?.disable();
   }else{
    this.form.get('horaDeSalida')?.enable();
    this.form.get('horaDeEntrada')?.enable();


   }
    

  }

}

