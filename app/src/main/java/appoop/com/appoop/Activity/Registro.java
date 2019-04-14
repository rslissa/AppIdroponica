package appoop.com.appoop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import appoop.com.appoop.DBadapter.DBadapter;
import appoop.com.appoop.DBadapter.VolleyCallback;
import appoop.com.appoop.altro.ClickListener;
import appoop.com.appoop.R;
import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.altro.RilevamentoAdapter;
import appoop.com.appoop.modelli.Serra;

public class Registro extends AppCompatActivity implements View.OnClickListener, ClickListener {
    private FrameLayout registro;
    private FloatingActionButton fab;
    private RecyclerView recycler;
    public RilevamentoAdapter rAdapter;
    String nomeserra;
    DBadapter db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nomeserra=getIntent().getStringExtra ("nomeserra");

        //setta il titolo dell'activity
        TextView titolo = findViewById(R.id.textView10);
        titolo.setText("Registro" + " " + nomeserra);
        titolo.setTextSize(22);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        tastinavigazione();
        db=new DBadapter ();
        db.GetRilevamenti (this,nomeserra,new VolleyCallback ( ) {
            @Override
            public void onSuccessGNS(ArrayList ns) {
                //do nothing
            }

            @Override
            public void onSuccessGS(Serra s) {
                //do nothing
            }

            @Override
            public void onSuccessGR(ArrayList<Rilevamento> lr) {
                System.out.println ("lista rilevamenti: "+lr.toString ());
                registro = findViewById(R.id.RilevamentiFrame);
                registro.setVisibility(View.VISIBLE);
                recycler = findViewById(R.id.RilevamentiList);
                LinearLayoutManager llm = new LinearLayoutManager(Registro.this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recycler.setLayoutManager(llm);
                rAdapter = new RilevamentoAdapter (lr);
                rAdapter.setClickListener(Registro.this);
                recycler.setAdapter(rAdapter);
            }
        });




        fab = findViewById(R.id.addRilevamento);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addriv = new Intent(getBaseContext(), AddRilevamento.class);
                addriv.putExtra ("nomeserra",nomeserra);
                startActivity(addriv);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void tastinavigazione(){
        BottomNavigationView btnNav = findViewById(R.id.bottom_navigation);
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_info:
                        Intent openInfo = new Intent(Registro.this,Info.class);
                        openInfo.putExtra ("nomeserra",nomeserra);
                        startActivity(openInfo);
                        break;
                    case R.id.nav_reg:
                        Toast.makeText(Registro.this,"Sei già su registro", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_ana:
                        Intent openAna = new Intent(Registro.this,Analisi.class);
                        openAna.putExtra ("nomeserra",nomeserra);
                        startActivity(openAna);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void itemClicked(View view, final int position) {
        //deleteVoto(position);
        PopupMenu pm = new PopupMenu(this,view);
        pm.inflate(R.menu.context_menu);
        pm.show();
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.context_menu_delete:
                        deleteRilevamento (position);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }


    public void deleteRilevamento(int pos){
        ArrayList <Rilevamento>lr=new ArrayList (rAdapter.getRilevamentoList ());
        Rilevamento deleterilevamento=lr.get (pos);
        db.DeleteRilevamento (this,deleterilevamento);
        Toast.makeText(this,"rilevamento eliminato",Toast.LENGTH_SHORT).show();
        Intent openReg = new Intent(Registro.this,Registro.class);
        openReg.putExtra ("nomeserra",nomeserra);
        startActivity(openReg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.item2:
                Intent goToAddNewSerra = new Intent(this, aggiungi.class);
                startActivity(goToAddNewSerra);
                break;
        }

        return true;
    }
}
