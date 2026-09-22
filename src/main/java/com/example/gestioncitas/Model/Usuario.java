package com.example.gestioncitas.Model;

public class Usuario {
    protected String nombre;
    protected String dni;

    public Usuario(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
}