package agenda.agenda.Controller;

import agenda.agenda.Entities.Usuario;
import agenda.agenda.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class UsuarioController {

    private final UsuarioService usuarioServicio;

    public UsuarioController(UsuarioService usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    // Muestra vista de informacion de la cuenta
    @GetMapping
    public String mostrarPerfil(@AuthenticationPrincipal UserDetails userDetails, Model modelo) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());

        if (usuario == null) {
            return "redirect:/error";
        }
        modelo.addAttribute("usuario", usuario);
        return "profile";
    }

    @DeleteMapping("/eliminarCuenta/{id}")
    public String eliminarCuenta(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {

        Integer idPropietario = usuarioServicio.findByEmail(userDetails.getUsername()).getId();

        if (idPropietario.equals(id)) {
            try {
                usuarioServicio.eliminarUsuario(id);
                redirectAttributes.addFlashAttribute("msgExito", "Usuario eliminado exitosamente");
                return "redirect:/logout";
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("msgError", e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("msgError", "No eres propietario de la cuenta con id " + id);
        }
        return "redirect:/profile";
    }
}
