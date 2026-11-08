package com.iacym.repository;

import com.iacym.entity.Hermano;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HermanoRepository extends JpaRepository<Hermano, Long> {

    List<Hermano> findByActivoTrueOrderByApellidosAsc();
    List<Hermano> findByActivoTrueAndRecordatorioActivoTrue();
}