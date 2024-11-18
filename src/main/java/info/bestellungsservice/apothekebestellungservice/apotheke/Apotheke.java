package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;

import java.util.Map;

public class Apotheke implements BestellService {
    public Warenkorb warenkorbZumVersenden = new Warenkorb();
    public BenutzerService benutzerService = new BenutzerService();
    public Bestellverfahren bestellverfahren = new Bestellverfahren();
    public PaketVersandService paketVersandService = new PaketVersandService();
    private static Apotheke instance;

    private Apotheke() {}

    public static Apotheke getInstance() {
        if (Apotheke.instance == null) {
            Apotheke.instance = new Apotheke();
            return Apotheke.instance;
        }
        return Apotheke.instance;
    }

    @Override
    public void addWarenkorb(String produkt, int anzahl) {
    }

    @Override
    public void removeWarenkorb(String produkt, int anzahl) {

    }


    @Override
    public void berechneGesamtpreis(Map<String, Integer> produktList,
                                    Warenbestand warenbestand) {
    }

    @Override
    public void berechneStrecke() {

    }

    @Override
    public void bestellungAbschicken() {

    }

}
