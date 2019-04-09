package appoop.com.appoop.DBadapter;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.modelli.Serra;


public class DBadapter {

    private static final String URL_GETSERRE = "http://www.appidroponica.altervista.org/GetSerre.php ";
    private static final String URL_GETNOMESERRE = "http://www.appidroponica.altervista.org/GetNomeSerre.php ";
    private static final String URL_PUTSERRA = "http://www.appidroponica.altervista.org/PutSerra.php ";
    private static final String URL_PUTRILEVAMENTO = "http://www.appidroponica.altervista.org/PutRilevamento.php ";
    private static final String URL_GETSERRA = "http://www.appidroponica.altervista.org/GetSerra.php ";
    private static final String URL_GETRILEVAMENTI = "http://www.appidroponica.altervista.org/GetRilevamenti.php ";
    ArrayList serre;
    //Serra serra2=null;
    Date trapianto=null;
    ArrayList<Rilevamento> rilevamenti;

     static Serra temp=new Serra();
    public ArrayList<Serra> GetSerre(Context context) {
        serre = new ArrayList<> ( );
        StringRequest stringRequest = new StringRequest (Request.Method.GET, URL_GETSERRE,
                new Response.Listener<String> ( ) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray (response);

                            //traversing through all the object
                            for (int i = 0; i < array.length ( ); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject (i);
                                //adding the product to product list
                                serre.add (new Serra (
                                        product.getString ("nome"),
                                        product.getString ("m2"),
                                        product.getString ("coltura"),
                                        product.getString ("varieta"),
                                        trapianto,
                                        product.getDouble ("LOin"),
                                        product.getDouble ("LOout"),
                                        product.getDouble ("targetEC")
                                ));
                            }
                            for (int i = 0; i < serre.size ( ); i++) {
                                System.out.println ("serra: " + serre.get (i).toString ( ));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace ( );
                        }
                    }
                },
                new Response.ErrorListener ( ) {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue (context).add (stringRequest);
        return serre;
    }

    public ArrayList<String> GetNomeSerre(Context context) {
        final ArrayList<String> nomiserre=new ArrayList<> ();
        StringRequest stringRequest = new StringRequest (Request.Method.GET, URL_GETNOMESERRE,
                new Response.Listener<String> ( ) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray (response);

                            //traversing through all the object
                            for (int i = 0; i < array.length ( ); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject (i);

                                nomiserre.add (
                                        product.getString ("nome")
                                );
                            }
                            for (int i = 0; i < nomiserre.size ( ); i++) {
                                System.out.println ("nomeserra dentro al try: " +nomiserre.get (i));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace ( );
                        }
                    }
                },
                new Response.ErrorListener ( ) {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        for (int i = 0; i < nomiserre.size ( ); i++) {
            System.out.println ("nomeserra fuori dal try: " +nomiserre.get (i));
        }

        //adding our stringrequest to queue
        Volley.newRequestQueue (context).add (stringRequest);
        return nomiserre;
    }

    public Serra GetSerra(Context context,String nomeserra)  {
        //RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            final String ns = nomeserra;

            StringRequest stringRequest = new StringRequest (Request.Method.POST, URL_GETSERRA, new Response.Listener<String> ( ) {

                @Override
                public void onResponse(String response) {
                        try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray (response);
                        Serra serra2=new Serra();
                        //traversing through all the object
                        for (int i = 0; i < array.length ( ); i++) {

                            //getting product object from json array
                            JSONObject product = array.getJSONObject (i);

                            //adding the product to product list
                            serra2.setSerra (product.getString ("nome"));
                            serra2.setM2 (product.getString ("m2"));
                            serra2.setColtura (product.getString ("coltura"));
                            serra2.setVarieta (product.getString ("varieta"));
                            serra2.setTrapianto (trapianto);
                            serra2.setLOentrata (product.getDouble ("LOin"));
                            serra2.setLOsgrondo (product.getDouble ("LOout"));
                            serra2.setTargetEC (product.getDouble ("targetEC"));

                            System.out.println ("ricevuto dentro al try: " + serra2.toString ( ));
                        }

                            temp=serra2;
                            temp.setSerra ("dioporco");
                    } catch (JSONException e) {
                        e.printStackTrace ( );
                    }

                }
            }, new Response.ErrorListener ( ) { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e ("VOLLEY NOT OK", error.toString ( ));

                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String> ( );
                    MyData.put ("nome", ns);
                    return MyData;
                }
            };


            Volley.newRequestQueue (context).add (stringRequest);
        try {
            Thread.sleep (1000);
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }

        System.out.println ("ricevuto fuori dal try: " + temp.toString ( ));
                return temp;


    }

    public ArrayList<Rilevamento> GetRilevamenti(Context context,String nomeserra) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        rilevamenti=new ArrayList();
        final String ns=nomeserra;

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL_GETRILEVAMENTI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //converting the string to json array object
                    JSONArray array = new JSONArray (response);

                    for (int i = 0; i < array.length ( ); i++) {

                        //getting product object from json array
                        JSONObject product = array.getJSONObject (i);
                        //adding the product to product list
                        rilevamenti.add (
                                new Rilevamento (
                                        product.getString ("nome"),
                                        trapianto,
                                        product.getDouble ("Lentrata"),
                                        product.getDouble ("Lsgrondo"),
                                        product.getDouble ("ECsgrondo")
                                ));

                    }
                    for(int i=0;i<rilevamenti.size ();++i){
                        System.out.println("rilevamenti ricevuti: "+rilevamenti.get(i));
                    }

                } catch (JSONException e) {
                    e.printStackTrace ( );
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY NOT OK", error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String> ();
                MyData.put("nome",ns);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
        return rilevamenti;
    }


    public void PutSerra(Context context,Serra s){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        final Serra serra=s;

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL_PUTSERRA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY OK", response);

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY NOT OK", error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                formatter.format(serra.getTrapianto ());
                Map<String, String> MyData = new HashMap<String, String> ();
                MyData.put("nome",serra.getSerra ());
                MyData.put("metriquadri", serra.getM2 ());
                MyData.put("coltura",serra.getColtura ());
                MyData.put("varieta",serra.getVarieta ());
                MyData.put("trapianto",formatter.format(serra.getTrapianto ()));
                MyData.put("LOentrata",serra.getLOentrata ().toString ());
                MyData.put("LOsgrondo",serra.getLOsgrondo ().toString ());
                MyData.put("TargetEC", serra.getTargetEC ().toString ());
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }

    public void PutRilevamento(Context context,Rilevamento r){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        final Rilevamento rilevamento=r;
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL_PUTRILEVAMENTO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY OK", response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY NOT OK", error.toString());
            }
        }) {
            protected Map<String, String> getParams() {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                formatter.format(rilevamento.getData ());
                Map<String, String> MyData = new HashMap<String, String> ();
                MyData.put("serra",rilevamento.getSerra ());
                MyData.put("data",formatter.format(rilevamento.getData ()));
                MyData.put("Lentrata",rilevamento.L_entrataToString());
                MyData.put("Lsgrondo",rilevamento.L_sgrondoToString());
                MyData.put("ECsgrondo",rilevamento.EC_sgrondoToString());
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }
}
