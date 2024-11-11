package info.bestellungsservice.apothekebestellungservice.paket;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class Paket {
    private int paketNummer;
    private double gewicht;
    private String zielAdresse;
    private boolean zugestellt;
    List<HashMap<String, Integer>> paketInhalt;

    public Paket(int paketnummer, double gewicht, String zielAdresse, boolean zugestellt,
                 List<HashMap<String, Integer>> paketInhalt) {
        this.paketNummer = paketnummer;
        this.gewicht = gewicht;
        this.zielAdresse = zielAdresse;
        this.zugestellt = false;
        this.paketInhalt = paketInhalt;
    }

    public void addWaren(String nameMedikament, int menge){
    }

    public void clearWaren(String nameMedikament){
    }
}
