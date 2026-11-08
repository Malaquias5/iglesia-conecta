package com.iacym.service;

import com.iacym.entity.Reunion;
import com.iacym.repository.ReunionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReunionService {

    private final ReunionRepository repository;

    public ReunionService(ReunionRepository repository) {
        this.repository = repository;
    }

    public List<Reunion> listarActivas() {
        return repository.findByActivaTrueOrderByDiaSemanaAscHoraInicioAsc().stream()
            .sorted(java.util.Comparator
                .comparing((Reunion r) -> r.getDiaSemana().getValue() % 7)
                .thenComparing(Reunion::getHoraInicio))
            .toList();
    }
    
    public List<Reunion> listarTodas() {
        return repository.findAll();
    }

    public void guardar(Reunion reunion) {
        repository.save(reunion);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    }