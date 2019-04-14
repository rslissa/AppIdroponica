package appoop.com.appoop.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import appoop.com.appoop.DBadapter.DBadapter;
import appoop.com.appoop.altro.DatePickerFragment;
import appoop.com.appoop.R;
import appoop.com.appoop.modelli.Rilevamento;

public class AddRilevamento extends AppCompatActivity implements View.OnClickListener {
    Rilevamento rilevamento;
    String nomeserra;
    DBadapter db;
    public static Date data=null;
    Intent openREG;
    public AddRilevamento() {
         rilevamento= new Rilevamento ();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_addrilevamento);
        nomeserra=getIntent().getStringExtra ("nomeserra");

        //setta il titolo dell'activity
        TextView titolo = findViewById(R.id.textView11);
        titolo.setText("Nuovo rilevamento" + " " + nomeserra);
        titolo.setTextSize(22);

        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick(){
        final FloatingActionButton button =  findViewById(R.id.button_crea);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText Lentrata = findViewById(R.id.edit_Lentrata);
        EditText Luscita= findViewById (R.id.edit_sgrondo);
        EditText EC=findViewById (R.id.edit_EC);
        rilevamento.setSerra (nomeserra);
        rilevamento.setL_entrata(Double.valueOf(Lentrata.getText().toString ())) ;
        rilevamento.setL_sgrondo (Double.valueOf(Luscita.getText().toString ())) ;
        rilevamento.setEC_sgrondo (Double.valueOf(EC.getText().toString ()));

        if(data==null){
            String pattern = "dd/MM/yyyy";
            DateFormat df = new SimpleDateFormat (pattern);
            data= df.getCalendar ().getInstance().getTime();
            System.out.println (""+data);

        }
        rilevamento.setData (data);
        db=new DBadapter ();
        db.PutRilevamento (this,rilevamento);
        System.out.println ("rilevamento ricevuto: "+rilevamento.toString ());
        openREG  = new Intent (AddRilevamento.this, Registro.class);
        openREG.putExtra ("nomeserra",nomeserra);
        startActivity (openREG);

    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment ();
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
