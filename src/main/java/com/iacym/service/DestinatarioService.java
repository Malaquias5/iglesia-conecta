package com.iacym.service;

import com.iacym.entity.DestinatarioAlerta;
import com.iacym.repository.DestinatarioAlertaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinatarioService {

    private final DestinatarioAlertaRepository repository;

    public DestinatarioService(DestinatarioAlertaRepository repository) {
        this.repository = repository;
    }

    public List<DestinatarioAlerta> listar() {
        return repository.findAll();
    }

    public void guardar(DestinatarioAlerta d) {
        repository.save(d);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}