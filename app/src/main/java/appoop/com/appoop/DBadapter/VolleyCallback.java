package appoop.com.appoop.DBadapter;

import java.util.ArrayList;

import appoop.com.appoop.modelli.Rilevamento;
import appoop.com.appoop.modelli.Serra;

public interface VolleyCallback {
    void onSuccessGNS(ArrayList <String>ns); //onsuccess getnomeserra
    void onSuccessGS(Serra s);              //onsuccess getserra
    void onSuccessGR(ArrayList<Rilevamento> lr); //onsuccess getrilevamenti
}
