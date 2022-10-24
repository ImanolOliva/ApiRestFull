package com.example.neoris.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;



@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Empleado {
    /*** creo mi tabla empleados independiente de otra  **/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30 , message = "El numero de caracteres no es permitido debe de estar entre 4 y 30")
    private String nombre;

    @NotNull
    private Integer dni;

    @NotNull
    @NotBlank
    @Email
    private String email;



}
