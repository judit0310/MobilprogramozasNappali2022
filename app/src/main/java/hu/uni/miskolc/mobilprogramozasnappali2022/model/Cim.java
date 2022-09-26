package hu.uni.miskolc.mobilprogramozasnappali2022.model;

import java.io.Serializable;

public class Cim implements Serializable {

    private String iranyitoszam;
    private String varos;
    private String utca;
    private String hazszam;

    public Cim() {
    }

    public Cim(String iranyitoszam, String varos, String utca, String hazszam) {
        this.iranyitoszam = iranyitoszam;
        this.varos = varos;
        this.utca = utca;
        this.hazszam = hazszam;
    }

    public String getIranyitoszam() {
        return iranyitoszam;
    }

    public void setIranyitoszam(String iranyitoszam) {
        this.iranyitoszam = iranyitoszam;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public String getHazszam() {
        return hazszam;
    }

    public void setHazszam(String hazszam) {
        this.hazszam = hazszam;
    }

    @Override
    public String toString() {
        return "Cim{" +
                "iranyitoszam='" + iranyitoszam + '\'' +
                ", varos='" + varos + '\'' +
                ", utca='" + utca + '\'' +
                ", hazszam='" + hazszam + '\'' +
                '}';
    }
}
