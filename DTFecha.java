package org.example;

public class DTFecha {
    private int dia;
    private int mes;
    private int ano;

    public DTFecha(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getAno() {
        return ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }
}
