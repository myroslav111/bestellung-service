package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;


import java.util.Map;

public interface BestellService {
    public void addWarenkorb(String produkt, int anzahl);
    public void removeWarenkorb(String produkt, int anzahl);
    public void berechneGesamtpreis(Map<String, Integer> produktList, Warenbestand warenbestand);
    public void berechneStrecke();
    public void bestellungAbschicken();

}
