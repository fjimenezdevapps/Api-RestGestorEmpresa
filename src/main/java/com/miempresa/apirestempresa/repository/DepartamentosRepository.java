package com.miempresa.apirestempresa.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.apirestempresa.entities.Departamentos;

@Repository
public interface DepartamentosRepository extends JpaRepository<Departamentos, Integer> {
    Optional<Departamentos> findByNombreDepart(String nombreDepart);
}
