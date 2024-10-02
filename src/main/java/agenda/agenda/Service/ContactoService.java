package agenda.agenda.Service;

import agenda.agenda.Entities.Contacto;
import agenda.agenda.Entities.Usuario;

import java.util.List;

public interface ContactoService {

    List<Contacto> findByUsuario(Usuario usuario);

    Contacto obtenerPorID(Integer id);

    Contacto guardarContacto(String username, Contacto contacto);

    Contacto actualizarContacto(Integer id, Contacto contactoActualizado);

    void eliminarContacto(Integer id);

}
