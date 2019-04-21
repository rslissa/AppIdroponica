package appoop.com.appoop.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import appoop.com.appoop.DBadapter.DBadapter;
import appoop.com.appoop.DBadapter.VolleyCallback;
import appoop.com.appoop.R;
import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.modelli.Serra;


public class ModifyRilevamento extends AppCompatActivity {
    DBadapter db;
    Rilevamento rilevamento;
    Intent openReg;
    TextView nome;
    EditText Lin;
    EditText Lout;
    EditText EC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_modify_rilevamento);
        db=new DBadapter ();
        rilevamento=new Rilevamento ();
        rilevamento=(Rilevamento) getIntent ().getSerializableExtra ("modifyrilevamento");
        nome=findViewById (R.id.TextView_mr_rilevamento);
        Lin=findViewById (R.id.EditText_mr_Lentrata);
        Lout=findViewById (R.id.edit_mr_sgrondo);
        EC=findViewById (R.id.edit_mr_EC);
        nome.setText ("Modifica Rilevamento "+rilevamento.getSerra ());
        Lin.setText (rilevamento.L_entrataToString ());
        Lout.setText (rilevamento.L_sgrondoToString ());
        EC.setText (rilevamento.EC_sgrondoToString ());
        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick(){
        final Button button =  findViewById(R.id.button_modRil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Lin.getText().toString ().length ()==0||Lout.getText().toString ().length ()==0 ||EC.getText().toString ().length ()==0){
                    new AlertDialog.Builder(ModifyRilevamento.this)
                            .setTitle("Alcuni campi sono vuoti")
                            .setMessage("E' necessario riempire tutti i campi")

                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
                else{
                    rilevamento.setL_entrata (Double.valueOf(Lin.getText().toString ()));
                    rilevamento.setL_sgrondo (Double.valueOf(Lout.getText().toString ())) ;
                    rilevamento.setEC_sgrondo (Double.valueOf(EC.getText().toString ()));
                    db.ModifyRilevamento (ModifyRilevamento.this,rilevamento);
                    System.out.println ("rilevamento modificato: "+rilevamento);
                    openReg  = new Intent(ModifyRilevamento.this, Registro.class);
                    openReg.putExtra("nomeserra",rilevamento.getSerra ());
                    Toast.makeText (ModifyRilevamento.this, " rilevamento modificato", Toast.LENGTH_SHORT).show ( );
                    finish();
                    startActivity (openReg);
                }
            }
        });
    }
}