package com.example.neoris.Repository;

import com.example.neoris.Model.Empleado;
import com.example.neoris.Model.TipoJornadaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositorioTipoJornadaLaboral extends JpaRepository<TipoJornadaLaboral,Long> {
    /** Extiende de JPA Repository para hacer las operaciones Basicas CRUD**/
    @Query(value = "SELECT * FROM WORKING_TIME WHERE NOMBRE = :NOMBRE",nativeQuery = true)
    TipoJornadaLaboral encontrarJornadaLaboralPorNombre(@Param("NOMBRE")String nombre);

}
