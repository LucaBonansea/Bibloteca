package org.example;

import java.util.ArrayList;

public class Bibloteca {
    // atributos
    private ArrayList<Libro> Catagolo;
    private ArrayList<Prestamo> Prestamo;

    public Bibloteca(ArrayList<Libro> Catagolo, ArrayList<Prestamo> Prestamo){
        this.Catagolo = Catagolo;
        this.Prestamo = Prestamo;
    }

    public ArrayList<Libro> getCatagolo() {
        return Catagolo;
    }

    public ArrayList<Prestamo> getPrestamo() {
        return Prestamo;
    }
}
