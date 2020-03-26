package com.juvera.materialwatertraining.view;

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

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.juvera.materialwatertraining.R;
import com.juvera.materialwatertraining.core.Entrenamiento;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Random;

public class TransitionAddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private static final String TAG = "MyActivity";
    Button buttonAdd;

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int Year, Month, Day, Hour, Minute, Second;
    private TextInputEditText nombre;
    private TextInputEditText fecha;
    private TextInputEditText segundos;
    private TextInputEditText minutos;
    private TextInputEditText horas;
    private TextInputEditText kilometros;
    private TextInputEditText metros;
    private Intent intent;
    String tipo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_add);



        nombre = (TextInputEditText) findViewById(R.id.nombre);
        fecha = (TextInputEditText) findViewById(R.id.fecha);
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        Hour = calendar.get(Calendar.HOUR);
        Minute = calendar.get(Calendar.MINUTE);
        Second = calendar.get(Calendar.SECOND);


        segundos = (TextInputEditText) findViewById(R.id.segundos);
        minutos = (TextInputEditText) findViewById(R.id.minutos) ;
        horas = (TextInputEditText) findViewById(R.id.horas);
        kilometros = (TextInputEditText) findViewById(R.id.kilometros);
        metros =  (TextInputEditText) findViewById(R.id.metros);
        intent = getIntent();






        Button dialog_bt_date = (Button) findViewById(R.id.dialog_bt_date);
        dialog_bt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog = DatePickerDialog.newInstance(TransitionAddActivity.this, Year, Month, Day);

                datePickerDialog.setThemeDark(false);

                datePickerDialog.showYearPickerFirst(false);

                datePickerDialog.setAccentColor(Color.parseColor("#0072BA"));

                datePickerDialog.setTitle("Select Date From DatePickerDialog");

                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
                onDateSet(datePickerDialog, Year, Month, Day);


            }
        });


        buttonAdd = (Button) findViewById(R.id.add_button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // must not be zero otherwise do not finish activity and report Toast message

                    intent.putExtra(MainActivity.EXTRA_NOMBRE, String.valueOf(nombre.getText()));
                    intent.putExtra(MainActivity.EXTRA_FECHA, String.valueOf(fecha.getText()));
                    intent.putExtra(MainActivity.EXTRA_SEGUNDOS, String.valueOf(segundos.getText()));
                    intent.putExtra(MainActivity.EXTRA_MINUTOS, String.valueOf(minutos.getText()));
                    intent.putExtra(MainActivity.EXTRA_HORAS, String.valueOf(horas.getText()));
                    intent.putExtra(MainActivity.EXTRA_KILOMETROS, String.valueOf(kilometros.getText()));
                    intent.putExtra(MainActivity.EXTRA_METROS, String.valueOf(metros.getText()));
                    intent.putExtra(MainActivity.EXTRA_TIPO, String.valueOf(tipo));
                    setResult(RESULT_OK, intent);
                    supportFinishAfterTransition();

                Log.i(TAG, nombre.getText()+ "/" + Day + "-" + Month + "-" + Year+ "/" + horas.getText()+ ":" + segundos.getText() + ":" +  minutos.getText()+ "/"
                        + kilometros.getText()+ " km " + metros.getText() + " m" + tipo) ;
                }
            });
}



    @Override
    public void onDateSet(DatePickerDialog view, int Year, int Month, int Day) {

        String date = Day + "-" + Month + "-" + Year;

        Toast.makeText(TransitionAddActivity.this, date, Toast.LENGTH_LONG).show();
        fecha.setText(date);
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