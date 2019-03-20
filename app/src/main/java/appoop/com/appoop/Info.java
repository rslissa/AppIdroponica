package appoop.com.appoop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

public class Info extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public String nome;
    public int m2;
    public String coltura;
    public String varieta;
    public int piante;
    public Date trapianto;
    public double LOentrata;
    public double LOsgrondo;
    public double TargetEC;

    ArrayList<Serra> serre;
    ArrayList<String> nomiSerre;
    public Serra serra;

    public Info(String nome, int m2, String coltura, String varieta, Date trapianto, double LOentrata, double LOsgrondo, double targetEC, ArrayList<Serra> serre, ArrayList<String> nomiSerre, Serra serra) {
        this.nome = nome;
        this.m2 = m2;
        this.coltura = coltura;
        this.varieta = varieta;
        this.trapianto = trapianto;
        this.LOentrata = LOentrata;
        this.LOsgrondo = LOsgrondo;
        TargetEC = targetEC;
        this.serre = serre;
        this.nomiSerre = nomiSerre;
        this.serra = new Serra();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public String getColtura() {
        return coltura;
    }

    public void setColtura(String coltura) {
        this.coltura = coltura;
    }

    public String getVarieta() {
        return varieta;
    }

    public void setVarieta(String varieta) {
        this.varieta = varieta;
    }

    public Date getTrapianto() {
        return trapianto;
    }

    public void setTrapianto(Date trapianto) {
        this.trapianto = trapianto;
    }

    public double getLOentrata() {
        return LOentrata;
    }

    public void setLOentrata(double LOentrata) {
        this.LOentrata = LOentrata;
    }

    public double getLOsgrondo() {
        return LOsgrondo;
    }

    public void setLOsgrondo(double LOsgrondo) {
        this.LOsgrondo = LOsgrondo;
    }

    public double getTargetEC() {
        return TargetEC;
    }

    public void setTargetEC(double targetEC) {
        TargetEC = targetEC;
    }

    public ArrayList<Serra> getSerre() {
        return serre;
    }

    public void setSerre(ArrayList<Serra> serre) {
        this.serre = serre;
    }

    public ArrayList<String> getNomiSerre() {
        return nomiSerre;
    }

    public void setNomiSerre(ArrayList<String> nomiSerre) {
        this.nomiSerre = nomiSerre;
    }



    protected void onCreate(Bundle savedInstanceState) {
        //questo pezzo di codice è quello che collega l'activity_info con questa classe java
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final Button btnHome = (Button) findViewById(R.id.button_home);
        btnHome.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Intent openPage1 = new Intent(this,aggiungi.class);
        startActivity(openPage1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
