package com.juvera.materialwatertraining.core;

import java.text.DecimalFormat;

public class Entrenamiento {

    private String nombre;
    private String fecha;
    private int segundos;
    private int minutos;
    private int horas;
    private int kilometros;
    private int metros;
    private String tipo;
    private int color_resource;
    private  int id;

    public Entrenamiento( int id,String nombre, String fecha, int segundos, int minutos, int horas, int kilometros, int metros, String tipo){
        this.id=id;
        this.nombre = nombre;
        this.segundos = segundos;
        this.minutos = minutos;
        this.horas = horas;
        this.kilometros = kilometros;
        this.metros=metros;
        this.tipo=tipo;
    }

    public Entrenamiento(){}

    DecimalFormat formato = new DecimalFormat("#0.00");
    public int getKmHour (){
        return this.kilometros/(this.horas);
    }
    public int getMSeg (){
        return this.metros/(this.segundos);
    }
    public int getTotalKm (){
        return this.kilometros + this.metros/1000;
    }
    public int getTotalM (){ return this.metros + this.kilometros*1000; }
    public int getTotalTime (){
        return this.horas + this.minutos/60 + this.segundos/60;
    }

    @Override
    public String toString(){
        return "Entrenamiento = " +nombre + " fecha " + fecha ;
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

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
