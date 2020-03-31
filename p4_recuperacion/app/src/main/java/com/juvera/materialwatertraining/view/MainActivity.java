package com.juvera.materialwatertraining.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juvera.materialwatertraining.R;
import com.juvera.materialwatertraining.bd.DBManager;
import com.juvera.materialwatertraining.bd.DBNadador;
import com.juvera.materialwatertraining.core.Entrenamiento;
import com.juvera.materialwatertraining.core.Nadador;
import com.juvera.materialwatertraining.core.SampleMaterialAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    protected static final int CODIGO_ADICION_ITEM = 100;
    private Locale locale;
    private Configuration config = new Configuration();
    public ArrayList<Entrenamiento> listaEntrenamientos;
    private DBManager gestorDB;
    private RecyclerView recyclerView;
    private SampleMaterialAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        DBNadador dbn = new DBNadador(this);

        Nadador n = new Nadador();
        n.setNombre("nombre");
        n.setEdad(00);
        n.setNacionalidad("España");
        dbn.setNadador(n);

        gestorDB = new DBManager(this.getApplicationContext());
        this.listaEntrenamientos= gestorDB.getEntrenamientos();

        if (adapter == null) {
            adapter = new SampleMaterialAdapter(this, listaEntrenamientos );
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(MainActivity.this, TransitionAddActivity.class);
                add.putExtra("id", -1);
                MainActivity.this.startActivityForResult(add, CODIGO_ADICION_ITEM );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean toret = false;
        switch (menuItem.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, TransitionAddActivity.class);
                intent.putExtra("id",-1);
                this.startActivityForResult(intent, CODIGO_ADICION_ITEM);
                toret = true;
                break;
            case R.id.delete:
                listaEntrenamientos = gestorDB.getEntrenamientos();
                String[] strings = new String[listaEntrenamientos.size()];
                final boolean[] selected = new boolean[listaEntrenamientos.size()];

                for (int i=0;i<listaEntrenamientos.size();i++){
                    strings[i] = listaEntrenamientos.get(i).toString();
                    selected[i]=false;
                }

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
                builder.setTitle("¿Qué entrenamientos desea eliminar?");
                builder.setMultiChoiceItems(strings, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which] =  isChecked;
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int aux = 0;
                        for(int i = 0; i<listaEntrenamientos.size();i++){
                            if (selected[i]){
                                adapter.delete(null, (i-aux));
                                aux++;
                            }
                        }
                    }
                });
                builder.create().show();

                break;
            case R.id.edit:
                listaEntrenamientos = gestorDB.getEntrenamientos();
                String[] strings2 = new String[listaEntrenamientos.size()];
                final Integer[] selected2 = new Integer[1];
                selected2[0]=0;

                for (int i=0;i<listaEntrenamientos.size();i++){
                    strings2[i] = listaEntrenamientos.get(i).toString();
                }

                MaterialAlertDialogBuilder builder2 = new MaterialAlertDialogBuilder(this);
                builder2.setTitle("¿Qué entrenamiento quiere modificar?");
                builder2.setSingleChoiceItems(strings2, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected2[0] = which;
                    }
                });
                builder2.setNegativeButton("Cancelar", null);
                builder2.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, TransitionAddActivity.class);
                        intent.putExtra("id", listaEntrenamientos.get(selected2[0]).getId());
                        MainActivity.this.startActivityForResult(intent, CODIGO_ADICION_ITEM);
                    }
                });
                builder2.create().show();

                break;
            case R.id.configuracion:
                Intent subactividad = new Intent(MainActivity.this, ConfigActivity.class);
                MainActivity.this.startActivity(subactividad);
                break;
        }

        return toret;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CODIGO_ADICION_ITEM && resultCode==RESULT_OK){
            int id = data.getIntExtra("id", -1);
            adapter.addEntrenamiento(gestorDB.getEntrenamiento(id));
        } else if (resultCode==RESULT_OK) {
            int id = data.getIntExtra("id", -1);
            adapter.updateEntrenamiento(gestorDB.getEntrenamiento(id));
        }

    }





    public void doSmoothScroll(int position) {
        recyclerView.smoothScrollToPosition(position);
    }
}



