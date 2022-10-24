package com.example.neoris.Repository;

import com.example.neoris.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEmpleado extends JpaRepository<Empleado,Long>{
 /** Extiende de JPA Repository para hacer las operaciones Basicas CRUD**/
    @Query(value = "SELECT * FROM EMPLOYEE WHERE DNI = :DNI",nativeQuery = true)
    Empleado encontrarEmpleadoPorDni(@Param("DNI")Integer dni);
}
