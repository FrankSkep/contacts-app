package agenda.agenda.Controller;

import agenda.agenda.Entities.Usuario;
import agenda.agenda.Repository.UsuarioRepository;
import agenda.agenda.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServicio;

    public UsuarioController(UsuarioService usuarioServicio) {
        super();
        this.usuarioServicio = usuarioServicio;
    }

    // Muestra vista de informacion de la cuenta
    @GetMapping({"/profile"})
    public String mostrarPerfil(@AuthenticationPrincipal UserDetails userDetails, Model modelo) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        Usuario usuario = usuarioServicio.findByEmail(userDetails.getUsername());

        if (usuario == null) {
            // Manejo del caso en que no se encuentra el usuario
            return "redirect:/error";
        }

        modelo.addAttribute("usuario", usuario);
        return "profile";
    }

    @DeleteMapping("/{id}/eliminarCuenta")
    public String eliminarCuenta(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

        try {
            usuarioServicio.eliminarUsuario(id);
            redirectAttributes.addFlashAttribute("msgExito", "Usuario eliminado exitosamente");
            return "redirect:/logout";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msgError", e.getMessage());
            return "redirect:/registro?error";
        }
    }
}
