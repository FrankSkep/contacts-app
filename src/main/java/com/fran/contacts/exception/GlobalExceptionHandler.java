package com.fran.contacts.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public String handleContactNotFoundException(ContactNotFoundException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msgError", e.getMessage());
        return "redirect:/contactos";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception e, Model model) {
        model.addAttribute("msgError", "An unexpected error occurred: " + e.getMessage());
        return "error";
    }
}