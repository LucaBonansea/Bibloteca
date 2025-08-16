package org.example;

public class Prestamo {

    // Atributos nuevos
    private String fecha_entrega;
    private String fecha_emision;
    private boolean multa;
    private int id_libro;


    // constructor
    public Prestamo(String fecha_entrega, String fecha_emision, boolean multa, int id_libro){
        this.fecha_entrega = fecha_entrega;
        this.fecha_emision = fecha_emision;
        this.multa = multa;
        this.id_libro = id_libro;
    }

    // metodos
    public void setFecha_entrega(String fecha) {
        this.fecha_entrega = fecha;
    }



    // getters
    public String getFecha_entrega() {
        return fecha_entrega;
    }
    public String getFecha_emision() {
        return fecha_emision;
    }


    public void showInfo(){
        System.out.println("➜ Fecha entrega: " + fecha_entrega);
        System.out.println("➜ Fecha Emision: " + fecha_emision);
        System.out.println("➜ Multado: " + multa);
    }


    public int getId_libro() {
        return id_libro;
    }
}
