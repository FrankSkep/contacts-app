package com.fran.contacts.service;

import com.fran.contacts.dto.RegisterRequest;
import com.fran.contacts.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    Usuario findByEmail(String username);

    Usuario guardarUsuario(RegisterRequest registroDTO);

    void eliminarUsuario(Integer id);
}
