package appoop.com.appoop.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;

import appoop.com.appoop.DBadapter.DBadapter;
import appoop.com.appoop.DBadapter.VolleyCallback;
import appoop.com.appoop.R;
import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.modelli.Serra;


public class Info extends AppCompatActivity implements Serializable, AdapterView.OnItemSelectedListener {
    DBadapter db;
    Intent openInfo;
    Spinner spinner;
    String nomeserra;
    Button btnDelete;
    Button btnModify;
    Button btnDeleteRil;
    EditText info_m2;
    EditText info_coltura;
    EditText info_varieta;
    EditText info_data;
    EditText info_entrata;
    EditText info_uscita;
    EditText info_ec;
    ArrayList<String> nomiserre;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        db= new DBadapter ();
        nomeserra=getIntent ().getStringExtra ("nomeserra");
        addListenerOnSpinnerItemSelection ();
        loadNomiSerre ();
        TextView titolo = findViewById(R.id.info_nome);
        titolo.setText("Info" + " " + nomeserra);
        titolo.setTextSize(22);

        db.GetSerra (this,nomeserra,new VolleyCallback ( ) {
            @Override
            public void onSuccessGNS(ArrayList ns) {
                //do nothing
            }

            @Override
            public void onSuccessGS(Serra s) {
                System.out.println ("stampa serra: "+s.toString ());
                load(s);
            }

            @Override
            public void onSuccessGR(ArrayList<Rilevamento> lr) {
                //do nothing
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        tastinavigazione();
        btnDelete = findViewById(R.id.button_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Info.this)
                        .setTitle("Sei sicuro di voler eliminare questa serra?")
                        .setMessage(" verranno eliminati in maniera definitiva anche i rilevamenti ad essa associata")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                 db.DeleteSerra (Info.this,nomeserra);
                                 db.DeleteRilevamentiSerra (Info.this,nomeserra);
                                 Intent gotoaggiungi = new Intent(getBaseContext(), aggiungi.class);
                                 finish();
                                 startActivity(gotoaggiungi);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        btnModify = findViewById(R.id.button_modify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.GetSerra (Info.this,nomeserra,new VolleyCallback ( ) {
                    @Override
                    public void onSuccessGNS(ArrayList ns) {
                        //do nothing
                    }

                    @Override
                    public void onSuccessGS(Serra s) {
                        Intent gotoModify=new Intent (Info.this,ModifySerra.class);
                        gotoModify.putExtra ("modifyserra",s);
                        finish ();
                        startActivity(gotoModify);
                    }

                    @Override
                    public void onSuccessGR(ArrayList<Rilevamento> lr) {
                        //do nothing
                    }
                });



            }
        });
        btnDeleteRil = findViewById(R.id.button_delete_rilevamenti);
        btnDeleteRil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Info.this)
                        .setTitle("Sei sicuro di voler continuare?")
                        .setMessage(" tutti i rilevamenti associati a questa serra verranno eliminati in maniera definitiva")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                db.DeleteRilevamentiSerra (Info.this,nomeserra);
                                Toast.makeText (Info.this, "tutti i rilevamenti della serra sono stati eliminati", Toast.LENGTH_SHORT).show ( );
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
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
    public void addItemsOnSpinner(ArrayList ns){
        spinner =  findViewById(R.id.spinnerInfo);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ns);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    public void addListenerOnSpinnerItemSelection(){
        spinner =  findViewById(R.id.spinnerInfo);
        spinner.setOnItemSelectedListener(this);
    }

    public void tastinavigazione(){
        BottomNavigationView btnNav = findViewById(R.id.bottom_navigation);
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_info:
                        Toast.makeText(Info.this,"Sei già su Info", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_reg:
                        Intent openReg = new Intent(Info.this,Registro.class);
                        openReg.putExtra ("nomeserra",nomeserra);
                        startActivity(openReg);
                        break;
                    case R.id.nav_ana:
                        Intent openAna = new Intent(Info.this,Analisi.class);
                        openAna.putExtra ("nomeserra",nomeserra);
                        startActivity(openAna);
                        break;
                }

                return true;
            }
        });
    }

    public void load(Serra serra) {

        //TextView info_nome = findViewById(R.id.info_nome);
         info_m2 = findViewById(R.id.info_m2);
         info_coltura = findViewById(R.id.info_coltura);
         info_varieta = findViewById(R.id.info_varieta);
         info_data = findViewById(R.id.info_data);
         info_entrata = findViewById(R.id.info_entrata);
         info_uscita = findViewById(R.id.info_uscita);
         info_ec = findViewById(R.id.info_ec);

        //info_nome.setText(serra.getSerra());
        info_m2.setText(serra.getM2());
        info_coltura.setText(serra.getColtura());
        info_varieta.setText(serra.getVarieta());
        info_data.setText(serra.TrapiantoToString());
        info_entrata.setText(serra.LOinToString());
        info_uscita.setText(serra.LOoutToString());
        info_ec.setText(serra.TargetECToString());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.item2:
                Intent goToAddNewSerra = new Intent(this, aggiungi.class);
                finish();
                startActivity(goToAddNewSerra);
                break;
        }

        return true;
    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, View view, final int pos, long id) {

        if(parent.getItemAtPosition (pos).equals("lista serre")){
            //do nothing
        }else{
            db.GetNomeSerre (this,new VolleyCallback ( ) {
                @Override
                public void onSuccessGNS(ArrayList <String> ns) {
                    if(pos!=0){
                        Toast.makeText(parent.getContext(),"Serra : " + parent.getItemAtPosition(pos).toString (),Toast.LENGTH_SHORT).show();
                        String nomeserra=  ns.get(pos-1);
                        openInfo  = new Intent(Info.this, Info.class);
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
    public void onNothingSelected(AdapterView<?> parent) {

    }
}