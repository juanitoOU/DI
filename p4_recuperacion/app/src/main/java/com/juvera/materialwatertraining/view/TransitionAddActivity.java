package com.juvera.materialwatertraining.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.juvera.materialwatertraining.R;
import com.juvera.materialwatertraining.bd.DBManager;
import com.juvera.materialwatertraining.core.Entrenamiento;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialog;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialogCallback;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class TransitionAddActivity extends AppCompatActivity implements SlideDatePickerDialogCallback{

    private static final String TAG = "MyActivity";
    Button buttonAdd;
    Button buttonCancel;
    int Year, Month, Day, Hour, Minute, Second;

    String tipo;

    private Button fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_add);

        buttonAdd = (Button) findViewById(R.id.add_button);
        buttonCancel = (Button) this.findViewById(R.id.cancel_button);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextInputEditText nombre = findViewById(R.id.nombre);
        final TextInputEditText segundos = findViewById(R.id.segundos);
        final TextInputEditText minutos = findViewById(R.id.minutos) ;
        final TextInputEditText horas = findViewById(R.id.horas);
        final TextInputEditText kilometros = findViewById(R.id.kilometros);
        final TextInputEditText metros = findViewById(R.id.metros);
        final Intent intent = this.getIntent();
        final int id = intent.getExtras().getInt("id");





        fecha = (Button) this.findViewById(R.id.fecha);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar date= Calendar.getInstance();
                date.set(Calendar.YEAR, 2030);

                SlideDatePickerDialog.Builder builder = new SlideDatePickerDialog.Builder();
                builder.setEndDate(date);
                SlideDatePickerDialog dialog = builder.build();
                dialog.show(getSupportFragmentManager(), "Dialog");
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionAddActivity.this.finish();
            }
        });

        if (id!=-1){
            DBManager gestorDB = new DBManager(this.getApplicationContext());

            Entrenamiento e = gestorDB.getEntrenamiento(id);
            buttonAdd.setText("Editar");
            nombre.setText(e.getNombre());
            segundos.setText(String.valueOf(e.getSegundos()));
            minutos.setText(String.valueOf(e.getMinutos()));
            horas.setText(String.valueOf(e.getHoras()));
            kilometros.setText(String.valueOf(e.getKilometros()));
            metros.setText(String.valueOf(e.getMetros()));
            fecha.setText(e.getFecha());

            if (e.getTipo().equalsIgnoreCase("Piscina Grande")){
                RadioButton radioButton = (RadioButton) this.findViewById(R.id.primero);
                radioButton.setChecked(true);
            } else if (e.getTipo().equalsIgnoreCase("Piscina Mediana")){
                RadioButton radioButton = (RadioButton) this.findViewById(R.id.segundo);
                radioButton.setChecked(true);
            } else {
                RadioButton radioButton = (RadioButton) this.findViewById(R.id.tercero);
                radioButton.setChecked(true);
            }

        } else {
            buttonAdd.setText("Añadir");
        }

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validar();
            }
        });
        segundos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validar();
            }
        });
        minutos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validar();
            }
        });

        horas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validar();
            }
        });
        metros.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validar();
            }
        });

        kilometros.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validar();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // must not be zero otherwise do not finish activity and report Toast message

                Entrenamiento entrenamiento = new Entrenamiento();
                entrenamiento.setId(id);
                entrenamiento.setNombre(nombre.getText().toString());
                entrenamiento.setFecha(fecha.getText().toString());
                entrenamiento.setSegundos(Integer.valueOf(segundos.getText().toString()));
                entrenamiento.setMinutos(Integer.valueOf(minutos.getText().toString()));
                entrenamiento.setHoras(Integer.valueOf(horas.getText().toString()));
                entrenamiento.setKilometros(Integer.valueOf(kilometros.getText().toString()));
                entrenamiento.setMetros(Integer.valueOf(metros.getText().toString()));
                entrenamiento.setTipo(tipo);

                DBManager gestorBD = new DBManager(TransitionAddActivity.this.getApplicationContext());
                Intent intent = new Intent();
                Log.i(TAG, nombre.getText()+ "/" + Day + "-" + Month + "-" + Year+ "/" + horas.getText()+ ":" + segundos.getText() + ":" +  minutos.getText()+ "/"
                        + kilometros.getText()+ " km " + metros.getText() + " m" + tipo) ;

                if (id==-1){
                    gestorBD.insertaItem(entrenamiento);
                    intent.putExtra("id", gestorBD.getEntrenamientos().get(gestorBD.getEntrenamientos().size()-1).getId());
                    Log.i(TAG, String.valueOf(entrenamiento.getId())) ;
                } else {
                    gestorBD.editaItem(entrenamiento);
                    intent.putExtra("id", id);
                }

                gestorBD.close();

                TransitionAddActivity.this.setResult(Activity.RESULT_OK, intent);
                TransitionAddActivity.this.finish();
            }
            });



}
    private void validar(){
        final TextInputEditText nombre = (TextInputEditText) this.findViewById(R.id.nombre);
        final TextInputEditText horas = (TextInputEditText) this.findViewById(R.id.horas);
        final TextInputEditText min = (TextInputEditText) this.findViewById(R.id.minutos);
        final TextInputEditText segundos = (TextInputEditText) this.findViewById(R.id.segundos);
        final TextInputEditText km = (TextInputEditText) this.findViewById(R.id.kilometros);
        final TextInputEditText metros = (TextInputEditText) this.findViewById(R.id.metros);
        Button button = (Button) this.findViewById(R.id.add_button);
        if (!nombre.getText().toString().trim().isEmpty() && !horas.getText().toString().trim().isEmpty() && !min.getText().toString().trim().isEmpty() &&
                !segundos.getText().toString().trim().isEmpty() && !km.getText().toString().trim().isEmpty() && !metros.getText().toString().trim().isEmpty()
                && !fecha.getText().toString().trim().equalsIgnoreCase("Seleccionar Fecha")){


            buttonAdd.setEnabled(true);
        } else {
            buttonAdd.setEnabled(false);
        }
    }



    @Override
    public void onPositiveClick(int i, int i1, int i2, Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fecha.setText(dateFormat.format(calendar.getTime()));
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.primero:
                if (checked)
                    tipo ="Piscina grande";
                    break;
            case R.id.segundo:
                if (checked)
                    tipo ="Piscina mediana";
                    break;
            case R.id.tercero:
                if (checked)
                    tipo ="Aguas abiertas";
                    break;
        }
    }

}