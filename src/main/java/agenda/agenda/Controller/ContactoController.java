package agenda.agenda.Controller;

import agenda.agenda.Entities.Contacto;
import agenda.agenda.Entities.Usuario;
import agenda.agenda.Service.ContactoService;
import agenda.agenda.Service.UsuarioService;
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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contactos")
public class ContactoController {

    private final UsuarioService usuarioService;
    private final ContactoService contactoService;

    public ContactoController(UsuarioService usuarioService, ContactoService contactoService) {
        this.usuarioService = usuarioService;
        this.contactoService = contactoService;
    }

    @GetMapping({"/"})
    public String redirigirARutaContactos() {
        return "redirect:/contactos";
    }

    // Obtencion de todos los contactos
    @GetMapping
    public String verPaginaDeInicio(Model modelo, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        String email = userDetails.getUsername();

        Usuario usuario = usuarioService.findByEmail(email);
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

        Object result = contactoService.guardarContacto(userDetails.getUsername(), contacto);
        if (result instanceof Contacto) {
            redirectAttributes.addFlashAttribute("msgExito", "Contacto guardado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("msgError", result);
        }

        return "redirect:/contactos";
    }

    // Direccionamiento al formulario para editar un contacto
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model modelo) {
        Contacto contacto = contactoService.obtenerPorID(id);
        modelo.addAttribute("contacto", contacto);
        return "nuevo";
    }

    // Actualiza los datos del contacto
    @PutMapping("/editar/{id}")
    public String actualizarContacto(@PathVariable Integer id, @Validated Contacto contacto, BindingResult bindingRes,
                                     RedirectAttributes redirect, Model modelo) {

        if (bindingRes.hasErrors()) {
            modelo.addAttribute("contacto", contacto);
            return "nuevo";
        }

        Object result = contactoService.actualizarContacto(id, contacto);
        if (result instanceof Contacto) {
            redirect.addFlashAttribute("msgExito", "El contacto ha sido actualizado correctamente");
        } else {
            redirect.addFlashAttribute("msgError", result);
        }

        return "redirect:/contactos";
    }

    // Elimina un contacto
    @DeleteMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirect) {
        contactoService.eliminarContacto(id);
        redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado correctamente");
        return "redirect:/contactos";
    }
}
