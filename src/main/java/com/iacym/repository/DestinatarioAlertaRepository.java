package com.iacym.repository;

import com.iacym.entity.DestinatarioAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DestinatarioAlertaRepository extends JpaRepository<DestinatarioAlerta, Long> {

    List<DestinatarioAlerta> findByActivoTrue();
}