package appoop.com.appoop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class Registro extends Activity implements View.OnClickListener{

    public Date trapianto;
    public double LOentrata;
    public double LOsgrondo;
    public double TargetEC;

    ArrayList<Serra> serre;
    ArrayList<String> nomiSerre;
    public Serra serra;

    //collegamento classe-interfaccia
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        final Button btnHome = (Button) findViewById(R.id.btn_home);
        btnHome.setOnClickListener(this);

        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent openPage1 = new Intent(Registro.this,aggiungi.class);
                startActivity(openPage1);
            }
        });

        BottomNavigationView btnNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_info:
                        Intent openInfo = new Intent(Registro.this,Info.class);
                        startActivity(openInfo);
                        break;
                    case R.id.nav_reg:
                        Intent openReg = new Intent(Registro.this,Registro.class);
                        startActivity(openReg);
                        break;
                    case R.id.nav_ana:
                        Intent openAna = new Intent(Registro.this,Analisi.class);
                        startActivity(openAna);
                        break;
                }

                return true;
            }
        });


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
