package info.bestellungsservice.apothekebestellungservice.paket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaketBuilder implements PaketBuilderInterface{
    private int paketNummer;
    private double paketGewicht;
    private String zielAdresse;
    private boolean zugestellt;
    private List<HashMap<String, Integer>> paketInhalt = new ArrayList<>();

    // Setzt die Paketnummer und gibt den Builder selbst zurück (für das fluide Design)
    @Override
    public PaketBuilderInterface paketNummer(int paketNummer) {
        this.paketNummer = paketNummer;
        return this; // Rückgabe des Builders ermöglicht das Verketten von Methoden
    }

    @Override
    public PaketBuilderInterface gewicht(double gewicht) {
        this.paketGewicht = gewicht;
        return this;
    }

    @Override
    public PaketBuilderInterface zielAdresse(String zielAdresse) {
        this.zielAdresse = zielAdresse;
        return this;
    }

    @Override
    public void addWare(String waren, int menge) {
        HashMap<String, Integer> produkt = new HashMap<>();
        produkt.put(waren, menge);// Das Produkt und die Menge werden im Map gespeichert
        this.paketInhalt.add(produkt);// Das Produkt wird zur Liste des Paketinhalt hinzugefügt
    }

    @Override
    public PaketBuilderInterface zugestellt(boolean zugestellt) {
        this.zugestellt = zugestellt;
        return this;
    }

    @Override
    public Paket build() {
        Paket paket = new Paket();
        paket.setPaketNummer(paketNummer);
        paket.setGewicht(paketGewicht);
        paket.setZielAdresse(zielAdresse);
        paket.setPaketInhalt(paketInhalt);
        paket.setZugestellt(zugestellt);
        return paket;
    }
}
