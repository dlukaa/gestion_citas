package com.example.gestioncitas.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Cita {
    private String nombre;
    private String dni;
    private String especialidad;
    private String tipo;
    private LocalDate fecha;
    private List<String> servicios;

    public Cita(String nombre, String dni, String especialidad, String tipo, LocalDate fecha, List<String> servicios) {
        this.nombre = nombre;
        this.dni = dni;
        this.especialidad = especialidad;
        this.tipo = tipo;
        this.fecha = fecha;
        this.servicios = servicios;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getEspecialidad() { return especialidad; }
    public String getTipo() { return tipo; }
    public LocalDate getFecha() { return fecha; }
    public List<String> getServicios() { return servicios; }

    // Lo que se muestra en cada fila del ListView (ej: "María García - Dermatología")
    @Override
    public String toString() {
        return nombre + " - " + especialidad;
    }

    // Formato exacto que pide el PDF para el TextArea de Información
    public String getResumenCita() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaStr = (fecha != null) ? fecha.format(formatoFecha) : "Sin fecha";

        boolean tieneSms = servicios.contains("SMS");
        boolean tieneEmail = servicios.contains("Email");
        boolean tieneInterprete = servicios.contains("Intérprete");

        return "INFORMACIÓN DE LA CITA\n" +
                "------------------------------------\n" +
                "Paciente: " + nombre + "\n" +
                "DNI: " + dni + "\n" +
                "Especialidad: " + especialidad + "\n" +
                "Tipo: " + tipo + "\n" +
                "Fecha: " + fechaStr + "\n" +
                "Servicios adicionales:\n" +
                (tieneSms ? "  [✓] SMS\n" : "  [✗] SMS\n") +
                (tieneEmail ? "  [✓] Email\n" : "  [✗] Email\n") +
                (tieneInterprete ? "  [✓] Intérprete" : "  [✗] Intérprete");
    }
}