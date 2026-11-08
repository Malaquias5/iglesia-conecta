package com.iacym.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_alertas",
       uniqueConstraints = @UniqueConstraint(columnNames = {"hermano_id", "fecha_cumpleanos"}))
public class HistorialAlerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hermano_id", nullable = false)
    private Long hermanoId;

    @Column(name = "fecha_cumpleanos", nullable = false)
    private LocalDate fechaCumpleanos;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    @Column(name = "tipo_alerta", nullable = false)
    private String tipoAlerta = "CUMPLEANOS_2_DIAS";

    @Column(name = "estado_envio", nullable = false)
    private String estadoEnvio;

    @Column(name = "mensaje_error", length = 1000)
    private String mensajeError;

    public Long getId() { return id; }
    public Long getHermanoId() { return hermanoId; }
    public void setHermanoId(Long hermanoId) { this.hermanoId = hermanoId; }
    public LocalDate getFechaCumpleanos() { return fechaCumpleanos; }
    public void setFechaCumpleanos(LocalDate fechaCumpleanos) { this.fechaCumpleanos = fechaCumpleanos; }
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }
    public String getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(String estadoEnvio) { this.estadoEnvio = estadoEnvio; }
    public String getMensajeError() { return mensajeError; }
    public void setMensajeError(String mensajeError) { this.mensajeError = mensajeError; }
}