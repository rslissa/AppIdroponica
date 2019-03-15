package appoop.com.appoop;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.util.Locale;

public class aggiungi extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Serra> serre;
    Serra serra1;
    Serra serra2;
    Serra serra3;
    public static Date data=null;
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

        EditText Tnome = findViewById(R.id.editText_nome);
        EditText Tm2 = findViewById(R.id.editText_m2);
        EditText Tcoltura = findViewById(R.id.editText3_coltura);
        EditText Tvarieta = findViewById(R.id.editText4_varieta);
        EditText TLOin = findViewById(R.id.editText_LOin);
        EditText TLOout = findViewById(R.id.editText_LOout);
        EditText TEC = findViewById(R.id.editText_EC);

        serra1.setSerra (Tnome.getText().toString());
        serra1.setM2 ( Tm2.getText().toString());
        serra1.setColtura ( Tcoltura.getText().toString());
        serra1.setVarieta ( Tvarieta.getText().toString());
        serra1.setTrapianto (data);
        serra1.setLOentrata (Double.valueOf (TLOin.getText().toString()));
        serra1.setLOsgrondo (Double.valueOf (TLOout.getText().toString()));
        serra1.setTargetEC (Double.valueOf (TEC.getText().toString()));
        serre.add (serra1);

        for(Iterator<Serra> i= serre.iterator (); i.hasNext ();){
            Serra s=i.next ();
            System.out.println(s);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static void setData(String Sdata) {
        try {

            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            data = formatoData.parse(Sdata);
        } catch (ParseException e) {
            e.printStackTrace ( );
        }
    }

}
