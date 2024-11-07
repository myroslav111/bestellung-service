package info.bestellungsservice.apothekebestellungservice.paket;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class Paket {
    private int paketnummere;
    private double gewicht;
    private String zielAdresse;
    private boolean zugestellt;
    List<HashMap<String, Integer>> paketinhalt;

    public Paket(int paketnummere, double gewicht, String zielAdresse, boolean zugestellt,
                 List<HashMap<String, Integer>> paketinhalt) {
        this.paketnummere = paketnummere;
        this.gewicht = gewicht;
        this.zielAdresse = zielAdresse;
        this.zugestellt = false;
        this.paketinhalt = paketinhalt;
    }

    public void addWaren(String nameMedikament, int menge){
    }

    public void clearWaren(String nameMedikament){
    }
}
