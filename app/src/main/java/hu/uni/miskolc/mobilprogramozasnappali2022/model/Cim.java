package hu.uni.miskolc.mobilprogramozasnappali2022.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Cim implements Serializable {
    @PrimaryKey
    @NonNull
    private UUID id;
    private String iranyitoszam;
    private String varos;
    private String utca;
    private String hazszam;

    public Cim() {
        this.id= UUID.randomUUID();
    }

    public Cim(String iranyitoszam, String varos, String utca, String hazszam) {
        this.iranyitoszam = iranyitoszam;
        this.varos = varos;
        this.utca = utca;
        this.hazszam = hazszam;
        this.id= UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cim{" +
                "id=" + id +
                ", iranyitoszam='" + iranyitoszam + '\'' +
                ", varos='" + varos + '\'' +
                ", utca='" + utca + '\'' +
                ", hazszam='" + hazszam + '\'' +
                '}';
    }
}
