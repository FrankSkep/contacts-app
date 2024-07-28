package agenda.agenda.Repository;

import agenda.agenda.Entities.Contacto;
import agenda.agenda.Entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

    List<Contacto> findByUsuario(Usuario usuario);

}
