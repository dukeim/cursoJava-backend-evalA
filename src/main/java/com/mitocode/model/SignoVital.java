package com.mitocode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "signo_vital")
public class SignoVital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSignoVital;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "temperatura", nullable = false)
    private String temperatura;

    @Column(name = "pulso", nullable = false)
    private String pulso;

    @Column(name = "ritmo_resp", nullable = false)
    private String ritmoRespiratorio;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_signo_vital_paciente"))
    private Paciente paciente;

    public Integer getIdSignoVital() {
        return idSignoVital;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setIdSignoVital(Integer idSignoVital) {
        this.idSignoVital = idSignoVital;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getRitmoRespiratorio() {
        return ritmoRespiratorio;
    }

    public void setRitmoRespiratorio(String ritmoRespiratorio) {
        this.ritmoRespiratorio = ritmoRespiratorio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
