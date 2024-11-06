package info.bestellungsservice.apothekeBestellungService.apotheke;

import info.bestellungsservice.apothekeBestellungService.ProduktList;

import java.util.HashMap;
import java.util.Map;


public class Warenkorb {
    //public List<String> produktList;
    public Map<String, Integer> produktList = new HashMap<>();


    //  Fügt ein Produkt mit einer bestimmten Menge zum Warenkorb hinzu.
    public void addProdukte(String produkt, int menge){
        //System.out.println(produkt + menge);
        produktList.put(produkt, menge);

    }

    // Löscht alle Produkte aus dem Warenkorb.
    public void clearProdukt(){
        produktList.clear();
        System.out.println("Wir haben alle aus Ihrem Warenkorb entfernt");

    }

    public void aktualisiereBestandNachBestellung(String nameMedikament, int menge){
        // Überprüft, ob die gewünschte Menge des Medikaments im Bestand ausreicht.
        if (produktList.get(nameMedikament) < menge || produktList.get(nameMedikament) - menge < 0){
            System.out.println("Sie haben weniger in der Bestellung");
        } else {
            // Aktualisiert den Bestand des Medikaments mit der neuen Menge,
            // wenn ausreichend Bestand vorhanden ist.
            produktList.put(nameMedikament, menge);
            System.out.println("Hat gearbeitet");
        }

    }

    // Gibt eine Übersicht über die Produkte im Warenkorb aus.
    public void showWarenkorb(){
        for(Map.Entry<String, Integer> produkt: produktList.entrySet()){
            int bestellungsNummer = ProduktList.showProduktnummerByBedinung(produkt.getKey());
            System.out.println("|-----" + produkt.getKey() + ": " + produkt.getValue() + " Stück" + " --> Bestellnummer: " + bestellungsNummer);
        }

    }
}
