package com.example.gestioncitas.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente extends Usuario {
    private String especialidad;
    private String tipoCita;
    private LocalDate fecha;
    private boolean sms;
    private boolean email;
    private boolean interprete;

    public Cliente(String nombre, String dni, String especialidad, String tipoCita,
                   LocalDate fecha, boolean sms, boolean email, boolean interprete) {
        super(nombre, dni);
        this.especialidad = especialidad;
        this.tipoCita = tipoCita;
        this.fecha = fecha;
        this.sms = sms;
        this.email = email;
        this.interprete = interprete;
    }

    public String getResumenCita() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "INFORMACIÓN DE LA CITA\n" +
                "----------------------------------\n" +
                "Paciente: " + nombre + "\n" +
                "DNI: " + dni + "\n" +
                "Especialidad: " + especialidad + "\n" +
                "Tipo: " + tipoCita + "\n" +
                "Fecha: " + (fecha != null ? fecha.format(formato) : "") + "\n" +
                "Servicios adicionales:\n" +
                (sms ? "  ✓ SMS\n" : "  ✗ SMS\n") +
                (email ? "  ✓ Email\n" : "  ✗ Email\n") +
                (interprete ? "  ✓ Intérprete" : "  ✗ Intérprete");
    }

    @Override
    public String toString() {
        return nombre + " - " + especialidad;
    }
}