package appoop.com.appoop;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

public class Analisi extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisi);

        BottomNavigationView btnNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_info:
                        Intent openInfo = new Intent(Analisi.this,Info.class);
                        startActivity(openInfo);
                        break;
                    case R.id.nav_reg:
                        Intent openReg = new Intent(Analisi.this,Registro.class);
                        startActivity(openReg);
                        break;
                    case R.id.nav_ana:
                        Intent openAna = new Intent(Analisi.this,Analisi.class);
                        startActivity(openAna);
                        break;
                }

                return true;
            }
        });

    }

}

