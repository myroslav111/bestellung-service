package info.bestellungsservice.apothekeBestellungService.logistikzentrum;

import info.bestellungsservice.apothekeBestellungService.paket.Paket;


import java.util.List;

public class Logistikzentrum {
    public List<Paket> paketeZumVersenden;
    public Warenbestand warenbestand = new Warenbestand();

    public Logistikzentrum() {}
}
