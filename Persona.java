package org.example;

public class Persona {
    // Atributos

    private boolean habilitado;
    private String cedula;

    // constructor
    public Persona(boolean habilitado, String cedula){
        this.habilitado = habilitado;
        this.cedula = cedula;
    }

    // metodos set
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    // metodos get
    public String getCedula() {
        return cedula;
    }


    public void showInfo(){
        System.out.println("➜ Cedula: " + cedula);
        System.out.println("➜ Habilitado: " + habilitado);
    }
}
