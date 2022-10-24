package com.example.neoris.Controller;


import com.example.neoris.DTO.GuardarJornadaLaboralDTO;
import com.example.neoris.DTO.RespuestaEjercicioUnoDTO;
import com.example.neoris.Model.Empleado;
import com.example.neoris.Model.Jornada;
import com.example.neoris.Model.TipoJornadaLaboral;
import com.example.neoris.Service.ServicioEmpleado;
import com.example.neoris.Service.ServicioJornada;
import com.example.neoris.Service.ServicioTipoJornadaLaboral;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ControllerJornada {

   @Autowired
   private ServicioJornada servicioJornada;

   @Autowired
   private ServicioEmpleado servicioEmpleado;
    @Autowired
    ServicioTipoJornadaLaboral serviceTipoJornadaLaboral;
    /** Endpoint utilizado para pegarle desde el frontend **/

   @PostMapping("/guardarJornada")
   public Jornada guardarJornadaLaboral(@RequestBody GuardarJornadaLaboralDTO guardarJornadaLaboralDTO){
        try {
            Jornada jornada = new Jornada();
            serviceTipoJornadaLaboral.inicializarJornadas();
            jornada.setFechaEntrada(LocalDate.parse((guardarJornadaLaboralDTO.getFechaDeEntrada())));
            jornada.setHoraDeEntrada(LocalTime.parse(guardarJornadaLaboralDTO.getHoraDeEntrada()));
            jornada.setHoraDeSalida(LocalTime.parse(guardarJornadaLaboralDTO.getHoraDeSalida()));
            Empleado empleado = servicioEmpleado.buscarEmpleadoPorDni(Integer.valueOf(guardarJornadaLaboralDTO.getDni()));
            if (empleado != null) {
                jornada.setEmpleado(servicioEmpleado.buscarEmpleadoPorDni(Integer.valueOf(guardarJornadaLaboralDTO.getDni())));
            } else {
                return null;
            }
            TipoJornadaLaboral tipoJornadaLaboral = serviceTipoJornadaLaboral.listarTipoJornadaLaboralPorId(Long.valueOf(guardarJornadaLaboralDTO.getIdJornadaLaboral()));
            if(tipoJornadaLaboral != null){
                jornada.setTipoJornadaLaboral(serviceTipoJornadaLaboral.listarTipoJornadaLaboralPorId(Long.valueOf(guardarJornadaLaboralDTO.getIdJornadaLaboral())));
                return servicioJornada.cargarJornadaLaboral(jornada);
            }else{
                return null;
            }
        }
        catch(Error error){
            return  null;
        }
   }

/***CARGO JORNADA LABORAL A TRAVES DEL METODO SAVE QUE LO EXTRAIGO DEL REPOSITORIO**/
    @PostMapping("/cargarJornadaLaboral")
    public Jornada cargarJornadaLaboral(@RequestBody Jornada jornada){
         Jornada jornada1 = servicioJornada.cargarJornadaLaboral(jornada);
         return jornada1;
    }
    /***CARGO JORNADA LABORAL A TRAVES DEL METODO FIND ALL QUE LO EXTRAIGO DEL REPOSITORIO**/

    @GetMapping("/listarJornadaLaboral")
    public List<Jornada> listarJornadaLaboral(){
        List<Jornada> jor = servicioJornada.listarJornadaLaboral();
        return jor;
    }


    @GetMapping("/listarHorasPorEmpleadoPorJornada")
    public List<RespuestaEjercicioUnoDTO> listarHorasPorEmpleadoPorJornada(){
        return servicioJornada.listarPorEmpleadosPorJornada();
    }

    @PutMapping("/modificar")
    public ResponseEntity actualizarJornada(@RequestBody Jornada jornadaModificada){
        Jornada jornadaActualizada = servicioJornada.actualizarJornadaLaboral(jornadaModificada);
        if (isNull(jornadaActualizada)) {
            //Si me devuelve algun valor nulo
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(jornadaActualizada);
    }

}
