package com.iacym.repository;

import com.iacym.entity.HistorialAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface HistorialAlertaRepository extends JpaRepository<HistorialAlerta, Long> {

    boolean existsByHermanoIdAndFechaCumpleanos(Long hermanoId, LocalDate fechaCumpleanos);

    List<HistorialAlerta> findAllByOrderByFechaEnvioDesc();
}