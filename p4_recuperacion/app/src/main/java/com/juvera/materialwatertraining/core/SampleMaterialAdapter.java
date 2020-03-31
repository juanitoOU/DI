package com.juvera.materialwatertraining.core;


import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.juvera.materialwatertraining.R;
import com.juvera.materialwatertraining.bd.DBManager;
import com.juvera.materialwatertraining.view.MainActivity;
import com.juvera.materialwatertraining.view.TransitionAddActivity;

import java.util.ArrayList;
import java.util.List;


public class SampleMaterialAdapter extends RecyclerView.Adapter<SampleMaterialAdapter.ViewHolder> {
    private static final String DEBUG_TAG = "SampleMaterialAdapter";

    public Context context;
    public ArrayList<Entrenamiento> listaEntrenamientos;
    private DBManager gestorDB;


    public SampleMaterialAdapter(Context context, ArrayList<Entrenamiento> listaEntrenamientos) {
        this.context = context;
        this.listaEntrenamientos = listaEntrenamientos;
        this.gestorDB = new DBManager(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View v = li.inflate(R.layout.lvlista_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleMaterialAdapter.ViewHolder viewHolder, int position) {


            String nombre = listaEntrenamientos.get(position).getNombre();
            TextView nombreTextView = viewHolder.nombre;
            nombreTextView.setText(nombre);

            String fecha =listaEntrenamientos.get(position).getFecha();
            TextView fechaTextView = viewHolder.fecha;
            fechaTextView.setText(fecha);

            float segundos = listaEntrenamientos.get(position).getSegundos();
            float minutos = listaEntrenamientos.get(position).getMinutos();
            float horas = listaEntrenamientos.get(position).getHoras();
            String duracion = horas + "h " + minutos + "m " + segundos +"seg ";
            TextView duracionTextView = viewHolder.duracion;
            duracionTextView.setText(duracion);

            float kilometros = listaEntrenamientos.get(position).getKilometros();
            float metros = listaEntrenamientos.get(position).getMetros();
            String distancia = kilometros + "km " + metros+ "m.";
            TextView distanciaTextView = viewHolder.distancia;
            distanciaTextView.setText(distancia);

            String tipo =listaEntrenamientos.get(position).getTipo();
            TextView tipoTextView = viewHolder.tipo;
            tipoTextView.setText(tipo);


        }




    public void addEntrenamiento(Entrenamiento entrenamiento) {
        listaEntrenamientos.add(entrenamiento);
        ((MainActivity) context).doSmoothScroll(getItemCount());
        notifyItemInserted(getItemCount());
    }
    public void updateEntrenamiento(Entrenamiento entrenamiento) {

        int pos=-1;
        for(int i=0;i<listaEntrenamientos.size();i++){
            System.out.println(listaEntrenamientos.get(i).getNombre() + ": " + listaEntrenamientos.get(i).getId());
            if(listaEntrenamientos.get(i).getId()==entrenamiento.getId()){
                pos=i;
            }
        }
        listaEntrenamientos.set(pos, entrenamiento);
        notifyItemChanged(pos);
    }
    public void deleteCard(View view, int list_position){
        delete(view, list_position);
    }
    public void delete(final View view, final int list_position){
        gestorDB.eliminaItem(listaEntrenamientos.get(list_position).getId());
        listaEntrenamientos.remove(list_position);
        notifyItemRemoved(list_position);
    }



    @Override
    public int getItemCount() {
        if (listaEntrenamientos.isEmpty()) {
            return 0;
        } else {
            return listaEntrenamientos.size();
        }
    }

    @Override
    public long getItemId(int position){
        return listaEntrenamientos.get(position).getId();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        private TextView nombre;
        private TextView fecha;
        private TextView duracion;
        private TextView distancia;
        private TextView tipo;

        public ViewHolder(View view) {
            super(view);

            view.setOnCreateContextMenuListener(this);
            nombre = (TextView) view.findViewById(R.id.cardNombre);
            fecha = (TextView) view.findViewById(R.id.cardFecha);
            duracion = (TextView) view.findViewById(R.id.cardDuracion);
            distancia = (TextView) view.findViewById(R.id.cardDistancia);
            tipo = (TextView) view.findViewById(R.id.cardTipo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int requestCode = getAdapterPosition();
                    Intent subactividad = new Intent((AppCompatActivity) context, TransitionAddActivity.class);
                    subactividad.putExtra("id", listaEntrenamientos.get(requestCode).getId());
                    ((AppCompatActivity) context).startActivityForResult(subactividad, requestCode);
                }
            });
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()){
                case 1:
                    delete(itemView, getAdapterPosition());
                    break;
                case 2:
                    int requestCode = getAdapterPosition();
                    Intent subactividad = new Intent(context, TransitionAddActivity.class);
                    subactividad.putExtra("id", listaEntrenamientos.get(requestCode).getId());
                    ((AppCompatActivity) context).startActivityForResult(subactividad, requestCode);
                    break;

            }
            return true;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem borrar = menu.add(Menu.NONE, 1, 1, "Borrar");
            borrar.setOnMenuItemClickListener(this);
            MenuItem modificar = menu.add(Menu.NONE, 2, 2,"Modificar");
            modificar.setOnMenuItemClickListener(this);
        }
    }

}


