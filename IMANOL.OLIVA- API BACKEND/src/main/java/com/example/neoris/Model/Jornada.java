package com.example.neoris.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;




@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hs_Working_Time")
public class Jornada {
    /***Creo mi tabla de Jornadas que va a estar relacionada con
     * Tipo de Jornada Laboral y la tabla de Empleado
     * en una relacion Many to one a traves de sus ID
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /** Trabajo las fechas con anios mes y dias
     * en formato yyyy-hh-mm
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate fechaEntrada;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    /** Trabajo las horas con horas minutos y segundos **/
    private LocalTime horaDeEntrada;
    /** Trabajo las horas con horas minutos y segundos **/
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime horaDeSalida;


    @ManyToOne
    private Empleado empleado;

    @ManyToOne
    private TipoJornadaLaboral tipoJornadaLaboral;
    //LO HARCODEO CON UN SELECT X ID

}
