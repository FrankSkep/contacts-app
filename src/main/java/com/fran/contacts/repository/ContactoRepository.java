package com.fran.contacts.repository;

import com.fran.contacts.entity.Contacto;
import com.fran.contacts.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

    List<Contacto> findByUsuario(Usuario usuario);

}
