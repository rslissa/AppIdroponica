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

public class Registro extends Activity implements View.OnClickListener{


    //collegamento classe-interfaccia
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        tastohome();
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
                Intent openPage1 = new Intent(Registro.this,aggiungi.class);
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
                        Intent openInfo = new Intent(Registro.this,Info.class);
                        openInfo.removeExtra ("ActivityKey");
                        openInfo.putExtra ("ActivityKey","Registro");
                        startActivity(openInfo);
                        break;
                    case R.id.nav_reg:
                        Toast.makeText(Registro.this,"Sei già su registro", Toast.LENGTH_SHORT).show();
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
}
