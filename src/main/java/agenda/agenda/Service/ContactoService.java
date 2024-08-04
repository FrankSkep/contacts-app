package agenda.agenda.Service;

import agenda.agenda.Entities.Contacto;
import agenda.agenda.Entities.Usuario;
import java.util.List;

public interface ContactoService {

    public List<Contacto> findByUsuario(Usuario usuario);

    public Contacto obtenerPorID(Integer id);

    public Object guardarContacto(String username, Contacto contacto);

    public Object actualizarContacto(Integer id, Contacto contactoActualizado);

    public void eliminarContacto(Integer id);

}
