package Application.Model;

import java.util.ArrayList;

public class Fad {
    private int id;
    private String fadType;
    private double volumen;
    private double nuværendeVolumen;
    private int antalGangeBrugt;
    private Lager lager;


    public Fad(int id, String fadType, double volumen, double nuværendeVolumen, int antalGangeBrugt, Lager lager) {
        this.id = id;
        this.fadType = fadType;
        this.volumen = volumen;
        this.nuværendeVolumen = nuværendeVolumen;
        this.antalGangeBrugt = antalGangeBrugt;
        this.lager = lager;
    }

    public void setLager(Lager lager) {
        if(this.lager != lager) {
            Lager gammeltLager = this.lager;
            if(gammeltLager != null) {
                gammeltLager.sletFad(this);
            }
            this.lager = lager;
            if(lager != null){
                lager.tilføjFad(this);
            }
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFadType() {
        return fadType;
    }

    public void setFadType(String fadType) {
        this.fadType = fadType;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getNuværendeVolumen() {
        return nuværendeVolumen;
    }

    public void setNuværendeVolumen(double nuværendeVolumen) {
        this.nuværendeVolumen = nuværendeVolumen;
    }

    public int getAntalGangeBrugt() {
        return antalGangeBrugt;
    }

    public void setAntalGangeBrugt(int antalGangeBrugt) {
        this.antalGangeBrugt = antalGangeBrugt;
    }

    public Lager getLager() {
        return lager;
    }



    @Override
    public String toString() {
        return "Fad{" +
                "id=" + id +
                ", fadType='" + fadType + '\'' +
                ", volumen=" + volumen +
                ", nuværendeVolumen='" + nuværendeVolumen + '\'' +
                ", antalGangeBrugt=" + antalGangeBrugt +
                ", lager=" + (lager != null ? lager.getNavn() : "Intet Lager") +
                '}';
    }



}
