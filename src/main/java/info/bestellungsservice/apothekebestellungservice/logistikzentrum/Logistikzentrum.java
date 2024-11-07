package info.bestellungsservice.apothekebestellungservice.logistikzentrum;

import info.bestellungsservice.apothekebestellungservice.paket.Paket;


import java.util.List;

public class Logistikzentrum {
    public List<Paket> paketeZumVersenden;
    public Warenbestand warenbestand = new Warenbestand();

    public Logistikzentrum() {}
}
