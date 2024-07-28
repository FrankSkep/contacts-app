package agenda.agenda.Service;

import agenda.agenda.DTO.UsuarioRegDTO;
import agenda.agenda.Entities.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;

public interface UsuarioService extends UserDetailsService {

    public Usuario guardarUsuario(UsuarioRegDTO registroDTO);
    public boolean eliminarUsuario(@PathVariable Integer id);
}
