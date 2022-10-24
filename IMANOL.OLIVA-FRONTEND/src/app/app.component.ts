import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormControl,FormControlName,FormGroup, Validators } from '@angular/forms';
import { EmpleadoService } from './services/empleado.service';

import { Empleado } from './models/empleado';
import { Observer , of } from 'rxjs';
import { json } from 'express';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  form = this.fb.group({
    nombre: ['',Validators.required],
    email:  ['',Validators.required],
    dni:  ['',Validators.required],
  })
//SE INYECTA FORMBUILDER PARA PODER UTILIZAR EL FORMULARIO REACTIVO 
//SE INYECTA EL SERVICIO EL CUAL CONTIENE EL METODO PARA DAR DE ALTA UN EMPLEADO 
  constructor(private fb: FormBuilder ,private empleadoService: EmpleadoService){
    console.log('Construyendo la clase');
  }
  ngOnInit(): void {
    
  }
//METODO QUE SE UTILIZA PARA EL POST DEL EMPLEADO
  guardarEmpleado(){
    let empleado = new Empleado();
//SE INSTANCIA UN NUEVO EMPLEADO
    empleado.nombre = this.form.get('nombre')?.value!;
    empleado.dni =  this.form.get('dni')?.value!;
    empleado.email = this.form.get('email')?.value!;

    //SE UTILIZA EL SUBSCRIBE, EL CUAL PUEDE ACCEDER AL OBSERVABLE: CREAR EMPLEADO
    //QUE SE INYECTA MEDIANTE EL SERVICIO 

    this.empleadoService.crearEmpleado(empleado).subscribe(
      {
        //LAS CLASES SWAL.FIRE SE UTILIZAN PARA CAPTURAR LOS ERRORES Y DARLE ESTILO A LOS ERRORES 
        //COMO ASI TAMBIEN A LOS OBJETIVOS CUMPLIDOS  
        next: res => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Empleado dado de alta',
          showConfirmButton: false,
          timer: 1500
        })    
        this.form.reset();
        },
        error: err  => { 
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Verifique los datos ingresados!',
          })
        },
        complete: () =>{
          console.log("Recurso utilizado")
        } 
      });
  }



}

