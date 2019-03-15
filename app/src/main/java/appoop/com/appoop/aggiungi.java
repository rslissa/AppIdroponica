package appoop.com.appoop;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.util.Locale;

public class aggiungi extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ArrayList<Serra> serre;
    ArrayList<String> nomiSerre;
    Serra serra;
    public static Date data=null;

    public aggiungi() {
        nomiSerre=new ArrayList<String> ();
        serre=new ArrayList<Serra> ();
        serra=new Serra();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_aggiungi);
        final Button button = (Button) findViewById(R.id.button_crea);
        button.setOnClickListener(this);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomiSerre);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void onClick(View v) {

        EditText Tnome = findViewById(R.id.editText_nome);
        EditText Tm2 = findViewById(R.id.editText_m2);
        EditText Tcoltura = findViewById(R.id.editText3_coltura);
        EditText Tvarieta = findViewById(R.id.editText4_varieta);
        EditText TLOin = findViewById(R.id.editText_LOin);
        EditText TLOout = findViewById(R.id.editText_LOout);
        EditText TEC = findViewById(R.id.editText_EC);

        serra.setSerra (Tnome.getText().toString());
        serra.setM2 ( Tm2.getText().toString());
        serra.setColtura ( Tcoltura.getText().toString());
        serra.setVarieta ( Tvarieta.getText().toString());
        serra.setTrapianto (data);
        serra.setLOentrata (Double.valueOf (TLOin.getText().toString()));
        serra.setLOsgrondo (Double.valueOf (TLOout.getText().toString()));
        serra.setTargetEC (Double.valueOf (TEC.getText().toString()));
        serre.add (serra);
        nomiSerre.add(serra.getSerra ());
        for(Iterator<Serra> i= serre.iterator (); i.hasNext ();){
            Serra s=i.next ();
            System.out.println(s);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static void setData(String Sdata) {
        try {

            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            data = formatoData.parse(Sdata);
        } catch (ParseException e) {
            e.printStackTrace ( );
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
