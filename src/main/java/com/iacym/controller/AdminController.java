package com.iacym.controller;

import com.iacym.entity.Reunion;
import com.iacym.service.ReunionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.DayOfWeek;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ReunionService reunionService;

    public AdminController(ReunionService reunionService) {
        this.reunionService = reunionService;
    }

    @GetMapping
    public String panel() {
        return "admin/panel";
    }

    @GetMapping("/reuniones")
    public String reuniones(Model model) {
        model.addAttribute("reuniones", reunionService.listarTodas());
        model.addAttribute("reunion", new Reunion());
        model.addAttribute("dias", DayOfWeek.values());
        return "admin/reuniones";
    }

    @PostMapping("/reuniones")
    public String guardar(@ModelAttribute Reunion reunion) {
        reunionService.guardar(reunion);
        return "redirect:/admin/reuniones";
    }

    @PostMapping("/reuniones/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        reunionService.eliminar(id);
        return "redirect:/admin/reuniones";
    }
}