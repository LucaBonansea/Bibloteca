package org.example;

public class Autor {
    // Atributos
    private int codigo;
    private String nombre;
    private String nacionalidad;
    private DTFecha Fecha_nec;


    public Autor(int codigo, String nombre, String nacionalidad, DTFecha Fecha_nac) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.Fecha_nec = Fecha_nac;
    }

    public void showInfo() {
        System.out.println("-> Codigo: " + codigo);
        System.out.println("-> Nombre: " + nombre);
        System.out.println("-> Nacionalidad: " + nacionalidad);
        System.out.println("-> Fecha Nacimiento: " + Fecha_nec);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;}

    public void setCodigo(int codigo) {
        this.codigo = codigo;}

    public void setFecha_nec(DTFecha fecha_nec) {}

    public int getCodigo() {
        return codigo;}

    public String getNombre() {
        return nombre;}

    public DTFecha getFecha_nec() {
        return Fecha_nec;}

    public String getNacionalidad() {
        return nacionalidad;}

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;}

    public Object getFechaNac() {
        return Fecha_nec;
    }
}
