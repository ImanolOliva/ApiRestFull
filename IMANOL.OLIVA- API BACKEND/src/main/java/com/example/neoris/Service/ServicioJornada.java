package com.example.neoris.Service;

import com.example.neoris.DTO.HorasTrabajadasPorJornadaDTO;
import com.example.neoris.DTO.RespuestaEjercicioUnoDTO;
import com.example.neoris.Model.Empleado;
import com.example.neoris.Model.Jornada;
import com.example.neoris.Model.TipoJornadaLaboral;
import com.example.neoris.Repository.RepositorioEmpleado;
import com.example.neoris.Repository.RepositorioJornada;
import com.example.neoris.Repository.RepositorioTipoJornadaLaboral;
import org.hibernate.boot.JaccPermissionDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.time.LocalTime.of;
import static java.util.Objects.isNull;

@Service
public class ServicioJornada {

    /**
     * Inyecto pool de dependencias
     **/
    @Autowired
    RepositorioJornada repositorioJornada;
    @Autowired
    RepositorioEmpleado repositorioEmpleado;
    @Autowired
    RepositorioTipoJornadaLaboral repositorioTipoJornadaLaboral;


    /*** Cargo mi jornada a traves del metodo Save**/
    public Jornada cargarJornadaLaboral(Jornada jornada) {
        return repositorioJornada.save(jornada);
    }

    /**
     * Listo Toda mi jornada
     **/
    public List<Jornada> listarJornadaLaboral() {
        List<Jornada> jorn = repositorioJornada.findAll();
        return jorn;
    }

    /**
     * Este metodo se utilizaria para validar la cantidad de horas
     * semanales que trabajo el empleado
     **/

    public String validacionDeHorasSemanales(Integer horasTotales) {
        if (horasTotales > 48) {
            return "Sobrepasa el limite de horas semanales";
        } else if (horasTotales < 30) {
            return "Sobrepasa el limite de horas minimas";
        }
        return "Horas cargadas con exito";
    }


    /**
     * Metodo que me retorna la cantidad de horas que trabajo un Empleado
     **/
    //  Listar para cada empleado la cantidad de horas cargadas por cada tipo de jornada laboral.
    public List<RespuestaEjercicioUnoDTO> listarPorEmpleadosPorJornada(){
        //Creo una lista que me devuelva la respuesta
        List<RespuestaEjercicioUnoDTO> respuesta = new ArrayList<>();
        //Me devuelve la lista agrupada por sus respectivos Id
        List<Jornada> jornadas = repositorioJornada.findAllAndGroupByEmpleadoIdAndTipoJornadaLaboralId();
        //Lista de horas trabajadas por jornada DTO
        ArrayList<HorasTrabajadasPorJornadaDTO> horasTrabajadas = new ArrayList<>();
        //Inicializo los valores en el primer registro
        Long idEmpleadoAnterior = jornadas.get(0).getEmpleado().getId();
        //Inicializo los valores en el primer registro
        Long idJornadaLaboralAnterior = jornadas.get(0).getTipoJornadaLaboral().getId();

        //sumo las horas para el total de horas
        String nombreEmpleadoAnterior = jornadas.get(0).getEmpleado().getNombre();
        Integer horasTotales=0;
        String nombreTipoJornada = "";
        for(Jornada jornada: jornadas){
            if(idEmpleadoAnterior.equals(jornada.getEmpleado().getId())){
                if(idJornadaLaboralAnterior.equals(jornada.getTipoJornadaLaboral().getId())){
                    Integer horasDiarias = jornada.getHoraDeSalida().getHour() - jornada.getHoraDeEntrada().getHour();
                    horasTotales += horasDiarias;
                    nombreTipoJornada = jornada.getTipoJornadaLaboral().getNombre();
                    }else{
                    HorasTrabajadasPorJornadaDTO horas = new HorasTrabajadasPorJornadaDTO(nombreTipoJornada,horasTotales);
                    horasTrabajadas.add(horas);
                    horasTotales =0;
                    nombreTipoJornada = "";
                    Integer horasDiarias = jornada.getHoraDeSalida().getHour() - jornada.getHoraDeEntrada().getHour();
                    horasTotales += horasDiarias;
                    nombreTipoJornada = jornada.getTipoJornadaLaboral().getNombre();

                    idJornadaLaboralAnterior = jornada.getTipoJornadaLaboral().getId();
                    }
                }else{
                    HorasTrabajadasPorJornadaDTO horas = new HorasTrabajadasPorJornadaDTO(nombreTipoJornada,horasTotales);
                    horasTrabajadas.add(horas);
                    respuesta.add(new RespuestaEjercicioUnoDTO(nombreEmpleadoAnterior,horasTrabajadas));

                    horasTrabajadas = new ArrayList<>();

                    horasTotales =0;
                    nombreTipoJornada = "";

                    Integer horasDiarias = jornada.getHoraDeSalida().getHour() - jornada.getHoraDeEntrada().getHour();
                    horasTotales += horasDiarias;
                    nombreTipoJornada = jornada.getTipoJornadaLaboral().getNombre();


                    nombreEmpleadoAnterior = jornada.getEmpleado().getNombre();
                    idEmpleadoAnterior = jornada.getEmpleado().getId();
                    idJornadaLaboralAnterior = jornada.getTipoJornadaLaboral().getId();
                }
        }
        /** Estoy afuera del for trabajando con el ultimo empleado**/
        HorasTrabajadasPorJornadaDTO horas = new HorasTrabajadasPorJornadaDTO(nombreTipoJornada,horasTotales);
        horasTrabajadas.add(horas);
        respuesta.add(new RespuestaEjercicioUnoDTO(nombreEmpleadoAnterior,horasTrabajadas));

        return respuesta;
    }


    /**Actualizar jornada Laboral **/

    public Jornada actualizarJornadaLaboral(Jornada jornada) {

        Integer cantidadDeActualizaciones =  repositorioJornada.updateEmpleados(jornada.getHoraDeEntrada(),jornada.getHoraDeSalida(),jornada.getId());

        if(cantidadDeActualizaciones>0) {
            jornada.setHoraDeEntrada(jornada.getHoraDeEntrada());
            jornada.setHoraDeSalida(jornada.getHoraDeSalida());
            jornada.setId(jornada.getId());
            return repositorioJornada.save(jornada);
        }else{
            return null;
        }
    }
}






