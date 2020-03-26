package com.juvera.materialwatertraining.view;

import androidx.appcompat.app.AppCompatActivity;
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
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.juvera.materialwatertraining.R;
import com.juvera.materialwatertraining.bd.DBManager;
import com.juvera.materialwatertraining.core.Entrenamiento;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Locale locale;
    private Configuration config = new Configuration();
    private ArrayList<Entrenamiento> entrenamientoList = new ArrayList<>();
    private SampleBDAdapter adapter;
 //   private DBManager gestorDB;
    Cursor c;


    private RecyclerView.LayoutManager layoutManager;



    private RecyclerView recyclerView;

    protected static final int CODIGO_EDICION_ITEM = 100;
    private static final String DEBUG_TAG = "AppCompatActivity";

    public static final String EXTRA_UPDATE = "update";
    public static final String EXTRA_DELETE = "delete";
    public static final String EXTRA_NOMBRE = "nombre";
    public static final String EXTRA_FECHA = "fecha";
    public static final String EXTRA_SEGUNDOS = "segundos";
    public static final String EXTRA_MINUTOS = "minutos";
    public static final String EXTRA_HORAS = "horas";
    public static final String EXTRA_KILOMETROS = "kilometros";
    public static final String EXTRA_METROS = "metros";
    public static final String EXTRA_TIPO = "tipo";


    public static final String TRANSITION_FAB = "fab_transition";
    public static final String TRANSITION_NOMBRE = "nombre_transition";
    public static final String TRANSITION_FECHA = "fecha_transition";
    public static final String TRANSITION_SEGUNDOS = "segundos_transition";
    public static final String TRANSITION_MINUTOS = "minutos_transition";
    public static final String TRANSITION_HORAS = "horas_transition";
    public static final String TRANSITION_KILOMETROS = "kilometros_transition";
    public static final String TRANSITION_METROS = "metros_transition";
    public static final String TRANSITION_TIPO = "initial_tipo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Configurar lista
        final RecyclerView recyclerView = this.findViewById(R.id.recyclerView);

        if (adapter == null) {
            adapter = new SampleBDAdapter(this, entrenamientoList, c );
        }

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        this.actualizaEntrenamientos();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pair<View, String> pair = Pair.create(v.findViewById(R.id.fab), TRANSITION_FAB);

                ActivityOptionsCompat options;
                Activity act = MainActivity.this;
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, pair);

                Intent transitionIntent = new Intent(act, TransitionAddActivity.class);
                act.startActivityForResult(transitionIntent, adapter.getItemCount(), options.toBundle());
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(DEBUG_TAG, "requestCode is " + requestCode);
        // if adapter.getItemCount() is request code, that means we are adding a new position
        // anything less than adapter.getItemCount() means we are editing a particular position
        if (requestCode == adapter.getItemCount()) {
            if (resultCode == RESULT_OK && requestCode == CODIGO_EDICION_ITEM) {
                // Make sure the Add request was successful
                // if add name, insert name in list
                Integer id = data.getExtras().getInt("_id", -1);
                String nombre = data.getExtras().getString("nombre", "ERROR");
                String fecha = data.getExtras().getString("fecha", "ERROR");
                float segundos = data.getExtras().getFloat("segundos", 0);
                float minutos = data.getExtras().getFloat("minutos", 0);
                float horas = data.getExtras().getFloat("horas", 0);
                float kilometros = data.getExtras().getFloat("kilometros", 0);
                float metros = data.getExtras().getFloat("metros", 0);
                String tipo = data.getExtras().getString("tipo", "ERROR");
                //              int color = data.getIntExtra(EXTRA_COLOR, 0);
                adapter.addEntrenamiento(nombre, fecha,segundos,minutos,horas,kilometros,metros, tipo);

            }
        } else {
            // Anything other than adapter.getItemCount() means editing a particular list item
            // the requestCode is the list item position
            if (resultCode == RESULT_OK) {
                // Make sure the Update request was successful
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(requestCode);
                if (data.getExtras().getBoolean(EXTRA_DELETE, false)) {
                    // if delete user delete
                    // The user deleted a contact
                    Integer id = data.getExtras().getInt("_id", -1);
                    adapter.deleteEntrenamiento(viewHolder.itemView, requestCode);
                    delete(id);
                } else if (data.getExtras().getBoolean(EXTRA_UPDATE)) {
                    // if name changed, update user
                    int id = data.getExtras().getInt("_id", -1);
                    String nombre = data.getExtras().getString("nombre", "ERROR");
                    String fecha = data.getExtras().getString("fecha", "ERROR");
                    float segundos = data.getExtras().getFloat("segundos", 0);
                    float minutos = data.getExtras().getFloat("minutos", 0);
                    float horas = data.getExtras().getFloat("horas", 0);
                    float kilometros = data.getExtras().getFloat("kilometros", 0);
                    float metros = data.getExtras().getFloat("metros", 0);
                    String tipo = data.getExtras().getString("tipo", "ERROR");
                    viewHolder.itemView.setVisibility(View.INVISIBLE);
                    adapter.updateEntrenamiento(id, nombre, fecha,segundos,minutos,horas,kilometros,metros, tipo, requestCode);
            }
            }
        }
    }

    private void delete(final int pos) {

        Cursor cursor = this.adapter.getCursor();
        final int id = cursor.getInt(0);


//        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Eliminado");
//        builder.setMessage("Seguro que quieres borrar el elemento ?");
//        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                gestorDB.eliminaItem(id);
//                actualizaEntrenamientos();
//
//            }
//        });
//        builder.setNegativeButton("Cancelar", null);
//        builder.create().show();
    }


    private void showDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.select_language));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                switch (which) {
                    case 0:
                        locale = new Locale("es");
                        config.locale = locale;
                        break;
                    case 1:
                        locale = new Locale("en");
                        config.locale = locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                startActivity(refresh);
                finish();
            }
        });
        b.show();
    }

    private void actualizaEntrenamientos()
    {
        adapter.getAllEntrenamientos();
    }
    //method to do the scroll smoother
    public void doSmoothScroll(int position) {
        recyclerView.smoothScrollToPosition(position);
    }
}



