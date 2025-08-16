package org.example;

public class Ejemplar {
    // atributos
    private int idEjemplar;
    private Libro libro;
    private Estado estado;

    // constructor
    public Ejemplar(int idEjemplar, Libro libro, Estado estado){
        this.idEjemplar = idEjemplar;
        this.libro = libro;
        this.estado = estado;
    }

    public int getIdEjemplar() {
        return idEjemplar;
    }

    public Estado getEstado() {
        return estado;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void showInfo(){
        System.out.println("-> id Prestamo: " + idEjemplar);
        System.out.println("-> Libro: " + libro.getTitulo());
        System.out.println(" -> Estado: " + estado.name());
    }
}
