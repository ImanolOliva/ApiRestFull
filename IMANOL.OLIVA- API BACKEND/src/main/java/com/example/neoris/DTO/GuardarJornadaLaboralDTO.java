package com.example.neoris.DTO;

public class GuardarJornadaLaboralDTO {

    private String fechaDeEntrada;
    private String horaDeEntrada;
    private String horaDeSalida;

    private String dni;

    private Integer idJornadaLaboral;


    public Integer getIdJornadaLaboral() {
        return idJornadaLaboral;
    }

    public void setIdJornadaLaboral(Integer idJornadaLaboral) {
        this.idJornadaLaboral = idJornadaLaboral;
    }

    public GuardarJornadaLaboralDTO() {
    }

    ;

    public GuardarJornadaLaboralDTO(String fechaDeEntrada, String horaDeEntrada, String horaDeSalida, String dni,Integer idJornadaLaboral) {
        this.fechaDeEntrada = fechaDeEntrada;
        this.horaDeEntrada = horaDeEntrada;
        this.horaDeSalida = horaDeSalida;
        this.dni = dni;
        this.idJornadaLaboral = idJornadaLaboral;
    }

    public String getFechaDeEntrada() {
        return this.fechaDeEntrada;

    }

    public void setFechaDeEntrada(String fechaDeEntrada) {
        this.fechaDeEntrada = fechaDeEntrada;
    }

    public String getHoraDeEntrada() {
        return horaDeEntrada;
    }

    public void setHoraDeEntrada(String horaDeEntrada) {
        this.horaDeEntrada = horaDeEntrada;
    }

    public String getHoraDeSalida() {
        return horaDeSalida;
    }

    public void setHoraDeSalida(String horaDeSalida) {
        this.horaDeSalida = horaDeSalida;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}