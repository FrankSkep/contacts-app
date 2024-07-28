package agenda.agenda.Service;

import agenda.agenda.Entities.Contacto;
import agenda.agenda.Entities.Usuario;
import agenda.agenda.Repository.ContactoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServiceImpl implements ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoServiceImpl(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    @Override
    public List<Contacto> findByUsuario(Usuario usuario) {
        return contactoRepository.findByUsuario(usuario);
    }
}
