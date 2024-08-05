package agenda.agenda.Service;

import agenda.agenda.DTO.UsuarioRegDTO;
import agenda.agenda.Entities.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    public Usuario findByEmail(String username);

    public Usuario guardarUsuario(UsuarioRegDTO registroDTO);

    public boolean eliminarUsuario(Integer id);
}
