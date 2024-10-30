package info.bestellungsservice.apothekeBestellungService.apotheke;

import info.bestellungsservice.apothekeBestellungService.logistikzentrum.Warenbestand;


import java.util.Map;

public interface BestellService {
    public void addWarenkorb(String produkt, int anzahl);
    public void removeWarenkorb(String produkt, int anzahl);
    public void berechneGesamtpreis(Map<String, Integer> produktList, Warenbestand warenbestand);
    public void berechneStrecke();
    public void bestellungAbschicken();

}
