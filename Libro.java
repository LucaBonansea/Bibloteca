package org.example;

import java.util.ArrayList;

public class Libro {
    // Atributos
    private String titulo;
    private Genero genero;
    private String Editorial;
    private DTFecha FechaPub;
    private String ISBN;
    private ArrayList<Autor> Autores;
    private ArrayList<Ejemplar> Ejemplarse;
    // constructor
    public Libro(String titulo, Genero genero, String Editorial, DTFecha FechaPub, String ISBN, ArrayList<Autor> Autores, ArrayList<Ejemplar> Ejemplarse){
        this.titulo = titulo;
        this.genero = genero;
        this.Editorial = Editorial;
        this.FechaPub = FechaPub;
        this.ISBN = ISBN;
        this.Autores = Autores;
        this.Ejemplarse = Ejemplarse;
    }

    public void showInfo(){
        System.out.println("Libro: ");
        System.out.println(" | Titulo: " + titulo);
        System.out.println(" | Genero: " + genero.name());
        System.out.println(" | Editorial: " + Editorial);
        System.out.println(" | ISBN: " + ISBN);
    }

    public Genero getGenero() {
        return genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public DTFecha getFechaPub() {
        return FechaPub;
    }

    public String getEditorial() {
        return Editorial;
    }

    public String getISBN() {
        return ISBN;
    }

    public ArrayList<Autor> getAutores() {
        return Autores;
    }

    public ArrayList<Ejemplar> getEjemplarse() {
        return Ejemplarse;
    }
}
