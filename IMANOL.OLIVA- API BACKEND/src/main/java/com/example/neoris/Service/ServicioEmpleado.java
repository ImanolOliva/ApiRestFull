package com.example.neoris.Service;


import com.example.neoris.Model.Empleado;
import com.example.neoris.Model.Jornada;
import com.example.neoris.Repository.RepositorioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ServicioEmpleado {

    /** Inyecto pool de dependencias con autowired**/
    @Autowired
    private RepositorioEmpleado repositorioEmpleado;


    /** Guardo empleado a traves de metodo save**/
    public Empleado guardarEmpleado(Empleado empleado) {
        Empleado emple = repositorioEmpleado.save(empleado);
        return emple;
    }

/**Listo todos mis empleados con el metodo findAll **/
    public List<Empleado> listarEmpleados(Empleado empleado) {
        List<Empleado> empleados = repositorioEmpleado.findAll();
        return empleados;
    }

    /** Listo todos los empleados por id con el metodo FindById**/
    public Empleado listarEmpleadoPorId(Long id) {
        Empleado empleado = repositorioEmpleado.findById(id).get();
        return empleado;
    }

    public Empleado buscarEmpleadoPorDni(Integer dni) {
        Empleado empleado = repositorioEmpleado.encontrarEmpleadoPorDni(dni);
        return empleado;
    }

}




