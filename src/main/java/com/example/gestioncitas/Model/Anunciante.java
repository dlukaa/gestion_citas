package com.example.gestioncitas.Model;


public class Anunciante extends Usuario {
    private String campania;

    public Anunciante(String nombre, String dni, String campania) {
        super(nombre, dni);
        this.campania = campania;
    }

    public String getCampania() {
        return campania;
    }
}