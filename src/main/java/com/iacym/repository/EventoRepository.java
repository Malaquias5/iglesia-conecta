package com.iacym.repository;

import com.iacym.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByEstadoInOrderByFechaInicioAsc(List<Evento.Estado> estados);
    
}