package appoop.com.appoop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class aggiungi extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Serra> serre;
    Serra serra1;
    Serra serra2;
    Serra serra3;
    Date data=null;
    String s;
    public aggiungi() {
        serre = new ArrayList<Serra> ();


        //data una stringa la converte in un oggetto di tipo date
        try{
            s="01/01/2019";
            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            formatoData.setLenient(false);
            data = formatoData.parse(s);
        } catch (ParseException e) {
            System.out.println("Formato data non valido.");
        }


        serra1=new Serra("c1 nord","1000","melanzana","bianca",data ,24.0,12.5,4.7);
        serra2=new Serra("c1 sud","2000","melanzana","nera",data ,16.0,15.5,5.0);
        serra3=new Serra("c2","15000","pomodoro","ciliegino",data ,12.0,12.5,4.7);

        serre.add (serra1);
        serre.add(serra2);
        serre.add(serra3);

        for(Iterator<Serra> i= serre.iterator (); i.hasNext ();){
            Serra s=i.next ();
            System.out.println(s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_aggiungi);
        final Button button = (Button) findViewById(R.id.button_crea);
        button.setOnClickListener(this);
    }

    public void onClick(View v) {

        EditText nameField = (EditText) findViewById(R.id.editText_nome);
        serra1.setSerra ( nameField.getText().toString());
        nameField = (EditText) findViewById(R.id.editText_m2);
        serra1.setM2 ( nameField.getText().toString());
        nameField = (EditText) findViewById(R.id.editText3_coltura);
        serra1.setColtura ( nameField.getText().toString());
        nameField = (EditText) findViewById(R.id.editText4_varieta);
        serra1.setVarieta ( nameField.getText().toString());
        try{
            nameField = (EditText) findViewById(R.id.editText_trapianto);
            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            data = formatoData.parse(nameField.getText().toString());
            formatoData.setLenient(false);
            serra1.setTrapianto (data);
        } catch (ParseException e) {
            System.out.println("Formato data non valido.");
        }
        nameField = (EditText) findViewById(R.id.editText_LOin);
        serra1.setLOentrata (Double.valueOf (nameField.getText().toString()));
        nameField = (EditText) findViewById(R.id.editText_LOout);
        serra1.setLOsgrondo (Double.valueOf (nameField.getText().toString()));
        nameField = (EditText) findViewById(R.id.editText_EC);
        serra1.setTargetEC (Double.valueOf (nameField.getText().toString()));
        serre.add (serra1);

        for(Iterator<Serra> i= serre.iterator (); i.hasNext ();){
            Serra s=i.next ();
            System.out.println(s);
        }
    }

}
