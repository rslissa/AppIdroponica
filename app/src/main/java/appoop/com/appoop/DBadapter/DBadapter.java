package appoop.com.appoop.DBadapter;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
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


public class DBadapter extends Thread{

    private static final String URL_GETSERRE = "http://www.appidroponica.altervista.org/GetSerre.php ";
    private static final String URL_GETNOMESERRE = "http://www.appidroponica.altervista.org/GetNomeSerre.php ";
    private static final String URL_PUTSERRA = "http://www.appidroponica.altervista.org/PutSerra.php ";
    private static final String URL_PUTRILEVAMENTO = "http://www.appidroponica.altervista.org/PutRilevamento.php ";
    private static final String URL_DELETERILEVAMENTO = "http://www.appidroponica.altervista.org/DeleteRilevamento.php ";
    private static final String URL_GETSERRA = "http://www.appidroponica.altervista.org/GetSerra.php ";
    private static final String URL_GETRILEVAMENTI = "http://www.appidroponica.altervista.org/GetRilevamenti.php ";




    public ArrayList<Serra> GetSerre(Context context) {
     final ArrayList serre = new ArrayList<> ( );
        StringRequest stringRequest = new StringRequest (Request.Method.GET, URL_GETSERRE,
                new Response.Listener<String> ( ) {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray (response);

                            //traversing through all the object
                            for (int i = 0; i < array.length ( ); i++) {

                                JSONObject product = array.getJSONObject (i);
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                Date trapianto = null;
                                try {
                                    trapianto = sdf.parse(product.getString ("trapianto"));
                                } catch (ParseException e) {
                                    e.printStackTrace ( );
                                }
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

        for (int i = 0; i < serre.size ( ); i++) {
            System.out.println ("serra fuori try: " + serre.get (i).toString ( ));
        }
        return serre;
    }

    public void GetNomeSerre(Context context, final VolleyCallback callback) {
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
                            callback.onSuccessGNS (nomiserre);
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

        Volley.newRequestQueue (context).add (stringRequest);

    }


    public void GetSerra(Context context,String nomeserra,final VolleyCallback callback) {
        final Serra serra = new Serra ();
        final String ns = nomeserra;
        StringRequest stringRequest = new StringRequest (Request.Method.POST, URL_GETSERRA, new Response.Listener<String> ( ) {
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date trapianto = null;
                        System.out.println ("stampa dioporco: "+(product.getString ("trapianto")));
                        try {
                            trapianto = sdf.parse(product.getString ("trapianto"));
                        } catch (ParseException e) {
                            e.printStackTrace ( );
                        }
                        serra.setSerra (product.getString ("nome"));
                        serra.setM2 (product.getString ("m2"));
                        serra.setColtura (product.getString ("coltura"));
                        serra.setVarieta (product.getString ("varieta"));
                        serra.setTrapianto (trapianto);
                        serra.setLOentrata (product.getDouble ("LOin"));
                        serra.setLOsgrondo (product.getDouble ("LOout"));
                        serra.setTargetEC (product.getDouble ("targetEC"));


                        callback.onSuccessGS (serra);
                    }

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
    }
    public void GetRilevamenti(Context context,String nomeserra,final VolleyCallback callback) {
        final ArrayList<Rilevamento> rilevamenti=new ArrayList<> ();
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        final String ns=nomeserra;

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL_GETRILEVAMENTI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //converting the string to json array object
                    JSONArray array = new JSONArray (response);

                    for (int i = 0; i < array.length ( ); i++) {

                        JSONObject product = array.getJSONObject (i);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date trapianto = null;
                        try {
                            trapianto = sdf.parse(product.getString ("data"));
                        } catch (ParseException e) {
                            e.printStackTrace ( );
                        }
                        rilevamenti.add (
                                new Rilevamento (
                                        product.getString ("nome"),
                                        trapianto,
                                        product.getDouble ("Lentrata"),
                                        product.getDouble ("Lsgrondo"),
                                        product.getDouble ("ECsgrondo")
                                ));

                    }
                    callback.onSuccessGR (rilevamenti);
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

    public void DeleteRilevamento(Context context,Rilevamento r){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        final Rilevamento rilevamento=r;
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL_DELETERILEVAMENTO, new Response.Listener<String>() {
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
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }

}
