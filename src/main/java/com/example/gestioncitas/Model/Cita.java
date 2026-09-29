package com.example.gestioncitas.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Cita {
    private Paciente paciente;
    private String especialidad;
    private String tipo;
    private LocalDate fecha;
    private List<String> servicios;

    public Cita(Paciente paciente, String especialidad, String tipo, LocalDate fecha, List<String> servicios) {
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.tipo = tipo;
        this.fecha = fecha;
        this.servicios = servicios;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<String> getServicios() {
        return servicios;
    }

    // Texto visible en el ListView
    @Override
    public String toString() {
        return paciente.getNombre() + " - " + especialidad;
    }

    // Formato exacto que pide el ejercicio para el TextArea
    public String getResumenDetalle() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "INFORMACIÓN DE LA CITA\n"
                + "Paciente: " + paciente.getNombre() + "\n"
                + "DNI: " + paciente.getDni() + "\n"
                + "Especialidad: " + especialidad + "\n"
                + "Tipo: " + tipo + "\n"
                + "Fecha: " + (fecha != null ? fecha.format(formato) : "") + "\n"
                + "Servicios adicionales:\n"
                + (servicios.contains("SMS") ? "✓ SMS\n" : "✗ SMS\n")
                + (servicios.contains("Email") ? "✓ Email\n" : "✗ Email\n")
                + (servicios.contains("Interprete") ? "✓ Intérprete" : "✗ Intérprete");
    }
}