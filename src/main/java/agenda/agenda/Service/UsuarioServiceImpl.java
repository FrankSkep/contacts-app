package agenda.agenda.Service;

import agenda.agenda.DTO.UsuarioRegDTO;
import agenda.agenda.Entities.Usuario;
import agenda.agenda.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    // Inyección de dependencias a través del constructor
    @Autowired
    public UsuarioServiceImpl(UsuarioRepository userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario findByEmail(String username) {
        return userRepo.findByEmail(username);
    }

    @Override
    public Usuario guardarUsuario(UsuarioRegDTO registroDTO) {
        // Verificar si ya existe un usuario con el mismo email
        if (userRepo.findByEmail(registroDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo correo electrónico.");
        }

        // Codificar la contraseña
        String encodedPassword = passwordEncoder.encode(registroDTO.getPassword());

        // Crear un nuevo objeto Usuario
        Usuario usuario = new Usuario(
                registroDTO.getNombre(),
                registroDTO.getApellido(),
                registroDTO.getEmail(),
                encodedPassword
        );

        // Guardar el usuario en el repositorio
        return userRepo.save(usuario);
    }

    @Override
    public boolean eliminarUsuario(@PathVariable Integer id) {
        Usuario usuario = userRepo.getReferenceById(id);

        if (usuario == null) {
            return false;
        }
        userRepo.delete(usuario);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = userRepo.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o contraseña incorrectos");
        }

        return new User(usuario.getEmail(), usuario.getPassword(), Collections.emptyList());
    }
}
