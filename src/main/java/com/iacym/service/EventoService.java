package com.iacym.service;

import com.iacym.entity.Evento;
import com.iacym.repository.EventoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public List<Evento> listarPublicos() {
        return repository.findByEstadoInOrderByFechaInicioAsc(
            List.of(Evento.Estado.PROXIMO, Evento.Estado.PUBLICADO));
    }
    public List<Evento> listarTodos() {
        return repository.findAll();
    }

    public void guardar(Evento evento) {
        repository.save(evento);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}