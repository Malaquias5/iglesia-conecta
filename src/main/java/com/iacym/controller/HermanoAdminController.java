package com.iacym.controller;

import com.iacym.entity.Hermano;
import com.iacym.service.HermanoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/hermanos")
public class HermanoAdminController {

    private final HermanoService hermanoService;

    public HermanoAdminController(HermanoService hermanoService) {
        this.hermanoService = hermanoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("hermanos", hermanoService.listar());
        model.addAttribute("hermano", new Hermano());
        return "admin/hermanos";
    }

    @PostMapping
    public String guardar(@ModelAttribute Hermano hermano) {
        hermanoService.guardar(hermano);
        return "redirect:/admin/hermanos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        hermanoService.eliminar(id);
        return "redirect:/admin/hermanos";
    }
}