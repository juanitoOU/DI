package com.juvera.materialwatertraining.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.juvera.materialwatertraining.R;
import com.juvera.materialwatertraining.bd.DBNadador;
import com.juvera.materialwatertraining.core.Nadador;

import java.util.Locale;

import static com.juvera.materialwatertraining.view.MainActivity.CODIGO_ADICION_ITEM;

public class ConfigActivity extends AppCompatActivity {


    private Locale locale;
    private Configuration configuracion;
    private DBNadador db;
    private Nadador nadador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        this.configuracion= new Configuration();

        this.db = new DBNadador(this.getApplicationContext());
        this.nadador = db.recuperarNadador();
      final TextInputEditText nombre = (TextInputEditText) findViewById(R.id.nombreNadador);
      final TextInputEditText edad = (TextInputEditText) findViewById(R.id.edadNadador) ;
      final TextInputEditText nacionalidad = (TextInputEditText) findViewById(R.id.nacionalidad);

        final int[] aux = {0};


        nombre.setText(nadador.getNombre());
        edad.setText(String.valueOf(nadador.getEdad()));
        nacionalidad.setText(nadador.getNacionalidad());

        Button editar = (Button) findViewById(R.id.modificar_button);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nadador aux3 = new Nadador();
                aux3.setNombre(nombre.getText().toString());
                aux3.setNacionalidad(nacionalidad.getText().toString());
                aux3.setEdad(Integer.valueOf(edad.getText().toString()));

                db.cambiarNadador(aux3);

                db.close();

                switch (aux[0]){
                    case 0:
                        locale= new Locale("en");
                        configuracion.locale=locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        configuracion.locale=locale;
                        break;
                }

                getResources().updateConfiguration(configuracion, null);
                Intent refresh = new Intent(ConfigActivity.this, MainActivity.class);
                startActivity(refresh);
                ConfigActivity.this.finish();
            }
        });
        RadioGroup radio = (RadioGroup) ConfigActivity.this.findViewById(R.id.leguaje);
        RadioButton radioButton = (RadioButton) ConfigActivity.this.findViewById(radio.getCheckedRadioButtonId());
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (aux[0] ==1){
                    aux[0] =0;
                } else {
                    aux[0] =1;
                }
            }
        });

        Button cancelar = (Button) findViewById(R.id.cancelar_button);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfigActivity.this.finish();
            }
        });
    }


}
