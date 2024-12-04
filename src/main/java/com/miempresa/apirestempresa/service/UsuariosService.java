package com.miempresa.apirestempresa.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miempresa.apirestempresa.entities.Usuarios;
import com.miempresa.apirestempresa.repository.UsuariosRepository;

import lombok.RequiredArgsConstructor;

import com.miempresa.apirestempresa.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class UsuariosService {
    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String loginUser(String username, String password) {
        Usuarios user = usuariosRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtTokenProvider.generateToken(username);
    }

    public Usuarios addUsuario(Usuarios usuario) {

        if (usuariosRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuariosRepository.save(usuario);
    }

}
