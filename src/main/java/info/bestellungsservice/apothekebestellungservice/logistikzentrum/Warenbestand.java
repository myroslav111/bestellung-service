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
        Produkt ibu = produktFactory.createMedikament("Ibu", 10, 5.99, 0.2);
        Produkt aspirin = produktFactory.createMedikament("Aspirin", 13, 5.99, 0.2);
        Produkt paracetamol = produktFactory.createMedikament("Paracetamol", 3, 5.99, 0.2);
        Produkt vitamin_c = produktFactory.createMedikament("Vitamin_C", 15, 5.99, 0.2);
        Produkt vitamin_d = produktFactory.createMedikament("Vitamin_D", 6, 5.99, 0.2);
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

            String produktName = produkt.getKey();
            double produktPreis = produkt.getValue().getPreis();
            double produktGewicht = produkt.getValue().getGewicht();
            int produktNummer = ProduktList.valueOf(produktName.toUpperCase()).getProduktNummer();

            System.out.println(produktName + " -> " + "Preis: " + produktPreis + ", "
                    + "Gewicht: " + produktGewicht + ", " + "Verfügbar: "
                    + menge + ", Produktnummer: " + produktNummer);
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
