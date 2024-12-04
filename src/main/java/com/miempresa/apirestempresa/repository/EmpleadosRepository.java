package com.miempresa.apirestempresa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.miempresa.apirestempresa.entities.Departamentos;
import com.miempresa.apirestempresa.entities.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {

        Optional<Empleados> findByNombreAndApellidosAndDepartamento_Id(
        String nombre, String apellidos, int departamentoId);

        Optional<Empleados> findByNombreAndApellidos(String nombre, String apellidos);

        @Modifying
        @Query("UPDATE Empleados e SET e.departamento = null WHERE e.departamento = :departamento")
        void desvincularDepartamento(@Param("departamento") Departamentos departamento);

}
