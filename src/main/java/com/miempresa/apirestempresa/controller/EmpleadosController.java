package com.miempresa.apirestempresa.controller;

import com.miempresa.apirestempresa.dto.EmpleadoDTO;
import com.miempresa.apirestempresa.dto.EmpleadoRequest;
import com.miempresa.apirestempresa.entities.Departamentos;
import com.miempresa.apirestempresa.entities.Empleados;
import com.miempresa.apirestempresa.service.DepartamentosService;
import com.miempresa.apirestempresa.service.EmpleadosService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@RequiredArgsConstructor
public class EmpleadosController {

    @Autowired
    private final EmpleadosService empleadosService;

    @Autowired
    private final DepartamentosService departamentosService;

    @GetMapping("/getAll")
    public List<Empleados> getDepartamentos() {
        return empleadosService.getEmpleados();
    }

    @PostMapping("/add")
    public ResponseEntity<Empleados> addEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {

        Empleados empleado = new Empleados();
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellidos(empleadoDTO.getApellidos());
        empleado.setNacionalidad(empleadoDTO.getNacionalidad());
        empleado.setHorasTrabajadas(empleadoDTO.getHorasTrabajadas());

        Departamentos departamento = departamentosService.getDepartamento(empleadoDTO.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        empleado.setDepartamento(departamento);

        Empleados nuevoEmpleado = empleadosService.addEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    @Transactional
    public ResponseEntity<Void> deleteEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {

        boolean isDeleted = empleadosService.deleteEmpleado(
                empleadoRequest.getNombre(),
                empleadoRequest.getApellidos(),
                empleadoRequest.getDepartamentoId());
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Empleados> updateEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {

        Empleados empleado = new Empleados();
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellidos(empleadoDTO.getApellidos());
        empleado.setNacionalidad(empleadoDTO.getNacionalidad());
        empleado.setHorasTrabajadas(empleadoDTO.getHorasTrabajadas());

        Departamentos departamento = departamentosService.getDepartamento(empleadoDTO.getDepartamentoId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        empleado.setDepartamento(departamento);

        empleado.setDepartamento(departamento);

        Empleados empleadoUpdated = empleadosService.updateEmpleado(empleado);

        try {
            return new ResponseEntity<>(empleadoUpdated, HttpStatus.OK);
        } catch (RuntimeException e) {

            return new ResponseEntity<>(empleadoUpdated, HttpStatus.NOT_FOUND);
        }
    }

}
