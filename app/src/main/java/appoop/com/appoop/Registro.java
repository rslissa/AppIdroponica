package appoop.com.appoop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

        //codice per l'implementazione della GridView
        String[] citta = new String[]{"Pisa","Firenze","Bologna","Modena","Milano"}; //risorsa dalla quale il registro prende i dati
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row,citta);
        GridView gridView01 = (GridView) findViewById(R.id.reg_dynamic);
        gridView01.setAdapter(adapter);

        gridView01.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,long id) {
                System.out.println("elemento selezionato");
            }
        });

        final Button btnHome = (Button) findViewById(R.id.btn_home);
        btnHome.setOnClickListener(this);

        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent openPage1 = new Intent(Registro.this,aggiungi.class);
                startActivity(openPage1);
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