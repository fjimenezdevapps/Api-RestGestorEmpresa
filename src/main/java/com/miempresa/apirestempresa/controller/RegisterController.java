package com.miempresa.apirestempresa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.miempresa.apirestempresa.service.UsuariosService;

import com.miempresa.apirestempresa.entities.Usuarios;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UsuariosService usuariosService;

    @PostMapping
    public ResponseEntity<Usuarios> register(@RequestBody Usuarios usuario) {
        try {
            Usuarios nuevoUsuario = usuariosService.addUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}