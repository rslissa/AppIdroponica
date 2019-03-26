package appoop.com.appoop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class aggiungi extends AppCompatActivity implements View.OnClickListener, OnItemSelectedListener,Serializable {
    ArrayList<Serra> serre;
    ArrayList<String> nomiSerre;
    Serra serra;
    public static Date data=null;
    Spinner spinner;
    Intent openInfo;
    SharedPreferences preferences;

    public aggiungi() {
        nomiSerre= new ArrayList<> ( );
        serre= new ArrayList<> ( );
        serra=new Serra();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_aggiungi);
        addListenerOnButtonClick();
        addListenerOnSpinnerItemSelection ();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        /*
            prima di tutto controlliamo se sharedpreference contiene i nostri array serre e nomi serre, se strjson è uguale a 0
            vuol dire che l'array non è contenuto nella sharedPreference, cioè è la prima volta che apro l' app, allora imposto il
            primo valore di nomiserre a "lista serre" e aggiungo il  primo valore (null) a serre per mantenere lo stesso indice.
            se invece sono presenti li carichiamo.
         */

        String strJsonNomiSerre = preferences.getString("JsonNomiSerre","0");//second parameter is necessary ie.,Value to return if this preference does not exist.
        String strJsonSerre = preferences.getString("JsonSerre","0");//second parameter is necessary ie.,Value to return if this preference does not exist.

        if(strJsonNomiSerre.equals ("0") && strJsonSerre.equals ("0")){
            nomiSerre.add("lista serre");
            serre.add(0,null);
            addItemsOnSpinner();
        }else{
            nomiSerre = new Gson().fromJson(strJsonNomiSerre, new TypeToken<List<String>> (){}.getType());
            addItemsOnSpinner();
            serre = new Gson().fromJson(strJsonSerre, new TypeToken<List<Serra>> (){}.getType());
            }


    }

    public void addItemsOnSpinner(){
           spinner =  findViewById(R.id.spinner);

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
        if(data==null){
            String pattern = "dd/MM/yyyy";
            DateFormat df = new SimpleDateFormat (pattern);
            data= df.getCalendar ().getInstance().getTime();
            System.out.println (""+data);
        }
        serra.setTrapianto (data);
        serra.setLOentrata (Double.valueOf (TLOin.getText().toString()));
        serra.setLOsgrondo (Double.valueOf (TLOout.getText().toString()));
        serra.setTargetEC (Double.valueOf (TEC.getText().toString()));

        serre.add (serra);
        nomiSerre.add(serra.getSerra ());
        addItemsOnSpinner();
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

    /*
        se si è selezionato un elemento dallo spinner, se è il primo elemento non dobbiamo fare niente, se non è il primo dobbiamo
        chiamare activity info, all' activity info passiamo il vettore nomiserre e la classe serra, invece per salvare i dati
        su aggiungi utilizziamo la libreria sharedpreferences e salviamo gli array nomiserre e serre
        nb: gli array devono essere prima convertiti in json per essere salvati in sharedpreferences
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if(pos!=0)
            Toast.makeText(parent.getContext(),"Serra : " + parent.getItemAtPosition(pos).toString (),Toast.LENGTH_SHORT).show();

        if(parent.getItemAtPosition (pos).equals("lista serre")){
            //do nothing
        }
        else{
            Gson gson = new GsonBuilder ().create();
            JsonArray JsonSerre = gson.toJsonTree(serre).getAsJsonArray();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString ("JsonSerre",JsonSerre.toString ());
            editor.apply();


            openInfo  = new Intent(aggiungi.this, Info.class);
            openInfo.removeExtra ("ActivityKey");
            openInfo.putExtra ("ActivityKey","Aggiungi");
            openInfo.putExtra("serra",serre.get(pos));
            openInfo.putExtra("nomiSerre",nomiSerre);
            startActivity (openInfo);
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

}
