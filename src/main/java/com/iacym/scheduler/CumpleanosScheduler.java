package com.iacym.scheduler;

import com.iacym.service.CumpleanosService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CumpleanosScheduler {

    private final CumpleanosService service;

    public CumpleanosScheduler(CumpleanosService service) {
        this.service = service;
    }

    @Scheduled(cron = "0 0 8 * * *", zone = "America/Lima")
    public void revisar() {
        service.enviarRecordatorios();
    }
}