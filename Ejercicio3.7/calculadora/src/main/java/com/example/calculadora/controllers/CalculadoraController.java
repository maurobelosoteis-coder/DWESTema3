package com.example.calculadora.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CalculadoraController {

    private String operando1 = "";
    private String operando2 = "";
    private String resultado = "";
    private Estado estado = Estado.OPERANDO1;

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("operando1", operando1);
        model.addAttribute("operando2", operando2);
        model.addAttribute("resultado", resultado);
        model.addAttribute("estado", estado.name());
        return "indexView";
    }

    @GetMapping("/digito/{num}")
    public String showDigito(@PathVariable Integer num) {
        switch (estado) {
            case OPERANDO1 -> operando1 += num;
            case OPERANDO2 -> operando2 += num;
            case RESULTADO -> {        
                limpiar();
                operando1 += num;
            }
        }
        return "redirect:/";
    }

    @GetMapping("/suma")
    public String showSuma() {
        
        if (estado == Estado.OPERANDO1 && !operando1.isEmpty()) {
            estado = Estado.OPERANDO2;
        }
        return "redirect:/";
    }

    @GetMapping("/igual")
    public String showIgual() {
        
        if (estado == Estado.OPERANDO2 && !operando2.isEmpty()) {
            long suma = Long.parseLong(operando1) + Long.parseLong(operando2);
            resultado = String.valueOf(suma);
            estado = Estado.RESULTADO;
        }
        return "redirect:/";
    }

    @GetMapping("/clear")
    public String showClear() {
        limpiar();
        return "redirect:/";
    }

    private void limpiar() {
        operando1 = "";
        operando2 = "";
        resultado = "";
        estado = Estado.OPERANDO1;
    }
}
