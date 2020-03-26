package com.juvera.materialwatertraining.view;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.juvera.materialwatertraining.bd.DBManager;
import com.juvera.materialwatertraining.core.Entrenamiento;

import java.util.ArrayList;


public class SampleBDAdapter extends SampleMaterialAdapter {

    protected Cursor c;
    DBManager db = new DBManager(context);

    public SampleBDAdapter(Context context, ArrayList<Entrenamiento> entrenamientoList, Cursor c) {
        super(context, entrenamientoList);
        this.c = c;
    }

    public Cursor getCursor() {
        return c;
    }

    public void setCursor(Cursor c) {
        this.c = c;
    }


    @Override
    public void addEntrenamiento(String nombre, String fecha,float segundos, float minutos, float horas, float kilometros, float metros, String tipo) {
       if(db.insertaItem(nombre, fecha, segundos, minutos, horas, kilometros, metros, tipo)){
           ArrayList<Entrenamiento> entrenamientoList = getAllEntrenamientos();
           Entrenamiento e = new Entrenamiento(nombre, fecha, segundos, minutos, horas, kilometros, metros, tipo);
           entrenamientoList.add(e);
           super.addEntrenamiento(e.getNombre(), e.getFecha(), e.getSegundos(), e.getMinutos(), e.getHoras(), e.getKilometros(), e.getMetros(), e.getTipo());
       }
         else{
            ///MENSAJE DE ERROR
           System.exit(-1);
       }


    }

    @Override
    public void updateEntrenamiento( int id, String nombre, String fecha, float segundos, float minutos, float horas, float kilometros, float metros, String tipo, int list_position) {
        entrenamientoList.get(list_position).setNombre(nombre);
        entrenamientoList.get(list_position).setFecha(fecha);
        entrenamientoList.get(list_position).setSegundos(segundos);
        entrenamientoList.get(list_position).setMinutos(minutos);
        entrenamientoList.get(list_position).setHoras(horas);
        entrenamientoList.get(list_position).setKilometros(kilometros);
        entrenamientoList.get(list_position).setMetros(metros);
        entrenamientoList.get(list_position).setTipo(tipo);
         id= list_position + 1;
        db.editaItem(id, nombre, fecha, segundos, minutos, horas, kilometros, metros, tipo);
        super.updateEntrenamiento(id, nombre, fecha, segundos, minutos, horas, kilometros,metros,  tipo, list_position);
    }

    @Override
    public void deleteEntrenamiento(View view, int list_position) {

        ArrayList<Entrenamiento> entrenamientoList = getAllEntrenamientos();
       entrenamientoList.remove(list_position);

       int id = list_position +1;
        db.eliminaItem(id);
        super.deleteEntrenamiento(view, list_position);
    }

    @Override
    public ArrayList<Entrenamiento> getAllEntrenamientos() {
        ArrayList<Entrenamiento> entrenamientoList = new ArrayList<>();
       c = db.getEntrenamientos();
        while(c.moveToNext()) {
            entrenamientoList.add(new Entrenamiento(c.getString(c.getColumnIndex("nombre")), c.getString(c.getColumnIndex("fecha")),c.getFloat(c.getColumnIndex("segundos")), c.getFloat(c.getColumnIndex("minutos")), c.getFloat(c.getColumnIndex("horas")), c.getFloat(c.getColumnIndex("kilometros")), c.getFloat(c.getColumnIndex("metros")), c.getString(c.getColumnIndex("tipo"))));
        }
        c.close();
        return super.getAllEntrenamientos();
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public long getItemId(int position) {

        return super.getItemId(position);
    }

}
