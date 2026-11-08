package com.iacym.service;

import com.iacym.entity.Hermano;
import com.iacym.repository.HermanoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.util.Comparator;

@Service
public class HermanoService {

    private final HermanoRepository repository;

    public HermanoService(HermanoRepository repository) {
        this.repository = repository;
    }

    public List<Hermano> listar() {
        return repository.findByActivoTrueOrderByApellidosAsc();
    }

    public void guardar(Hermano hermano) {
        repository.save(hermano);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    public record ProximoCumple(String nombre, LocalDate fecha) {}

    public List<ProximoCumple> proximosCumpleanos(int dias) {
        LocalDate hoy = LocalDate.now(ZoneId.of("America/Lima"));
        LocalDate limite = hoy.plusDays(dias);
        return repository.findByActivoTrueOrderByApellidosAsc().stream()
            .map(h -> {
                MonthDay md = MonthDay.from(h.getFechaNacimiento());
                LocalDate f = md.atYear(hoy.getYear());
                if (f.isBefore(hoy)) f = md.atYear(hoy.getYear() + 1);
                return new ProximoCumple(h.getNombres() + " " + h.getApellidos(), f);
            })
            .filter(c -> !c.fecha().isAfter(limite))
            .sorted(Comparator.comparing(ProximoCumple::fecha))
            .toList();
    }
}