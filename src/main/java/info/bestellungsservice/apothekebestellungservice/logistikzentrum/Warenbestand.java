package info.bestellungsservice.apothekebestellungservice.logistikzentrum;

import info.bestellungsservice.apothekebestellungservice.enums.Farbcodes;
import info.bestellungsservice.apothekebestellungservice.enums.ProduktList;
import info.bestellungsservice.apothekebestellungservice.produkt.Produkt;
import info.bestellungsservice.apothekebestellungservice.produkt.ProduktFactory;


import java.util.HashMap;
import java.util.Map;


public class Warenbestand {
    public ProduktFactory produktFactory;
    public Map<String, Produkt> produkte;

    //konstruktor mithilfe mapInterface
    public Warenbestand(ProduktFactory produktFactory) {
        this.produktFactory = produktFactory;
        produkte = new HashMap<>();
        Produkt ibu = produktFactory.createProdukt("Ibu", 10, 5.99, 0.2);
        Produkt aspirin = produktFactory.createProdukt("Aspirin", 13, 5.99, 0.2);
        Produkt paracetamol = produktFactory.createProdukt("Paracetamol", 3, 5.99, 0.2);
        Produkt vitamin_c = produktFactory.createProdukt("Vitamin_C", 15, 5.99, 0.2);
        Produkt vitamin_d = produktFactory.createProdukt("Vitamin_D", 6, 5.99, 0.2);
        produkte.put(ProduktList.IBU.getProduktName(), ibu);
        produkte.put(ProduktList.ASPIRIN.getProduktName(), aspirin);
        produkte.put(ProduktList.PARACETAMOL.getProduktName(), paracetamol);
        produkte.put(ProduktList.VITAMIN_C.getProduktName(), vitamin_c);
        produkte.put(ProduktList.VITAMIN_D.getProduktName(), vitamin_d);
    }

    // Ausgabe des gesamten Warenbestands
    public  void showWarenBestand(){
        System.out.println("Aktueller Warenbestand:");
        for(Map.Entry<String, Produkt> produkt: produkte.entrySet()){
            //System.out.println(produkt.getKey() + ": " + produkt.getValue().getMenge() + " Stück");
            String menge = produkt.getValue().getMenge() > 0 ? Farbcodes.GRUEN.formatText(produkt.getValue().getMenge()) : Farbcodes.ROT.formatText(produkt.getValue().getMenge());

            System.out.println(produkt.getKey() + " -> " + "Preis: " + produkt.getValue().getPreis() + ", "
                    + "Gewicht: " + produkt.getValue().getGewicht() + ", " + "Verfügbar: "
                    + menge + ", Produktnummer: " + ProduktList.valueOf(produkt.getKey().toUpperCase()).getProduktNummer());
        }
    }

    // entfernen Werte des Produkts
    public void deleteProdukte(String produktName, int abzuschreibendeMenge, Map<String, Produkt> produkte) {
        int neueProduktMenge = produkte.get(produktName).getMenge() - abzuschreibendeMenge;
        produkte.get(produktName)
                .setMenge(neueProduktMenge);
    }

    // add Werte des Produkts
    public void addProdukte(String produktName, int abzuschreibendeMenge, Map<String, Produkt> produkte) {
        int neueProduktMenge = produkte.get(produktName).getMenge() + abzuschreibendeMenge;
        produkte.get(produktName)
                .setMenge(neueProduktMenge);
    }
}
