package appoop.com.appoop;

import android.content.Intent;
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

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import java.util.Locale;
import android.widget.AdapterView.OnItemSelectedListener;

public class aggiungi extends AppCompatActivity implements View.OnClickListener, OnItemSelectedListener {
    ArrayList<Serra> serre;
    ArrayList<String> nomiSerre;
    Serra serra;
    public static Date data=null;
    Spinner spinner;
    Intent openInfo;
    public aggiungi() {
        nomiSerre=new ArrayList<String> ();
        serre=new ArrayList<Serra> ();
        serra=new Serra();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_aggiungi);
        addListenerOnButtonClick();
        addListenerOnSpinnerItemSelection ();

    }

    public void addItemsOnSpinner(){

        nomiSerre.removeAll(Collections.singleton(null));

            spinner =  findViewById(R.id.spinner);
            System.out.println ("ciao"+nomiSerre.size());
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomiSerre);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);


    }
    public void addListenerOnSpinnerItemSelection(){
        spinner =  findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener( this);
    }

    public void addListenerOnButtonClick(){
        final Button button =  findViewById(R.id.button_crea);
        button.setOnClickListener(this);
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
        addItemsOnSpinner();
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

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        Toast.makeText(parent.getContext(),"Serra : " + parent.getItemAtPosition(pos).toString (),Toast.LENGTH_SHORT).show();

        if(parent.getItemAtPosition (pos).equals("lista serre")){
            //do nothing
        }
        else{
            Gson gson = new GsonBuilder ().create();
            JsonArray JsonSerre = gson.toJsonTree(serre).getAsJsonArray();
            JsonArray JsonNomiSerre = gson.toJsonTree(nomiSerre).getAsJsonArray();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString ("JsonSerre",JsonSerre.toString ());
            editor.apply();
            editor.putString ("JsonNomiSerre",JsonNomiSerre.toString ());
            editor.apply();

            openInfo  = new Intent(aggiungi.this, Info.class);
            openInfo.putExtra("serra",serre.get(pos));
            openInfo.putExtra("nomiSerre",nomiSerre);
            startActivity (openInfo);
            //no se
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

}
