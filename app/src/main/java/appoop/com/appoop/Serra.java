package appoop.com.appoop;

import java.util.Date;

public class Serra {
    public String serra; //nome della serra
    public String m2;
    public String coltura;
    public String varieta;
    public Date trapianto; //data trapianto piante
    public Double LOentrata; //litri/ora entrata
    public Double LOsgrondo; //litri/ora uscita
    public Double TargetEC; //salinità

    public Serra() {
    }

    public Serra(String serra, String m2, String coltura, String varieta, Date trapianto, Double LOentrata, Double LOsgrondo, Double targetEC) {
        this.serra = serra;
        this.m2 = m2;
        this.coltura = coltura;
        this.varieta = varieta;
        this.trapianto = trapianto;
        this.LOentrata = LOentrata;
        this.LOsgrondo = LOsgrondo;
        TargetEC = targetEC;
    }

    public String getSerra() {
        return serra;
    }

    public void setSerra(String serra) {
        this.serra = serra;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
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

    public Double getLOentrata() {
        return LOentrata;
    }

    public void setLOentrata(Double LOentrata) {
        this.LOentrata = LOentrata;
    }

    public Double getLOsgrondo() {
        return LOsgrondo;
    }

    public void setLOsgrondo(Double LOsgrondo) {
        this.LOsgrondo = LOsgrondo;
    }

    public Double getTargetEC() {
        return TargetEC;
    }

    public void setTargetEC(Double targetEC) {
        TargetEC = targetEC;
    }

    @Override
    public String toString() {
        return "Serra{" +
                "serra='" + serra + '\'' +
                ", m2='" + m2 + '\'' +
                ", coltura='" + coltura + '\'' +
                ", varieta='" + varieta + '\'' +
                ", trapianto=" + trapianto +
                ", LOentrata=" + LOentrata +
                ", LOsgrondo=" + LOsgrondo +
                ", TargetEC=" + TargetEC +
                '}';
    }
}
