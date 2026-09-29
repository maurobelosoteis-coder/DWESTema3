package com.example.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller // anotación controlador
public class IndexController {

    private int[] votos = {0, 0, 0};

    @GetMapping({ "/", "/index" })
    public String showHome(Model model) {
        model.addAttribute("votos", votos);
        return "indexView";
    }

    @GetMapping("/voto")
    public String sumarVoto(@RequestParam(required = true) int foto, Model model) {

        votos[foto]++;
        model.addAttribute("votos", votos);

        // return "indexView";
        return "redirect:/";
    }   
}
