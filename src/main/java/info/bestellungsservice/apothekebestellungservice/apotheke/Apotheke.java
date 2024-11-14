package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.ProduktList;
import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Logistikzentrum;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.paket.Paket;
import info.bestellungsservice.apothekebestellungservice.paket.PaketBuilder;
import info.bestellungsservice.apothekebestellungservice.paket.PaketBuilderInterface;
import info.bestellungsservice.apothekebestellungservice.utils.*;

import java.util.Map;
import java.util.Scanner;

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
