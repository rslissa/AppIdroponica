package appoop.com.appoop.Activity;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import appoop.com.appoop.DBadapter.DBadapter;
import appoop.com.appoop.DBadapter.VolleyCallback;
import appoop.com.appoop.R;
import appoop.com.appoop.altro.DatePickerFragment;
import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.modelli.Serra;

public class ModifySerra extends AppCompatActivity  {
    public static Date data=null;
    public static boolean isDateModify=false;
    Serra serra;
    String serravecchia;
    DBadapter db;
    TextView titolo;
    EditText nome;
    EditText m2;
    EditText coltura;
    EditText varieta;
    EditText LOin;
    EditText LOout;
    EditText EC;
    Intent openInfo;
    static TextView info_data;
    public ModifySerra() {
        db=new DBadapter ();
        serra=new Serra();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_modify_serra);

        serra=(Serra)getIntent ().getSerializableExtra ("modifyserra");

        titolo=findViewById (R.id.titoloModify);
        nome=findViewById (R.id.edtm_nome);
        m2=findViewById (R.id.edtm_m2);
        coltura=findViewById (R.id.edtm_coltura);
        varieta=findViewById (R.id.edtm_varieta);
        info_data=findViewById (R.id.info_dataM);
        LOin=findViewById (R.id.edtm_LOin);
        LOout=findViewById (R.id.edtm_LOout);
        EC=findViewById (R.id.edtm_ec);
        titolo.setText ("Modifica Serra "+serra.getSerra ());
        serravecchia=serra.getSerra ();
        nome.setText (serra.getSerra ());
        m2.setText (serra.getM2 ());
        coltura.setText (serra.getColtura ());
        varieta.setText (serra.getVarieta ());
        info_data.setText (serra.TrapiantoToString ());
        data=serra.getTrapianto ();
        LOin.setText (serra.LOinToString ());
        LOout.setText (serra.LOoutToString ());
        EC.setText (serra.TargetECToString ());
        addListenerOnButtonClick();


    }
    public void addListenerOnButtonClick(){
        final Button button =  findViewById(R.id.button_ModificaSerra);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(data==null||nome.getText().toString().length ()==0 ||m2.getText().toString().length ()==0 ||
                        coltura.getText().toString().length ()==0||LOin.getText().toString().length ()==0
                        ||varieta.getText().toString().length ()==0
                        ||LOout.getText().toString().length ()==0||EC.getText().toString().length ()==0){
                    new AlertDialog.Builder(ModifySerra.this)
                            .setTitle("Alcuni campi sono vuoti")
                            .setMessage("E' necessario riempire tutti i campi")
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else{
                    db.GetNomeSerre (ModifySerra.this,new VolleyCallback ( ) {
                        @Override
                        public void onSuccessGNS(ArrayList <String> ns) {
                            List nomiserre=new ArrayList<> (ns);
                            String temp=nome.getText ().toString ();
                            int t=0;
                            for(int i=0;i<nomiserre.size ();++i){
                                if(temp.equals (nomiserre.get (i)) && !serra.getSerra ().equals (nomiserre.get(i))){
                                    t=1;
                                }
                            }
                            if(t==1){
                                new AlertDialog.Builder(ModifySerra.this)
                                        .setTitle("Esiste già una serra con questo nome")
                                        .setMessage("E' necessario selezionare un altro nome")
                                        .setNegativeButton(android.R.string.no, null)
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                            else{
                                serra.setSerra (nome.getText ().toString ());
                                serra.setM2 ( m2.getText().toString());
                                serra.setColtura ( coltura.getText().toString());
                                serra.setVarieta ( varieta.getText().toString());
                                serra.setTrapianto (data);
                                serra.setLOentrata (Double.valueOf (LOin.getText().toString()));
                                serra.setLOsgrondo (Double.valueOf (LOout.getText().toString()));
                                serra.setTargetEC (Double.valueOf (EC.getText().toString()));
                                db.ModifySerra (ModifySerra.this,serra,serravecchia);
                                openInfo  = new Intent(ModifySerra.this, Info.class);
                                openInfo.putExtra("nomeserra",serra.getSerra ());
                                Toast.makeText (ModifySerra.this, " serra "+serra.getSerra ()+" modificata", Toast.LENGTH_SHORT).show ( );
                                finish();
                                startActivity (openInfo);
                            }


                        }

                        @Override
                        public void onSuccessGS(Serra s) {
                            //do nothing
                        }

                        @Override
                        public void onSuccessGR(ArrayList<Rilevamento> lr) {
                            //do nothing
                        }
                    });

                }

            }
        });
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment ();
        isDateModify=true;
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public static void setData(String Sdata) {
        try {
            info_data.setText (Sdata);
            DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
            data= formatoData.parse(Sdata);

        } catch (ParseException e) {
            e.printStackTrace ( );
        }
    }
}
