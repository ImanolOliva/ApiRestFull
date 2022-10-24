package com.example.neoris.Repository;

import com.example.neoris.Model.Empleado;
import com.example.neoris.Model.Jornada;
import com.example.neoris.Model.TipoJornadaLaboral;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface RepositorioJornada extends JpaRepository<Jornada,Long> {


    /** Esta Query fue creada con la intencion de acomodar la lista para trabajar el punto 4
     * de una manera mas eficiente y ya acomodada*/
    @Query(value = "SELECT ID,FECHA_ENTRADA ,HORA_DE_ENTRADA ,HORA_DE_SALIDA ,EMPLEADO_ID ,TIPO_JORNADA_LABORAL_ID FROM  hs_working_time GROUP BY  empleado_id , tipo_jornada_laboral_id,id",nativeQuery = true)
    /** Me va a traer todos lo que tenga jornada Agrupado por su ID empleado y su ID jornada Laboral **/
    List<Jornada> findAllAndGroupByEmpleadoIdAndTipoJornadaLaboralId();



    @Modifying
    @Transactional
    @Query(value = "update  HS_WORKING_TIME SET  hora_de_entrada = :horaDeEntrada ,hora_de_salida = :horaDeSalida where id= :id",nativeQuery = true)
    Integer updateEmpleados(@Param("horaDeEntrada")LocalTime horaDeEntrada, @Param("horaDeSalida")LocalTime horaDeSalida, @Param("id") Long id);



}
