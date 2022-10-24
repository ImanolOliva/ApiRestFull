package com.example.neoris.Model;

import javafx.scene.input.KeyCode;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javafx.scene.input.KeyCode.T;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Working_time")
public class TipoJornadaLaboral {
    /** creo mi tabla tipo de jornada Laboral**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String nombre;




    //  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

}
