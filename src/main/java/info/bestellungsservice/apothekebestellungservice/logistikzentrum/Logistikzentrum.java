package info.bestellungsservice.apothekebestellungservice.logistikzentrum;

import info.bestellungsservice.apothekebestellungservice.paket.Paket;


import java.util.ArrayList;
import java.util.List;

public class Logistikzentrum {
    public List<Paket> paketeZumVersenden = new ArrayList<Paket>();
    public Warenbestand warenbestand = new Warenbestand();
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
