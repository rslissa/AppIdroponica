package appoop.com.appoop.Activity;

import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;


import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import appoop.com.appoop.DBadapter.DBadapter;
import appoop.com.appoop.DBadapter.VolleyCallback;
import appoop.com.appoop.altro.DatePickerFragment;
import appoop.com.appoop.R;
import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.modelli.Serra;

public class aggiungi extends AppCompatActivity implements View.OnClickListener, OnItemSelectedListener,Serializable {
    ArrayList<String> nomiserre;
    public static Date data=null;
    Serra serra;
    Spinner spinner;
    Intent openInfo;
    DBadapter db;
    public aggiungi() {
        serra=new Serra ();

        db=new DBadapter ();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_aggiungi);
        addListenerOnButtonClick();
        addListenerOnSpinnerItemSelection ();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        loadNomiSerre ();
      
    }

    private void loadNomiSerre( ){
        db.GetNomeSerre (this,new VolleyCallback ( ) {
            @Override
            public void onSuccessGNS(ArrayList <String> ns) {
                nomiserre=new ArrayList<> (ns);
                nomiserre.add(0,"lista serre");
                addItemsOnSpinner (nomiserre);
            }

            @Override
            public void onSuccessGS(Serra s) {
                //do nothing
            }

            @Override
            public void onSuccessGR(ArrayList<Rilevamento> lr) {
                //do nothing
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    public void addItemsOnSpinner(ArrayList ns){
            spinner =  findViewById(R.id.spinner);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ns);
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
        serra=new Serra();
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
        db.PutSerra (this,serra);
        ArrayList temp=new ArrayList ();
        temp.add(serra.getSerra ());
        addItemsOnSpinner (temp);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment ();
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
        chiamare activity info, all' activity info passiamo il nome della serra,
     */
    public void onItemSelected(final AdapterView<?> parent, View view,final int pos, long id) {

        if(parent.getItemAtPosition (pos).equals("lista serre")){
            //do nothing
        }else{
            db.GetNomeSerre (this,new VolleyCallback ( ) {
                @Override
                public void onSuccessGNS(ArrayList <String> ns) {
                    if(pos!=0){
                        Toast.makeText(parent.getContext(),"Serra : " + parent.getItemAtPosition(pos).toString (),Toast.LENGTH_SHORT).show();
                        String nomeserra=  ns.get(pos-1);
                        openInfo  = new Intent(aggiungi.this, Info.class);
                        openInfo.putExtra("nomeserra",nomeserra);
                        startActivity (openInfo);
                    }
                }

                @Override
                public void onSuccessGS(Serra s) {
                    //do nothing
                }

                @Override
                public void onSuccessGR(ArrayList<Rilevamento> lr) {
                    //do nothing
                }
            });
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.item2:
                Toast.makeText(this,"Puoi già aggiungere una nuova serra",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}
