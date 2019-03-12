package appoop.com.appoop;

import java.util.Date;

public class Serra {
    public int id;
    public String serra; //nome della serra
    public int m2;
    public String coltura;
    public String varieta;
    public Date trapianto; //data trapianto piante
    public float LOentrata; //litri/ora entrata
    public float LOsgrondo; //litri/ora uscita
    public float TargetEC; //salinità

    public Serra() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerra() {
        return serra;
    }

    public void setSerra(String serra) {
        this.serra = serra;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public String getColtura() {
        return coltura;
    }

    public void setColtura(String coltura) {
        this.coltura = coltura;
    }

    public String getVarieta() {
        return varieta;
    }

    public void setVarieta(String varieta) {
        this.varieta = varieta;
    }

    public Date getTrapianto() {
        return trapianto;
    }

    public void setTrapianto(Date trapianto) {
        this.trapianto = trapianto;
    }

    public float getLOentrata() {
        return LOentrata;
    }

    public void setLOentrata(float LOentrata) {
        this.LOentrata = LOentrata;
    }

    public float getLOsgrondo() {
        return LOsgrondo;
    }

    public void setLOsgrondo(float LOsgrondo) {
        this.LOsgrondo = LOsgrondo;
    }

    public float getTargetEC() {
        return TargetEC;
    }

    public void setTargetEC(float targetEC) {
        TargetEC = targetEC;
    }
}
