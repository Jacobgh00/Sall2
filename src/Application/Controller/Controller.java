package Application.Controller;

import Application.Model.Fad;
import Application.Model.Lager;

import java.util.ArrayList;
import java.util.Collection;

public class Controller {

    private static ArrayList<Lager> lagerliste = new ArrayList<>();

    public static void addLager(Lager nytLager) {
        lagerliste.add(nytLager);
    }

    public static ArrayList<Lager> getLagerliste(){
        return lagerliste;
    }

    public static void updateLager(Lager lager){
        //find det eksisterende lager i listen og overskriv det med det redigerede
        for (int i = 0; i < lagerliste.size(); i++) {
            if(lagerliste.get(i).getId() == lager.getId()){
                lagerliste.set(i, lager);
                break;
            }
        }
    }

    public static void deleteLager(Lager lager){
        lagerliste.remove(lager);
    }

    public static void clearLagerListe() {
        lagerliste.clear();
    }

    public static void addFad(Lager lager, Fad nytFad){
        lager.tilføjFad(nytFad);
    }

    public static ArrayList<Fad> getFadListe(Lager lager){
        return lager.getFade();
    }

    public static void updateFad(Fad fad) {
        // Find det relevante lager for fadet
        Lager lager = fad.getLager();
        // Find det eksisterende fad i lageret og overskriv det med det redigerede fad
        for (Fad eksisterendeFad : lager.getFade()) {
            if (eksisterendeFad.getId() == fad.getId()) {
                eksisterendeFad.setFadType(fad.getFadType());
                eksisterendeFad.setVolumen(fad.getVolumen());
                eksisterendeFad.setNuværendeVolumen(fad.getNuværendeVolumen());
                eksisterendeFad.setAntalGangeBrugt(fad.getAntalGangeBrugt());
                break;
            }
        }
    }

    public static void deleteFad(Fad fad) {
        // Fjern fadet fra det relevante lager
        Lager lager = fad.getLager();
        lager.sletFad(fad);
    }

    public static Lager findLagerById(int id) {
        // Find et lager i listen ud fra dets ID
        for (Lager lager : lagerliste) {
            if (lager.getId() == id) {
                return lager;
            }
        }
        return null;
    }


}
