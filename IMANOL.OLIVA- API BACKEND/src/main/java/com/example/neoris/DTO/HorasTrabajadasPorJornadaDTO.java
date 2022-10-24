package com.example.neoris.DTO;

/*** El DTO Se crea con la intencion de realizar el punto 4
 * los que capturan los datos de la base de datos **/


public class HorasTrabajadasPorJornadaDTO {

        private String tipoJornada;

        private Integer horasTotales;

    public String getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(String tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    public Integer getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(Integer horasTotales) {
        this.horasTotales = horasTotales;
    }

    public HorasTrabajadasPorJornadaDTO(String tipoJornada, Integer horasTotales) {
        this.tipoJornada = tipoJornada;
        this.horasTotales = horasTotales;
    }

    public HorasTrabajadasPorJornadaDTO(Integer horasTotales) {
        this.horasTotales = horasTotales;
    }
}
