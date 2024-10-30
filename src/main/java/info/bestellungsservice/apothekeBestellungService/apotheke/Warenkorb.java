package info.bestellungsservice.apothekeBestellungService.apotheke;

import java.util.HashMap;
import java.util.Map;


public class Warenkorb {
    //public List<String> produktList;
    public Map<String, Integer> produktList = new HashMap<>();

    //  Fügt ein Produkt mit einer bestimmten Menge zum Warenkorb hinzu.
    public void addProdukte(String produkt, int menge){
        System.out.println(produkt + menge);
        produktList.put(produkt, menge);

    }

    // Löscht alle Produkte aus dem Warenkorb.
    public void clearProdukt(){
        produktList.clear();

    }

    // Gibt eine Übersicht über die Produkte im Warenkorb aus.
    public void showWarenkorb(){
        for(Map.Entry<String, Integer> produkt: produktList.entrySet()){
            System.out.println(produkt.getKey() + ": " + produkt.getValue() + " Stück");
        }

    }
}
