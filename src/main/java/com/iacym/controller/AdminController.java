package com.iacym.controller;

import com.iacym.entity.Reunion;
import com.iacym.repository.HistorialAlertaRepository;
import com.iacym.service.EventoService;
import com.iacym.service.HermanoService;
import com.iacym.service.ReunionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.DayOfWeek;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ReunionService reunionService;
    private final EventoService eventoService;
    private final HermanoService hermanoService;
    private final HistorialAlertaRepository historial;

    public AdminController(ReunionService reunionService, EventoService eventoService,
                           HermanoService hermanoService, HistorialAlertaRepository historial) {
        this.reunionService = reunionService;
        this.eventoService = eventoService;
        this.hermanoService = hermanoService;
        this.historial = historial;
    }

    @GetMapping
    public String panel(Model model) {
        model.addAttribute("totalReuniones", reunionService.listarActivas().size());
        model.addAttribute("totalEventos", eventoService.listarPublicos().size());
        model.addAttribute("totalHermanos", hermanoService.listar().size());
        model.addAttribute("cumpleanos", hermanoService.proximosCumpleanos(30));
        model.addAttribute("alertas", historial.findAllByOrderByFechaEnvioDesc().stream().limit(5).toList());
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