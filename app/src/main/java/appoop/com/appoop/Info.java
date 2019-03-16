package appoop.com.appoop;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

public class Info extends Activity {

    public String nome;
    public int m2;
    public String coltura;
    public String varieta;
    public int piante;
    public Date trapianto;
    public double LOentrata;
    public double LOsgrondo;
    public double TargetEC;

    // ArrayList<Serra> serre;
    //ArrayList<String> nomiSerre;
    //Serra serra;


    //questo pezzo di codice è quello che collega l'activity con questa classe java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }


}
