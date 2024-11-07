package info.bestellungsservice.apothekebestellungservice.logistikzentrum;

import info.bestellungsservice.apothekebestellungservice.ProduktList;
import info.bestellungsservice.apothekebestellungservice.produkt.Produkt;


import java.util.HashMap;
import java.util.Map;


public class Warenbestand {
    public Map<String, Produkt> produkte;

    //konstruktor mithilfe mapInterface
    public Warenbestand() {
        produkte = new HashMap<>();
        produkte.put(ProduktList.IBU.getMedikamenteName(), new Produkt(3, 5.99, 0.2));
        produkte.put(ProduktList.ASPIRIN.getMedikamenteName(), new Produkt(5, 4.99, 0.1));
        produkte.put(ProduktList.PARACETAMOL.getMedikamenteName(), new Produkt(2, 3.99, 0.15));
        produkte.put(ProduktList.VITAMIN_C.getMedikamenteName(), new Produkt(8, 7.99, 0.05));
        produkte.put(ProduktList.VITAMIN_D.getMedikamenteName(), new Produkt(7, 6.99, 0.07));
    }

    // Ausgabe des gesamten Warenbestands
    public  void showWarenBestand(){
        System.out.println("Aktueller Warenbestand:");
        for(Map.Entry<String, Produkt> produkt: produkte.entrySet()){
            //System.out.println(produkt.getKey() + ": " + produkt.getValue().getMenge() + " Stück");

            System.out.println(produkt.getKey() + " -> " + "Preis: " + produkt.getValue().getPreis() + ", "
                    + "Gewicht: " + produkt.getValue().getGewicht() + ", " + "Verfügbar: "
                    + produkt.getValue().getMenge() + "   Produktnummer-<" + ProduktList.valueOf(produkt.getKey().toUpperCase()).getMedikamenteNummer() + ">-");
        }
    }
    // Ausgabe konkreten Produkt
    public void getProdukte(String productName) {
        if (produkte.containsKey(productName)){
            System.out.println("Momentan im Lager sind " + productName + ": " + produkte.get(productName) + " Stück");
        }
    }

    // entfernen Werte des Produkts
    public void entfernenProdukte(String name, int menge, Map<String, Produkt> produkte) {
        produkte.get(name).setMenge((produkte.get(name).getMenge() - menge));
    }

    // add Werte des Produkts
    public void addProdukte(String name, int menge, Map<String, Produkt> produkte) {
        produkte.get(name).setMenge((produkte.get(name).getMenge() + menge));
    }
}
