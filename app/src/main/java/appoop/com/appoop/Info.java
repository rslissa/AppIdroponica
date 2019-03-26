package appoop.com.appoop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Info extends Activity implements Serializable {
    Serra serra;
    String key;
    SharedPreferences preferences;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        key = getIntent().getStringExtra ("ActivityKey");
        System.out.println("key:"+key);
        if(key.equals ("Aggiungi")){
            serra= (Serra) getIntent().getSerializableExtra("serra");
        }else{
            String strJsonserra = preferences.getString("JsonSerra","0");//second parameter is necessary ie.,Value to return if this preference does not exist.
            System.out.println ("sono qui");
            if(strJsonserra.equals ("0")){
            }else{
                serra= new Gson().fromJson(strJsonserra, new TypeToken<Serra> (){}.getType());
                System.out.println(""+serra.toString ());
            }

        }
        ClickTastoHome();
        tastinavigazione();
        load();
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
                Gson gson = new GsonBuilder ().create();
                JsonObject JsonSerra = gson.toJsonTree(serra).getAsJsonObject ();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString ("JsonSerra",JsonSerra.toString ());
                editor.apply();
                switch(item.getItemId()){
                    case R.id.nav_info:
                        Toast.makeText(Info.this,"Sei già su Info", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_reg:
                        Intent openReg = new Intent(Info.this,Registro.class);
                        startActivity(openReg);
                        break;
                    case R.id.nav_ana:
                        Intent openAna = new Intent(Info.this,Analisi.class);
                        startActivity(openAna);
                        break;
                }

                return true;
            }
        });
    }

    public void load() {

        EditText info_nome = findViewById(R.id.info_nome);
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
