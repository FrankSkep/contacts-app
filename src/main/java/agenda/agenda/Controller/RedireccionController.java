package agenda.agenda.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedireccionController {

    @GetMapping({"/", ""})
    public String redirigirARutaContactos() {
        return "redirect:/contactos";
    }
}
