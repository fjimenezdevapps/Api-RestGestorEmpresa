package com.miempresa.apirestempresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.miempresa.apirestempresa.entities.Departamentos;
import com.miempresa.apirestempresa.repository.DepartamentosRepository;
import com.miempresa.apirestempresa.repository.EmpleadosRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentosService {

    private final DepartamentosRepository departamentosRepository;
    private final EmpleadosRepository empleadosRepository;

    public List<Departamentos> getDepartamentos() {
        return departamentosRepository.findAll();
    }

    public Optional<Departamentos> getDepartamento(int id) {
        return departamentosRepository.findById(id);
    }

    public Departamentos addDepartamento(Departamentos departramento) {
        return departamentosRepository.save(departramento);
    }


    @Transactional
    public boolean deleteDepartamento(String departamento) {
        
        Optional<Departamentos> departamentoAux = departamentosRepository.findByNombreDepart(departamento);

        if (departamentoAux.isPresent()) {
            Departamentos departamentoToDelete = departamentoAux.get();

            
            empleadosRepository.desvincularDepartamento(departamentoToDelete);

            departamentosRepository.delete(departamentoToDelete);
            return true;
        }
        return false;
    }
}
