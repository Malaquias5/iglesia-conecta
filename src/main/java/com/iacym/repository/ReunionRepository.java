package com.iacym.repository;

import com.iacym.entity.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReunionRepository extends JpaRepository<Reunion, Long> {

    List<Reunion> findByActivaTrueOrderByDiaSemanaAscHoraInicioAsc();
}