package com.iacym.service;

import com.iacym.entity.DestinatarioAlerta;
import com.iacym.entity.Hermano;
import com.iacym.entity.HistorialAlerta;
import com.iacym.repository.DestinatarioAlertaRepository;
import com.iacym.repository.HermanoRepository;
import com.iacym.repository.HistorialAlertaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
public class CumpleanosService {

    private static final ZoneId ZONA = ZoneId.of("America/Lima");
    private static final DateTimeFormatter FORMATO =
        DateTimeFormatter.ofPattern("d 'de' MMMM", Locale.forLanguageTag("es"));

    private final HermanoRepository hermanos;
    private final DestinatarioAlertaRepository destinatarios;
    private final HistorialAlertaRepository historial;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String remitente;

    public CumpleanosService(HermanoRepository hermanos,
                             DestinatarioAlertaRepository destinatarios,
                             HistorialAlertaRepository historial,
                             JavaMailSender mailSender) {
        this.hermanos = hermanos;
        this.destinatarios = destinatarios;
        this.historial = historial;
        this.mailSender = mailSender;
    }

    public void enviarRecordatorios() {
        LocalDate objetivo = LocalDate.now(ZONA).plusDays(2);

        String[] correos = destinatarios.findByActivoTrue().stream()
            .map(DestinatarioAlerta::getCorreo)
            .toArray(String[]::new);
        if (correos.length == 0) return;

        List<Hermano> lista = hermanos.findByActivoTrueAndRecordatorioActivoTrue();
        for (Hermano h : lista) {
            // atYear ajusta el 29 de febrero a 28 en años no bisiestos
            LocalDate cumple = MonthDay.from(h.getFechaNacimiento()).atYear(objetivo.getYear());
            if (!cumple.equals(objetivo)) continue;
            if (historial.existsByHermanoIdAndFechaCumpleanos(h.getId(), cumple)) continue;

            HistorialAlerta registro = new HistorialAlerta();
            registro.setHermanoId(h.getId());
            registro.setFechaCumpleanos(cumple);
            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                if (!remitente.isBlank()) msg.setFrom(remitente);
                msg.setTo(correos);
                msg.setSubject("Recordatorio: Próximo cumpleaños de un hermano");
                msg.setText("""
                    ¡Hola, equipo de liderazgo!

                    Les recordamos que dentro de dos días es el cumpleaños de uno de nuestros hermanos de la congregación: %s %s.

                    Es una buena oportunidad para organizarnos y preparar una pequeña celebración, compartir un momento especial o tener un detalle con esta persona.

                    Fecha del cumpleaños: %s.

                    ¡Bendiciones!
                    """.formatted(h.getNombres(), h.getApellidos(), cumple.format(FORMATO)));
                mailSender.send(msg);
                registro.setEstadoEnvio("ENVIADO");
            } catch (Exception e) {
                registro.setEstadoEnvio("ERROR");
                String error = String.valueOf(e.getMessage());
                registro.setMensajeError(error.length() > 900 ? error.substring(0, 900) : error);
            }
            historial.save(registro);
        }
    }
}