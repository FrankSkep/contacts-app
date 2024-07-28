package agenda.agenda.Service;

import agenda.agenda.Entities.Contacto;
import agenda.agenda.Entities.Usuario;
import java.util.List;

public interface ContactoService {

    public List<Contacto> findByUsuario(Usuario usuario);
}
