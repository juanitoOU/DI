package com.juvera.materialwatertraining.view;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.juvera.materialwatertraining.R;
import com.juvera.materialwatertraining.bd.DBManager;
import com.juvera.materialwatertraining.core.Entrenamiento;

import java.util.ArrayList;


public class SampleMaterialAdapter extends RecyclerView.Adapter<SampleMaterialAdapter.ViewHolder> {
    private static final String DEBUG_TAG = "SampleMaterialAdapter";

    public Context context;
    public ArrayList<Entrenamiento> entrenamientoList;



    public SampleMaterialAdapter(Context context,  ArrayList<Entrenamiento> entrenamientoList) {
        this.context = context;
        this.entrenamientoList = entrenamientoList;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String nombre = entrenamientoList.get(position).getNombre();
        String fecha = entrenamientoList.get(position).getFecha();
        String tipo = entrenamientoList.get(position).getTipo();
        String segundos = String.valueOf(entrenamientoList.get(position).getSegundos());
        String minutos = String.valueOf(entrenamientoList.get(position).getMinutos());
        String horas = String.valueOf(entrenamientoList.get(position).getHoras());
        String kilometros = String.valueOf(entrenamientoList.get(position).getKilometros());
        String metros = String.valueOf(entrenamientoList.get(position).getMetros());

        TextView nombreTextView = viewHolder.nombre;
        TextView fechaTextView = viewHolder.fecha;
        TextView segundosTextView = viewHolder.segundos;
        TextView minutosTextView = viewHolder.minutos;
        TextView horasTextView = viewHolder.horas;
        TextView kilometrosTextView = viewHolder.kilometros;
        TextView metrosTextView = viewHolder.metros;
        TextView tipoTextView = viewHolder.tipo;
        nombreTextView.setText(nombre);
        fechaTextView.setText(fecha);
        segundosTextView.setText(segundos);
        minutosTextView.setText(minutos);
        horasTextView.setText(horas);
        kilometrosTextView.setText(kilometros);
        metrosTextView.setText(metros);
        tipoTextView.setText(Character.toString(tipo.charAt(0)));
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }

    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    public void animateCircularDelete(final View view, final int list_position) {
        int centerX = view.getWidth();
        int centerY = view.getHeight();
        int startRadius = view.getWidth();
        int endRadius = 0;
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Log.d(DEBUG_TAG, "SampleMaterialAdapter onAnimationEnd for Edit adapter position " + list_position);
                Log.d(DEBUG_TAG, "SampleMaterialAdapter onAnimationEnd for Edit cardId " + getItemId(list_position));

                view.setVisibility(View.INVISIBLE);
                entrenamientoList.remove(list_position);
                notifyItemRemoved(list_position);
            }
        });
        animation.start();
    }
    public void addEntrenamiento(String nombre, String fecha, float segundos, float minutos, float horas, float kilometros, float metros,  String tipo) {
        Entrenamiento entrenamiento = new Entrenamiento(nombre, fecha, segundos, minutos, horas, kilometros, metros, tipo);
        entrenamientoList.add(entrenamiento);
        ((MainActivity) context).doSmoothScroll(getItemCount());
        notifyItemInserted(getItemCount());
    }
    public void updateEntrenamiento(int id, String nombre, String fecha, float segundos,float minutos, float horas, float kilometros, float metros, String tipo, int list_position) {

    }

    public void deleteEntrenamiento(View view, int list_position) {
        animateCircularDelete(view, list_position);
    }

    public ArrayList<Entrenamiento> getAllEntrenamientos() {
        return entrenamientoList;
    }

    @Override
    public int getItemCount() {
        if (entrenamientoList.isEmpty()) {
            return 0;
        } else {
            return entrenamientoList.size();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View v = li.inflate(R.layout.lvlista_item, viewGroup, false);
        return new ViewHolder(v);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tipo;
        private TextView nombre;
        private TextView fecha;
        private TextView segundos;
        private TextView minutos;
        private TextView horas;
        private TextView kilometros;
        private TextView metros;


        public ViewHolder(View v) {
            super(v);

            nombre = (TextView) v.findViewById(R.id.cardNombre);
            fecha = (TextView) v.findViewById(R.id.cardFecha);
            segundos = (TextView) v.findViewById(R.id.cardSegundos);
            minutos = (TextView) v.findViewById(R.id.cardMinutos);
            horas = (TextView) v.findViewById(R.id.cardHoras);
            kilometros = (TextView) v.findViewById(R.id.cardKilometros);
            metros = (TextView) v.findViewById(R.id.cardMetros);
            tipo = (TextView) v.findViewById(R.id.cardTipo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Pair<View, String> p1 = Pair.create((View) nombre, MainActivity.TRANSITION_NOMBRE);
                    Pair<View, String> p2 = Pair.create((View) fecha, MainActivity.TRANSITION_FECHA);
                    Pair<View, String> p3 = Pair.create((View) segundos, MainActivity.TRANSITION_SEGUNDOS);
                    Pair<View, String> p4 = Pair.create((View) minutos, MainActivity.TRANSITION_MINUTOS);
                    Pair<View, String> p5 = Pair.create((View) horas, MainActivity.TRANSITION_HORAS);
                    Pair<View, String> p6 = Pair.create((View) kilometros, MainActivity.TRANSITION_KILOMETROS);
                    Pair<View, String> p7 = Pair.create((View) metros, MainActivity.TRANSITION_METROS);
                    Pair<View, String> p8 = Pair.create((View) tipo, MainActivity.TRANSITION_TIPO);

                    ActivityOptionsCompat options;
                    Activity act = (AppCompatActivity) context;
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, p1, p2, p3, p4, p5, p6, p7, p8);

                    int requestCode = getAdapterPosition();
                    String nombre = entrenamientoList.get(requestCode).getNombre();
                    String fecha = entrenamientoList.get(requestCode).getFecha();
                    Float segundos = entrenamientoList.get(requestCode).getSegundos();
                    Float minutos = entrenamientoList.get(requestCode).getMinutos();
                    Float horas = entrenamientoList.get(requestCode).getHoras();
                    Float kilometros = entrenamientoList.get(requestCode).getKilometros();
                    Float metros = entrenamientoList.get(requestCode).getMetros();
                    String tipo = entrenamientoList.get(requestCode).getTipo();


                    Log.d(DEBUG_TAG, "SampleMaterialAdapter itemView listener for Edit adapter position " + requestCode);

                    Intent transitionIntent = new Intent(context, TransitionEditActivity.class);
                    transitionIntent.putExtra(MainActivity.EXTRA_NOMBRE, nombre);
                    transitionIntent.putExtra(MainActivity.EXTRA_FECHA, fecha);
                    transitionIntent.putExtra(MainActivity.EXTRA_SEGUNDOS, segundos);
                    transitionIntent.putExtra(MainActivity.EXTRA_MINUTOS, minutos);
                    transitionIntent.putExtra(MainActivity.EXTRA_HORAS, horas);
                    transitionIntent.putExtra(MainActivity.EXTRA_KILOMETROS, kilometros);
                    transitionIntent.putExtra(MainActivity.EXTRA_METROS, metros);
                    transitionIntent.putExtra(MainActivity.EXTRA_TIPO, tipo);
                    transitionIntent.putExtra(MainActivity.EXTRA_UPDATE, false);
                    transitionIntent.putExtra(MainActivity.EXTRA_DELETE, false);
                    ((AppCompatActivity) context).startActivityForResult(transitionIntent, requestCode, options.toBundle());
                }
            });
        }
    }

}
