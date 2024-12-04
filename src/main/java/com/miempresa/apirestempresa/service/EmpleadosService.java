package com.miempresa.apirestempresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miempresa.apirestempresa.entities.Empleados;

import com.miempresa.apirestempresa.repository.EmpleadosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadosService {

    private final EmpleadosRepository empleadosRepository;

    public List<Empleados> getEmpleados() {
        return empleadosRepository.findAll();
    }

    public Empleados addEmpleado(Empleados empleado) {
        return empleadosRepository.save(empleado);
    }

    public boolean deleteEmpleado(String nombre, String apellidos, int departamentoId) {

        Optional<Empleados> empleadoOpt = empleadosRepository.findByNombreAndApellidosAndDepartamento_Id(
                nombre, apellidos, departamentoId);

        if (empleadoOpt.isPresent()) {
            empleadosRepository.delete(empleadoOpt.get());
            return true;
        }
        return false;
    }

    public Empleados updateEmpleado(Empleados empleadoUpdate) {

        Empleados empleado = empleadosRepository
                .findByNombreAndApellidos(empleadoUpdate.getNombre(), empleadoUpdate.getApellidos())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado en el departamento especificado"));

        empleado.setNombre(empleadoUpdate.getNombre());
        empleado.setApellidos(empleadoUpdate.getApellidos());
        empleado.setHorasTrabajadas(empleadoUpdate.getHorasTrabajadas());
        empleado.setDepartamento(empleadoUpdate.getDepartamento());

        return empleadosRepository.save(empleado);
    }
}
