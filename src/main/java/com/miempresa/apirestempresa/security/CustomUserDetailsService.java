package com.miempresa.apirestempresa.security;

import com.miempresa.apirestempresa.entities.Usuarios;
import com.miempresa.apirestempresa.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuariosRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User.builder()
            .username(usuario.getUsername())
            .password(usuario.getPassword()) 
            .roles("USER")
            .disabled(!usuario.isEnabled())
            .build();
    }

    
}