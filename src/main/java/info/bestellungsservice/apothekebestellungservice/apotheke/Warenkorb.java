package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.enums.Farbcodes;
import info.bestellungsservice.apothekebestellungservice.enums.ProduktList;
import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        System.out.println(UserMessagesText.WARENKORB_ENTFERNT_BESTAETIGUNG);

    }

    public void aktualisiereBestandNachBestellung(String produktName, int gewuenschteProduktMenge){
        // Überprüft, ob die gewünschte Menge des Medikaments im Bestand ausreicht.
        int aktuelleProduktMengeImWarenkorb = produktList.get(produktName);
        if (aktuelleProduktMengeImWarenkorb < gewuenschteProduktMenge || aktuelleProduktMengeImWarenkorb - gewuenschteProduktMenge < 0){
            System.out.println(UserMessagesText.REDUZIERTE_BESTELLMENGE);
        } else {
            // Aktualisiert den Bestand des Medikaments mit der neuen Menge,
            // wenn ausreichend Bestand vorhanden ist.
            int resultDivedieren = produktList.get(produktName) - gewuenschteProduktMenge;
            produktList.put(produktName, resultDivedieren);
            String statusBestellung = UserMessagesText.STATUS.toString();
            System.out.println(Farbcodes.GRUEN.formatText(statusBestellung));
        }

    }

    public void showWarenkorb(){
        if (produktList.isEmpty()) System.out.println(UserMessagesText.WARENKORB_STATUS_IST_LEER);

        Set<Map.Entry<String, Integer>> warenkorbInhaht = produktList.entrySet();
        for(Map.Entry<String, Integer> produkt: warenkorbInhaht){
            String produktName = produkt.getKey();
            int bestellungsNummer = ProduktList.getProduktNummerByName(produktName);
            int produktMenge = produkt.getValue();
            String bestellung = (produktName + "\n").repeat(produktMenge);
            System.out.println(bestellung);
        }

    }

    public double getGewichtWarenkorb(Warenbestand warenbestand, Warenkorb warenkorbZumVersenden){
        double gewichtWarenkorb = 0;
        Set<Map.Entry<String, Integer>> warenkorbInhalt = warenkorbZumVersenden.produktList.entrySet();
        for(Map.Entry<String, Integer> produkt: warenkorbInhalt){
            double produktGewicht = warenbestand.produkte.get(produkt.getKey()).getGewicht();
            int produktMenge = produkt.getValue();
            gewichtWarenkorb += produktGewicht * produktMenge;
        }

        return Math.round(gewichtWarenkorb * 100.0) / 100.0 ;
    }
}
