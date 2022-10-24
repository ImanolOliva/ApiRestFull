package com.example.neoris.Controller;

import com.example.neoris.Model.Empleado;
import com.example.neoris.Model.Jornada;
import com.example.neoris.Model.TipoJornadaLaboral;
import com.example.neoris.Service.ServicioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerEmpleado {
    @Autowired
    private ServicioEmpleado servicioEmpleado;

/*** GUARDAR EMPLEADO A TRAVES DEL METODO SAVE QUE LO VOY A BUSCAR AL REPOSITORIO**/
    @PostMapping("/guardarEmpleado")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado){
       Empleado emple =  servicioEmpleado.guardarEmpleado(empleado);
       if(emple != null){
           return emple;
       }
       return null;
    }
    /*** LISTO EMPLEADO A TRAVES DEL METODO FINDALL QUE LO VOY A BUSCAR AL REPOSITORIO**/

    @GetMapping("/listarEmpleado")
    public List<Empleado> listarEmpleado(Empleado empleado){
        List<Empleado> emple = servicioEmpleado.listarEmpleados(empleado);
        return emple;
    }
    /*** LISTO EMPLEADO POR ID  A TRAVES DEL METODO FINDALL BY ID QUE LO VOY A BUSCAR AL REPOSITORIO**/
    @GetMapping("/listarEmpleadoPorId/{id}")
    public Empleado listarEmpleadoPorId(@PathVariable("id") Long id,Empleado empleado){
        Empleado emple = servicioEmpleado.listarEmpleadoPorId(id);
        return emple;
    }

}
