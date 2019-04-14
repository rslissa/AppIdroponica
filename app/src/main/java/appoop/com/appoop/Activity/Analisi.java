package appoop.com.appoop.Activity;


import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import appoop.com.appoop.DBadapter.DBadapter;
import appoop.com.appoop.R;
import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.modelli.Serra;


import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import appoop.com.appoop.altro.DatePickerFragment;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import static appoop.com.appoop.Activity.aggiungi.setData;

public class Analisi extends AppCompatActivity implements View.OnClickListener {

    private GraphicalView nChart;
    private XYMultipleSeriesDataset nDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer nRenderer = new XYMultipleSeriesRenderer(2);

    private XYSeries nCurrentSeries;
    private XYSeriesRenderer nCurrentRenderer;

    private XYSeries ecCurrentSeries;
    private XYSeriesRenderer ecCurrentRender;

    public ArrayList<Rilevamento> rilevamenti = new ArrayList<>(3);
    public Rilevamento r1;
    public Rilevamento r2;
    public Rilevamento r3;

    public Serra s;
    Serra serra;

    private static final String FORMAT = "dd/MM/yyyy"; //variabile per la conversione di date in stringa

    public DatePickerFragment period = new DatePickerFragment();

    String nomeserra;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisi);
        nomeserra = getIntent().getStringExtra("nomeserra");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        tastinavigazione();


        Date now = Calendar.getInstance().getTime();
        r1 = new Rilevamento("serra1", now, 2.1, 3.2, 4.56);
        r2 = new Rilevamento("serra1", now, 2.5, 2.8, 5);
        r3 = new Rilevamento("serra1", now, 3, 3.2, 4.38);

        rilevamenti.add(r1);
        rilevamenti.add(r2);
        rilevamenti.add(r3);

        s = new Serra("serra1", "12", "melanzane", "bianche", now, 2.0, 2.4, 4.3);


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.item1:
                //prendere una data con il picker ed impostarla come data inizio per il grafico
                period.show(getSupportFragmentManager(), "datePicker");
                //settare la data
                break;
            case R.id.item2:
                Intent goToAddNewSerra = new Intent(Analisi.this, aggiungi.class);
                startActivity(goToAddNewSerra);
                break;
        }

        return true;
    }

    public void tastinavigazione() {
        BottomNavigationView btnNav = findViewById(R.id.bottom_navigation);
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_info:
                        Intent openInfo = new Intent(Analisi.this, Info.class);
                        openInfo.removeExtra("ActivityKey");
                        openInfo.putExtra("ActivityKey", "Analisi");
                        startActivity(openInfo);
                        break;
                    case R.id.nav_reg:
                        Intent openReg = new Intent(Analisi.this, Registro.class);
                        openReg.removeExtra("ActivityKey");
                        openReg.putExtra("ActivityKey", "Analisi");
                        startActivity(openReg);
                        break;
                    case R.id.nav_ana:
                        Toast.makeText(Analisi.this, "Sei gi? su analisi", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }

    private void initChart() {
        //funzione 1
        nCurrentSeries = new XYSeries("Analisi rilevamenti");
        nDataset.addSeries(nCurrentSeries);
        nCurrentRenderer = new XYSeriesRenderer();
        nRenderer.addSeriesRenderer(nCurrentRenderer);

        //funzione 2
        ecCurrentSeries = new XYSeries("Andamento EC");
        nDataset.addSeries(ecCurrentSeries);
        ecCurrentRender = new XYSeriesRenderer();
        nRenderer.addSeriesRenderer(ecCurrentRender);

        //impostazioni funzione 1
        nCurrentRenderer.setLineWidth(5);
        nCurrentRenderer.setDisplayChartValues(true);
        nCurrentRenderer.setPointStyle(PointStyle.CIRCLE);
        nCurrentRenderer.setFillPoints(true);
        nCurrentRenderer.setChartValuesTextSize(35);
        nCurrentRenderer.setChartValuesSpacing(20);

        //impostazioni funzione 2
        ecCurrentRender.setLineWidth(5);
        ecCurrentRender.setLineWidth(5);
        ecCurrentRender.setDisplayChartValues(true);
        ecCurrentRender.setPointStyle(PointStyle.CIRCLE);
        ecCurrentRender.setFillPoints(true);
        ecCurrentRender.setChartValuesTextSize(35);
        ecCurrentRender.setChartValuesSpacing(20);
        ecCurrentRender.setColor(Color.GREEN);


        //impostazioni grafico complessivo

        nRenderer.setYAxisMax(6);
        nRenderer.setLegendHeight(200);
        nRenderer.setFitLegend(false);
        nRenderer.setMargins(new int[]{80, 100, 100, 0});
        nRenderer.setLegendTextSize(35);
        nRenderer.setAxisTitleTextSize(30);
        nRenderer.setChartTitleTextSize(40);
        nRenderer.setPointSize(8);
        nRenderer.setShowGrid(true);
        nRenderer.setPanEnabled(false);
        nRenderer.setLabelsTextSize(35);
        nRenderer.setYLabelsAlign(Paint.Align.RIGHT); //posizione numeri sulla sinistra
        nRenderer.setMarginsColor(Color.WHITE);
        nRenderer.setLabelsColor(Color.BLACK);
        //nRenderer.setXTitle("GIORNI");
        nRenderer.setYTitle("VALORI");
        nRenderer.setShowLegend(true);//elimina la parte inferiore permettendo al grafico di stare a tuttoschermo
        nRenderer.setXLabels(0); //nasconde le labels dell'asse x
        nRenderer.setInScroll(true);


        for (Iterator<Rilevamento> iter = rilevamenti.iterator(); iter.hasNext(); ) {
            Rilevamento element = iter.next();

            nRenderer.setChartTitle(element.serra);//titolo per il grafico (modificare la posizione?)

            //tramite questa funzione ottengo un punto del grafico
            double func = ((element.L_sgrondo / element.L_entrata) / (s.LOentrata / s.LOsgrondo));
            //func = (double) (Math.round(func*10)/10);

            nCurrentSeries.add(rilevamenti.indexOf(element), func);

            //converto la data del rilevamento in una stringa
            DateFormat df = new SimpleDateFormat(FORMAT);
            String strDate = df.format(element.data);

            nRenderer.addXTextLabel(rilevamenti.indexOf(element), strDate);//da implemtare la data del rilevamento
            nRenderer.setXTitle("Il periodo selezionato parte dal" + strDate);

            ecCurrentSeries.add(rilevamenti.indexOf(element), element.EC_sgrondo);

        }

    }

    protected void onResume() {

        super.onResume();
        LinearLayout layout = findViewById(R.id.chart);
        if (nChart == null) {
            initChart();
            nChart = ChartFactory.getCubeLineChartView(this, nDataset, nRenderer, 0);
            //layout.addView(nChart);
            layout.addView(nChart, new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1800));
        } else {
            nChart.repaint();
        }
    }

    protected void onChange() {
    }

}

