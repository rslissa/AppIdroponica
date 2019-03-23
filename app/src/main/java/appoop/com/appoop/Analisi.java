package appoop.com.appoop;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

public class Analisi extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisi);

        BottomNavigationView btnNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_info:
                        Toast.makeText(Analisi.this,"Info page selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_reg:
                        Toast.makeText(Analisi.this,"Register page selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_ana:
                        Toast.makeText(Analisi.this,"Analysis page selected", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });

    }

}
