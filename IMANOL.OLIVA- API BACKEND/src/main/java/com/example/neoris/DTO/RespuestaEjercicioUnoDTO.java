package com.example.neoris.DTO;

import com.example.neoris.Model.Empleado;

import java.util.List;

public class RespuestaEjercicioUnoDTO {

    /*** Se crea un nombre identificador **/
    private String nombre;
    /** Esta lista se utilizaria para conseguir las horas totales y el tipo
     * de jornada laboral
     */
    private List<HorasTrabajadasPorJornadaDTO> jornadas;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<HorasTrabajadasPorJornadaDTO> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<HorasTrabajadasPorJornadaDTO> jornadas) {
        this.jornadas = jornadas;
    }



    public RespuestaEjercicioUnoDTO(String nombre, List<HorasTrabajadasPorJornadaDTO> jornadas) {
        this.nombre = nombre;
        this.jornadas = jornadas;
    }

    public RespuestaEjercicioUnoDTO(){};
}
