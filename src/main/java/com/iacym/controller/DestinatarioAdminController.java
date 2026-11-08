package com.iacym.controller;

import com.iacym.entity.DestinatarioAlerta;
import com.iacym.service.DestinatarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/destinatarios")
public class DestinatarioAdminController {

    private final DestinatarioService service;

    public DestinatarioAdminController(DestinatarioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("destinatarios", service.listar());
        model.addAttribute("destinatario", new DestinatarioAlerta());
        return "admin/destinatarios";
    }

    @PostMapping
    public String guardar(@ModelAttribute DestinatarioAlerta destinatario) {
        service.guardar(destinatario);
        return "redirect:/admin/destinatarios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/admin/destinatarios";
    }
}