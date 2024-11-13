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

    @Override
    public PaketBuilderInterface paketNummer(int paketNummer) {
        this.paketNummer = paketNummer;
        return this;
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
        produkt.put(waren, menge);
        this.paketInhalt.add(produkt);
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
        return paket;
    }
}
