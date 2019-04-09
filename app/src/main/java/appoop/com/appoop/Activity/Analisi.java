package appoop.com.appoop.Activity;


import android.app.Activity;
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
import android.widget.Toast;
import appoop.com.appoop.R;


public class Analisi extends AppCompatActivity implements View.OnClickListener {

    String nomeserra;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisi);
        nomeserra=getIntent ().getStringExtra ("nomeserra");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        tastohome ();
        tastinavigazione();

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    public void tastohome(){

        final Button btnHome = findViewById(R.id.button_home);
        btnHome.setOnClickListener(this);

        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent openPage1 = new Intent(Analisi.this,aggiungi.class);
                startActivity(openPage1);
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
                        Intent openInfo = new Intent(Analisi.this,Info.class);
                        openInfo.removeExtra ("ActivityKey");
                        openInfo.putExtra ("ActivityKey","Analisi");
                        startActivity(openInfo);
                        break;
                    case R.id.nav_reg:
                        Intent openReg = new Intent(Analisi.this,Registro.class);
                        openReg.removeExtra ("ActivityKey");
                        openReg.putExtra ("ActivityKey","Analisi");
                        startActivity(openReg);
                        break;
                    case R.id.nav_ana:
                        Toast.makeText(Analisi.this,"Sei già su analisi", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }
}

