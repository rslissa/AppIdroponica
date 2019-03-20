package appoop.com.appoop;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import java.util.Locale;

public class aggiungi extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Serra> serre;
    ArrayList<String> nomiSerre;
    Serra serra;
    public static Date data=null;
    Spinner spinner;
    public aggiungi() {
        nomiSerre=new ArrayList<String> ();
        serre=new ArrayList<Serra> ();
        serra=new Serra();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_aggiungi);
        addListenerOnButtonClick();
        addListenerOnSpinnerItemSelection ();

    }

    public void addItemsOnSpinner(){

        nomiSerre.removeAll(Collections.singleton(null));

            spinner =  findViewById(R.id.spinner);
            System.out.println ("ciao"+nomiSerre.size());
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomiSerre);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);


    }
    public void addListenerOnSpinnerItemSelection(){
        spinner =  findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

    public void addListenerOnButtonClick(){
        final Button button =  findViewById(R.id.button_crea);
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

        serra.setSerra (Tnome.getText().toString());
        serra.setM2 ( Tm2.getText().toString());
        serra.setColtura ( Tcoltura.getText().toString());
        serra.setVarieta ( Tvarieta.getText().toString());
        serra.setTrapianto (data);
        serra.setLOentrata (Double.valueOf (TLOin.getText().toString()));
        serra.setLOsgrondo (Double.valueOf (TLOout.getText().toString()));
        serra.setTargetEC (Double.valueOf (TEC.getText().toString()));

        //controllo che il nome sia univoco
 /*       for(Iterator<String> i=nomiSerre.iterator ();i.hasNext ();){
            String temp=i.next ();
            if(serra.getSerra ()==temp){

                new AlertDialog.Builder(this)
                        .setTitle("Delete entry")
                        .setMessage("è già stata creata una serra con questo nome, cambia nome o elimina la serra precedente")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }else{

            }

        }
*/
        serre.add (serra);
        nomiSerre.add(serra.getSerra ());
        addItemsOnSpinner();
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
