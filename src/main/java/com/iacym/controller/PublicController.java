package com.iacym.controller;

import com.iacym.service.EventoService;
import com.iacym.service.ReunionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    private final ReunionService reunionService;
    private final EventoService eventoService;

    public PublicController(ReunionService reunionService, EventoService eventoService) {
        this.reunionService = reunionService;
        this.eventoService = eventoService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("reuniones", reunionService.listarActivas());
        model.addAttribute("eventos", eventoService.listarPublicos());
        return "public/inicio";
    }
}