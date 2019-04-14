package appoop.com.appoop.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;

import appoop.com.appoop.DBadapter.DBadapter;
import appoop.com.appoop.DBadapter.VolleyCallback;
import appoop.com.appoop.R;
import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.modelli.Serra;


public class Info extends AppCompatActivity implements Serializable {
    DBadapter db;
    String nomeserra;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        db= new DBadapter ();
        nomeserra=getIntent ().getStringExtra ("nomeserra");

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
        ClickTastoHome();
        tastinavigazione();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    public void ClickTastoHome(){
        final Button btnHome = findViewById(R.id.button_home);
        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent openAggiungi = new Intent(Info.this,aggiungi.class);
                startActivity(openAggiungi);
            }
        });
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

        TextView info_nome = findViewById(R.id.info_nome);
        EditText info_m2 = findViewById(R.id.info_m2);
        EditText info_coltura = findViewById(R.id.info_coltura);
        EditText info_varieta = findViewById(R.id.info_varieta);
        EditText info_data = findViewById(R.id.info_data);
        EditText info_entrata = findViewById(R.id.info_entrata);
        EditText info_uscita = findViewById(R.id.info_uscita);
        EditText info_ec = findViewById(R.id.info_ec);

        info_nome.setText(serra.getSerra());
        info_m2.setText(serra.getM2());
        info_coltura.setText(serra.getColtura());
        info_varieta.setText(serra.getVarieta());
        info_data.setText(serra.TrapiantoToString());
        info_entrata.setText(serra.LOinToString());
        info_uscita.setText(serra.LOoutToString());
        info_ec.setText(serra.TargetECToString());

    }

}