package com.fran.contacts.service.Impl;

import com.fran.contacts.dto.RegisterRequest;
import com.fran.contacts.entity.Usuario;
import com.fran.contacts.repository.UsuarioRepository;
import com.fran.contacts.service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario findByEmail(String username) {
        return userRepo.findByEmail(username);
    }

    @Override
    public Usuario guardarUsuario(RegisterRequest registroDTO) {
        // Verificar si ya existe un usuario con el mismo email
        if (userRepo.findByEmail(registroDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo correo electrónico.");
        }

        String encodedPassword = passwordEncoder.encode(registroDTO.getPassword());

        Usuario usuario = new Usuario(
                registroDTO.getNombre(),
                registroDTO.getApellido(),
                registroDTO.getEmail(),
                encodedPassword
        );

        return userRepo.save(usuario);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        Usuario usuario = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con el ID proporcionado."));
        userRepo.delete(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrectos");
        }

        return new User(usuario.getEmail(), usuario.getPassword(), Collections.emptyList());
    }
}
