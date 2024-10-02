package agenda.agenda.Service;

import agenda.agenda.Entities.Contacto;
import agenda.agenda.Entities.Usuario;
import agenda.agenda.Repository.ContactoRepository;
import agenda.agenda.Repository.UsuarioRepository;
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
        return contactoRepository.getReferenceById(id);
    }

    @Override
    public Contacto guardarContacto(String username, Contacto contacto) {
        // Obtener el usuario autenticado desde el UserDetails
        Usuario usuario = usuarioRepository.findByEmail(username);
        contacto.setUsuario(usuario); // Asocia el contacto con el usuario autenticado
        return contactoRepository.save(contacto); // Guarda el contacto en la base de datos
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
        contactoRepository.deleteById(id);
    }
}
