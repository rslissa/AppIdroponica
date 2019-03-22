package appoop.com.appoop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;



public class Info extends Activity implements Serializable {
    Serra serra;
    ArrayList<String> nomiSerre;
    public Info() {
        nomiSerre=new ArrayList ();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        // During initial setup, plug in the details fragment.
        if (savedInstanceState == null) {

            serra= (Serra) getIntent().getSerializableExtra("serra");
            nomiSerre.addAll ((ArrayList)getIntent().getSerializableExtra("nomiSerre"));
        }
        ClickTastoHome();
        load();
    }

    public void ClickTastoHome(){
        final Button btnHome = (Button) findViewById(R.id.button_home);
        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent openAggiungi = new Intent(Info.this,aggiungi.class);
                startActivity(openAggiungi);
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
