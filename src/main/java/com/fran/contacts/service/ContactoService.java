package com.fran.contacts.service;

import com.fran.contacts.entity.Contacto;
import com.fran.contacts.entity.Usuario;

import java.util.List;

public interface ContactoService {

    List<Contacto> findByUsuario(Usuario usuario);

    Contacto obtenerPorID(Integer id);

    Contacto guardarContacto(String username, Contacto contacto);

    Contacto actualizarContacto(Integer id, Contacto contactoActualizado);

    void eliminarContacto(Integer id);

}
