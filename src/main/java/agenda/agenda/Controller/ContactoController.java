package agenda.agenda.Controller;

import agenda.agenda.Entities.Contacto;
import agenda.agenda.Entities.Usuario;
import agenda.agenda.Repository.ContactoRepository;
import agenda.agenda.Repository.UsuarioRepository;
import agenda.agenda.Service.ContactoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ContactoServiceImpl contactoService;

    // Obtencion de todos los contactos
    @GetMapping({"/contactos"})
    public String verPaginaDeInicio(Model modelo, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        String email = userDetails.getUsername();

        Usuario usuario = usuarioRepo.findByEmail(email);
        List<Contacto> contactos = contactoService.findByUsuario(usuario);
        modelo.addAttribute("username", email);
        modelo.addAttribute("contactos", contactos);
        return "index";
    }

    // Direccionamiento al formulario para agregar un nuevo contacto
    @GetMapping("/nuevo")
    public String mostrarFormularioReg(Model modelo) {
        modelo.addAttribute("contacto", new Contacto());
        return "nuevo";
    }

    // Agrega el contacto y lo asocia al usuario autenticado
    @PostMapping("/nuevo")
    public String guardarContacto(
            @ModelAttribute("contacto") @Validated Contacto contacto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            return "nuevo"; // Retorna al formulario si hay errores
        }

        // Obtener el usuario autenticado desde el UserDetails
        Usuario usuario = usuarioRepo.findByEmail(userDetails.getUsername());

        contacto.setUsuario(usuario); // Asocia el contacto con el usuario autenticado
        contactoRepo.save(contacto); // Guarda el contacto en la base de datos
        redirectAttributes.addFlashAttribute("msgExito", "Contacto guardado exitosamente");
        return "redirect:/contactos";
    }

    // Direccionamiento al formulario para editar un contacto
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model modelo) {
        Contacto contacto = contactoRepo.getReferenceById(id);
        modelo.addAttribute("contacto", contacto);
        return "nuevo";
    }

    // Actualiza los datos del contacto
    @PostMapping("/{id}/editar")
    public String actualizarContacto(@PathVariable Integer id, @Validated Contacto contacto, BindingResult bindingRes, RedirectAttributes redirect, Model modelo) {

        Contacto contactoDB = contactoRepo.getReferenceById(id);

        if (bindingRes.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "nuevo";
        }

        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setCelular(contacto.getCelular());
        contactoDB.setEmail(contacto.getEmail());
        contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());

        contactoRepo.save(contactoDB);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido actualizado correctamente");
        return "redirect:/contactos";
    }

    // Elimina un contacto
    @PostMapping("/{id}/eliminar")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirect) {
        Contacto contacto = contactoRepo.getReferenceById(id);
        contactoRepo.delete(contacto);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado correctamente");
        return "redirect:/contactos";
    }
}
