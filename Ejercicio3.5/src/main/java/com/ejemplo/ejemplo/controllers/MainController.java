package com.ejemplo.ejemplo.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    //3.3  sin Optionales
    //como referencia.
    /*
    @GetMapping({"/", "/index", "/home"})
    public String showHome(@RequestParam(name = "usuario", required = false) String usuario,
                           Model model) {
        String mensaje = "Bienvenido a nuestra web";
        if (usuario != null && !usuario.isBlank()) {
            mensaje = "Bienvenido " + usuario.trim() + " a nuestra web";
        }
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("anno", LocalDate.now().getYear());
        return "inicio";
    }
    */

    //recibir el nombre de usuario de forma opcional.
    @GetMapping({"/", "/index", "/home"})
    public String showHome(@RequestParam(name = "usuario") Optional<String> usuario,
                           Model model) {
        String mensaje = "Bienvenido a nuestra web";
        if (usuario.isPresent() && !usuario.get().isBlank()) {
            mensaje = "Bienvenido " + usuario.get().trim() + " a nuestra web";
        }
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("anno", LocalDate.now().getYear());
        return "inicio";
    }

    @GetMapping("/palmares")
    public String showPalmares(Model model) {
        ArrayList<String> titulos = new ArrayList<>();
        titulos.add("Campeón de La Liga: 27 veces");
        titulos.add("Campeón de la Copa del Rey: 31 veces");
        titulos.add("Campeón de la Liga de Campeones: 5 veces");
        titulos.add("Campeón del Mundial de Clubes: 3 veces");
        titulos.add("Campeón de la Supercopa de Europa: 5 veces");
        model.addAttribute("titulos", titulos);
        return "palmares";
    }

    @GetMapping("/galeria-fotos")
    public String showFotos() {
        return "galeria-fotos";
    }
}
