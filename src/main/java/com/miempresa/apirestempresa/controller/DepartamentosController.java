package com.miempresa.apirestempresa.controller;

import com.miempresa.apirestempresa.dto.DepartamentoDTO;
import com.miempresa.apirestempresa.entities.Departamentos;
import com.miempresa.apirestempresa.service.DepartamentosService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
@RequiredArgsConstructor
public class DepartamentosController {

    private final DepartamentosService departamentosService;

    @GetMapping("/getAll")
    public List<Departamentos> getDepartamentos() {
        return departamentosService.getDepartamentos();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addDepartamento(@RequestBody DepartamentoDTO departamento) {

        Departamentos newDepartamento = new Departamentos();
        newDepartamento.setNombreDepart(departamento.getDepartamento());

        departamentosService.addDepartamento(newDepartamento);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDepartamento(@RequestBody DepartamentoDTO departamento) {

        boolean isDeleted = departamentosService.deleteDepartamento(departamento.getDepartamento());
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
