package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.ProduktList;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.utils.UserMessages;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Warenkorb {
    public Map<String, Integer> produktList = new HashMap<>();
    public int kundenummerCurrentWarenkorb;

    //  Fügt ein Produkt mit einer bestimmten Menge zum Warenkorb hinzu.
    public void addProdukte(String produkt, int menge){
        //System.out.println(produkt + menge);
        produktList.put(produkt, menge);

    }

    // Löscht alle Produkte aus dem Warenkorb.
    public void clearProdukt(){
        produktList.clear();
        System.out.println(UserMessages.warenkorbEntferntBestaetigungText());

    }

    public void aktualisiereBestandNachBestellung(String nameMedikament, int menge){
        // Überprüft, ob die gewünschte Menge des Medikaments im Bestand ausreicht.
        if (produktList.get(nameMedikament) < menge || produktList.get(nameMedikament) - menge < 0){
            System.out.println(UserMessages.reduzierteBestellmengeText());
        } else {
            // Aktualisiert den Bestand des Medikaments mit der neuen Menge,
            // wenn ausreichend Bestand vorhanden ist.
            produktList.put(nameMedikament, menge);
            System.out.println(UserMessages.statusText());
        }

    }

    public void showWarenkorb(){
        for(Map.Entry<String, Integer> produkt: produktList.entrySet()){
            int bestellungsNummer = ProduktList.getProduktNummerByName(produkt.getKey());
            String bestellung = (produkt.getKey() + "\n").repeat(produkt.getValue());
            System.out.println(bestellung);
        }

    }

    public double getGewichtWarenkorb(Warenbestand warenbestand, Warenkorb warenkorbZumVersenden){
        double gewichtWarenkorb = 0;
        for(Map.Entry<String, Integer> produkt: warenkorbZumVersenden.produktList.entrySet()){
            gewichtWarenkorb += warenbestand.produkte.get(produkt.getKey()).getGewicht() * produkt.getValue();
        }

        return Math.round(gewichtWarenkorb * 100.0) / 100.0 ;
    }
}
