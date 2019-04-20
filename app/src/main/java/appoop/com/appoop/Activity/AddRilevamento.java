package appoop.com.appoop.Activity;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
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
    public static boolean isDateRilevamento=false;
    Intent openREG;
    static TextView info_data;
    EditText Lentrata;
    EditText Luscita;
    EditText EC;
    TextView titolo;
    Button button;
    public AddRilevamento() {

        rilevamento= new Rilevamento ();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_addrilevamento);
        nomeserra=getIntent().getStringExtra ("nomeserra");
        Lentrata = findViewById(R.id.edit_Lentrata);
        Luscita= findViewById (R.id.edit_sgrondo);
        EC=findViewById (R.id.edit_EC);
        info_data=findViewById (R.id.info_data);
        titolo = findViewById(R.id.textView11);
        addListenerOnButtonClick();
        if(Registro.isModify==true){
            Registro.isModify=false;
            rilevamento=(Rilevamento)getIntent ().getSerializableExtra ("modifyrilevamento");
            titolo.setText ("Modifica rilevamento "+rilevamento.getSerra ());
            titolo.setTextSize(22);
            Lentrata.setText (rilevamento.L_entrataToString ());
            Luscita.setText (rilevamento.L_sgrondoToString ());
            EC.setText (rilevamento.EC_sgrondoToString ());
            info_data.setText (rilevamento.dataToString ());
            button.setText ("modifica rilevamento");

        }
        else{
            titolo.setText("Nuovo rilevamento" + " " + nomeserra);
            titolo.setTextSize(22);
        }

    }
    public void addListenerOnButtonClick(){
        button =  findViewById(R.id.button_creaRil);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(data==null||Lentrata.getText().toString ().length ()==0||Luscita.getText().toString ().length ()==0 ||EC.getText().toString ().length ()==0){
            new AlertDialog.Builder(AddRilevamento.this)
                    .setTitle("Alcuni campi sono vuoti")
                    .setMessage("E' necessario riempire tutti i campi")

                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
        else{
            rilevamento.setSerra (nomeserra);
            rilevamento.setL_entrata(Double.valueOf(Lentrata.getText().toString ())) ;
            rilevamento.setL_sgrondo (Double.valueOf(Luscita.getText().toString ())) ;
            rilevamento.setEC_sgrondo (Double.valueOf(EC.getText().toString ()));

            rilevamento.setData (data);
            db=new DBadapter ();
            db.PutRilevamento (this,rilevamento);
            System.out.println ("rilevamento ricevuto: "+rilevamento.toString ());
            openREG  = new Intent (AddRilevamento.this, Registro.class);
            openREG.putExtra ("nomeserra",nomeserra);
            startActivity (openREG);
        }


    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment ();
        isDateRilevamento=true;
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static void setData(String Sdata) {
        try {
            info_data.setText (Sdata);
            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            data = formatoData.parse(Sdata);
        } catch (ParseException e) {
            e.printStackTrace ( );
        }
    }
}
