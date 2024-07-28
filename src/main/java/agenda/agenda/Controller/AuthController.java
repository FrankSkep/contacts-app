package agenda.agenda.Controller;

import agenda.agenda.DTO.UsuarioRegDTO;
import agenda.agenda.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController {

    @Lazy
    private UsuarioService usuarioServicio;

    @Autowired
    public AuthController(UsuarioService usuarioServicio) {
        super();
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegDTO retornarNuevoUsuarioRegDTO() {
        return new UsuarioRegDTO();
    }

    // Maneja la visualización del formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    // Maneja el registro de usuario
    @PostMapping("/registro")
    public String registrarCuentaUsuario(
            @ModelAttribute("usuario") @Valid UsuarioRegDTO regDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // Verificar errores de validación del formulario
        if (bindingResult.hasErrors()) {
            return "registro"; // Retorna al formulario si hay errores de validación
        }

        try {
            usuarioServicio.guardarUsuario(regDTO);
            redirectAttributes.addFlashAttribute("msgExito", "Usuario registrado exitosamente");
            return "redirect:/registro?exito";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("msgError", e.getMessage());
            return "redirect:/registro?error";
        }
    }

    // Maneja la visualización del formulario de inicio de sesión
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }
}
