package com.juvera.materialwatertraining.core;

public class Entrenamiento {

    private String nombre;
    private String fecha;
    private float segundos;
    private float minutos;
    private float horas;
    private float kilometros;
    private float metros;
    private String tipo;
    private int color_resource;
    private  int id;

    public Entrenamiento( String nombre, String fecha, float segundos, float minutos, float horas, float kilometros, float metros, String tipo){

    }

    public float getKmHour (){
        return this.kilometros/(this.horas);
    }
    public float getMSeg (){
        return this.metros/(this.segundos);
    }

    @Override
    public String toString(){
        return "Distancia = " + this.kilometros + " Km " + this.metros + " m y Tiempo = " + this.horas + " horas " + this.minutos + " minutos" + this.segundos + "seg ";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getSegundos() {
        return segundos;
    }

    public void setSegundos(float segundos) {
        this.segundos = segundos;
    }

    public float getMinutos() {
        return minutos;
    }

    public void setMinutos(float minutos) {
        this.minutos = minutos;
    }

    public float getHoras() {
        return horas;
    }

    public void setHoras(float horas) {
        this.horas = horas;
    }

    public float getKilometros() {
        return kilometros;
    }

    public void setKilometros(float kilometros) {
        this.kilometros = kilometros;
    }

    public float getMetros() {
        return metros;
    }

    public void setMetros(float metros) {
        this.metros = metros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getColorResource() {
        return color_resource;
    }

    public void setColorResource(int color_resource) {
        this.color_resource = color_resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
