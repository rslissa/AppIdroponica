package appoop.com.appoop;

import java.util.Date;

public class Rilevamento {

    //questa classe rappresenta un rilevamento, ovvero un'entità della tabella del registro
    public String serra;    //mantiene l'informazione della serra sulla quale è stato fatto il rilevamento
    public Date trapianto;
    public double LOentrata;
    public double LOsgrondo;
    public double TargetEC;

    public Rilevamento(String serra, Date trapianto, double LOentrata, double LOsgrondo, double targetEC) {
        this.serra = serra;
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

    public Date getTrapianto() {
        return trapianto;
    }

    public void setTrapianto(Date trapianto) {
        this.trapianto = trapianto;
    }

    public double getLOentrata() {
        return LOentrata;
    }

    public void setLOentrata(double LOentrata) {
        this.LOentrata = LOentrata;
    }

    public double getLOsgrondo() {
        return LOsgrondo;
    }

    public void setLOsgrondo(double LOsgrondo) {
        this.LOsgrondo = LOsgrondo;
    }

    public double getTargetEC() {
        return TargetEC;
    }

    public void setTargetEC(double targetEC) {
        TargetEC = targetEC;
    }

    @Override
    public String toString() {
        return "Rilevamento{" +
                "serra='" + serra + '\'' +
                ", trapianto=" + trapianto +
                ", LOentrata=" + LOentrata +
                ", LOsgrondo=" + LOsgrondo +
                ", TargetEC=" + TargetEC +
                '}';
    }
}
