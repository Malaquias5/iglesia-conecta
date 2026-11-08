package com.iacym.controller;

import com.iacym.entity.Evento;
import com.iacym.service.EventoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/eventos")
public class EventoAdminController {

    private final EventoService eventoService;

    public EventoAdminController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("eventos", eventoService.listarTodos());
        model.addAttribute("evento", new Evento());
        model.addAttribute("estados", Evento.Estado.values());
        return "admin/eventos";
    }

    @PostMapping
    public String guardar(@ModelAttribute Evento evento) {
        eventoService.guardar(evento);
        return "redirect:/admin/eventos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        eventoService.eliminar(id);
        return "redirect:/admin/eventos";
    }
}