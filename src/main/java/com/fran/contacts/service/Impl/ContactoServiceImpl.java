package com.fran.contacts.service.Impl;

import com.fran.contacts.entity.Contacto;
import com.fran.contacts.entity.Usuario;
import com.fran.contacts.repository.ContactoRepository;
import com.fran.contacts.repository.UsuarioRepository;
import com.fran.contacts.service.ContactoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServiceImpl implements ContactoService {

    private final ContactoRepository contactoRepository;
    private final UsuarioRepository usuarioRepository;

    public ContactoServiceImpl(ContactoRepository contactoRepository, UsuarioRepository usuarioRepository) {
        this.contactoRepository = contactoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Contacto> findByUsuario(Usuario usuario) {
        return contactoRepository.findByUsuario(usuario);
    }

    @Override
    public Contacto obtenerPorID(Integer id) {
        return contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
    }

    @Override
    public Contacto guardarContacto(String username, Contacto contacto) {
        Usuario usuario = usuarioRepository.findByEmail(username);
        contacto.setUsuario(usuario);
        return contactoRepository.save(contacto);
    }

    @Override
    public Contacto actualizarContacto(Integer id, Contacto contactoActualizado) {
        Contacto contactoDB = contactoRepository.getReferenceById(id);
        contactoDB.setNombre(contactoActualizado.getNombre());
        contactoDB.setCelular(contactoActualizado.getCelular());
        contactoDB.setEmail(contactoActualizado.getEmail());
        contactoDB.setFechaNacimiento(contactoActualizado.getFechaNacimiento());
        return contactoRepository.save(contactoDB);
    }

    @Override
    public void eliminarContacto(Integer id) {
        Contacto contacto = contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));
        contactoRepository.delete(contacto);
    }
}
