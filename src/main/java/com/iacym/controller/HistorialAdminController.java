package com.iacym.controller;

import com.iacym.entity.Hermano;
import com.iacym.repository.HermanoRepository;
import com.iacym.repository.HistorialAlertaRepository;
import com.iacym.service.CumpleanosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/historial")
public class HistorialAdminController {

    private final HistorialAlertaRepository historial;
    private final HermanoRepository hermanos;
    private final CumpleanosService cumpleanosService;

    public HistorialAdminController(HistorialAlertaRepository historial,
                                    HermanoRepository hermanos,
                                    CumpleanosService cumpleanosService) {
        this.historial = historial;
        this.hermanos = hermanos;
        this.cumpleanosService = cumpleanosService;
    }

    @GetMapping
    public String ver(Model model) {
        Map<Long, String> nombres = new HashMap<>();
        for (Hermano h : hermanos.findAll()) {
            nombres.put(h.getId(), h.getNombres() + " " + h.getApellidos());
        }
        model.addAttribute("alertas", historial.findAllByOrderByFechaEnvioDesc());
        model.addAttribute("nombres", nombres);
        return "admin/historial";
    }

    @PostMapping("/ejecutar")
    public String ejecutar() {
        cumpleanosService.enviarRecordatorios();
        return "redirect:/admin/historial";
    }
}