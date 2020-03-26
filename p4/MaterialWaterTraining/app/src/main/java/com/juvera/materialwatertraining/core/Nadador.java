package com.juvera.materialwatertraining.core;

public class Nadador {

    private String nombre;
    private int edad;
    private String nacionalidad;

    @Override
    public String toString(){
        return "Nombre = " + this.nombre + " Edad = " + this.edad + " Nacionalidad" + this.nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
