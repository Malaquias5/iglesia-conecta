package com.iacym.controller;

import com.iacym.entity.Reunion;
import com.iacym.service.EventoService;
import com.iacym.service.ReunionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
        List<Reunion> reuniones = reunionService.listarActivas();

        Map<String, List<Reunion>> agenda = new LinkedHashMap<>();
        for (Reunion r : reuniones) {
            String dia = r.getDiaSemana().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));
            agenda.computeIfAbsent(dia, k -> new ArrayList<>()).add(r);
        }

        model.addAttribute("reuniones", reuniones);
        model.addAttribute("agenda", agenda);
        model.addAttribute("eventos", eventoService.listarPublicos());
        return "public/inicio";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}