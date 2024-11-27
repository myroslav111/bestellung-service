package info.bestellungsservice.apothekebestellungservice.logistikzentrum;

import info.bestellungsservice.apothekebestellungservice.paket.Paket;
import info.bestellungsservice.apothekebestellungservice.produkt.ProduktFactory;
import info.bestellungsservice.apothekebestellungservice.produkt.StandardProduktFactory;


import java.util.ArrayList;
import java.util.List;

public class Logistikzentrum {
    public List<Paket> paketeZumVersenden = new ArrayList<Paket>();
    ProduktFactory factory = new StandardProduktFactory();
    Warenbestand warenbestand = new Warenbestand(factory);
    private static Logistikzentrum instance;

    private Logistikzentrum() {}

    public static Logistikzentrum getInstance() {
        if (Logistikzentrum.instance == null) {
            Logistikzentrum.instance = new Logistikzentrum();
            return Logistikzentrum.instance;
        }
        return Logistikzentrum.instance;
    }
}
