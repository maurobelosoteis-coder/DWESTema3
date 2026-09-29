package com.example.numerosaleatorios;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Le indica a Spring que esta clase es un controlador encargado de recibir peticiones HTTP
@Controller
public class NumerosController {

    Random random = new Random();

    private Set<Integer> lista = new LinkedHashSet<>();

    //Atiende peticiones HTTP GET a la raíz(/), /list o a la ruta vacía
    /*
    Model: Objeto de Spring que actúa como canal para pasar datos
    desde el controlador hacia la 
    vista HTML
    */
    @GetMapping({"/", "/list", ""})
    public String showList(Model model) {

        //Envía a la vista el total de elementos y el conjunto de números
        model.addAttribute("cantidadTotal", lista.size());
        model.addAttribute("listaNumeros", lista);

        /*
        Devuelve el nombre de la plantilla que procesará
        y renderizará la información
        enviada en el objeto model
        */
        return "listView";
    }

    //Responde a peticiones GET enviadas a la URL /new
    @GetMapping("/new")
    public String showNew() {

        boolean añadido;

        do {
            añadido = lista.add(random.nextInt(100) + 1);
        } while (!añadido);

        /*
        Realiza una redirección HTTP a la ruta /list, actualizando
        la vista con el nuevo elemento insertado
        */
        return "redirect:/list";
    }

    /*
    Captura la URL dinámica /delete/{id},
    donde id representa el número que se desea eliminar
    */
    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable Integer id) {

        lista.remove(id);

        return "redirect:/list";
    }
}