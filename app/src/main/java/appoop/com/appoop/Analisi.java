package appoop.com.appoop;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Analisi extends Activity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisi);

        tastohome ();
        tastinavigazione();

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void tastohome(){

        final Button btnHome = findViewById(R.id.btn_home);
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

