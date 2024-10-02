package agenda.agenda.Service;

import agenda.agenda.DTO.UsuarioDTO;
import agenda.agenda.Entities.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    Usuario findByEmail(String username);

    Usuario guardarUsuario(UsuarioDTO registroDTO);

    void eliminarUsuario(Integer id);
}
