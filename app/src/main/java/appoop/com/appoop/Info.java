package appoop.com.appoop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;


public class Info extends Activity implements Serializable {
    Serra serra;

    public Info() {
        //serra= (Serra) getIntent ().getSerializableExtra("serra");
        serra=new Serra("c1","fds","asd","sdfd",null,1.0,1.0,1.0);
    }

    protected void onCreate(Bundle savedInstanceState) {
        //questo pezzo di codice è quello che collega l'activity_info con questa classe java
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final Button btnHome = (Button) findViewById(R.id.button_home);

        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent openPage1 = new Intent(Info.this,aggiungi.class);
                startActivity(openPage1);
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

        info_nome.setText(serra.getSerra().toString());
        info_m2.setText(serra.getM2().toString());
        info_coltura.setText(serra.getColtura().toString());
        info_varieta.setText(serra.getVarieta().toString());
        info_data.setText(serra.getTrapianto().toString());
        info_entrata.setText(serra.getLOentrata().toString());
        info_uscita.setText(serra.getLOsgrondo().toString());
        info_ec.setText(serra.getTargetEC().toString());

    }

}
