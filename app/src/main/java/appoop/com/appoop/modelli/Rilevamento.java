package appoop.com.appoop.modelli;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rilevamento implements Serializable {

    public String serra;
    public Date data;
    public double L_entrata;
    public double L_sgrondo;
    public double EC_sgrondo;

    public Rilevamento(){

    }

    public Rilevamento(String serra, Date data, double l_entrata, double l_sgrondo, double EC_sgrondo) {
        this.serra = serra;
        this.data = data;
        this.L_entrata = l_entrata;
        this.L_sgrondo = l_sgrondo;
        this.EC_sgrondo = EC_sgrondo;
    }

    public  String getSerra() {
        return serra;
    }

    public void setSerra(String serra) {
        this.serra = serra;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getL_entrata() {
        return L_entrata;
    }

    public void setL_entrata(double l_entrata) {
        L_entrata = l_entrata;
    }

    public double getL_sgrondo() {
        return L_sgrondo;
    }

    public void setL_sgrondo(double l_sgrondo) {
        L_sgrondo = l_sgrondo;
    }

    public double getEC_sgrondo() {
        return EC_sgrondo;
    }

    public void setEC_sgrondo(double EC_sgrondo) {
        this.EC_sgrondo = EC_sgrondo;
    }

    @Override
    public String toString() {
        return "Rilevamento{" +
                "serra='" + serra + '\'' +
                ", data=" + data +
                ", L_entrata=" + L_entrata +
                ", L_sgrondo=" + L_sgrondo +
                ", EC_sgrondo=" + EC_sgrondo +
                '}';
    }
    public String dataToString(){
        String pattern = "dd-MM-yyyy";
        DateFormat df = new SimpleDateFormat (pattern);
        return(df.format(getData ()));
    }

    public String L_entrataToString(){
        return (String.valueOf (getL_entrata ()));
    }
    public String L_sgrondoToString(){
        return (String.valueOf (getL_sgrondo ()));
    }
    public String EC_sgrondoToString(){
        return(String.valueOf (getEC_sgrondo ()));
    }
}
