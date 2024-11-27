package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.enums.Farbcodes;
import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.utils.AnzeigenBeleg;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerFragen;
import info.bestellungsservice.apothekebestellungservice.utils.Suche;


import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Bestellverfahren {
    Scanner scanner = new Scanner(System.in);

    // warenbestand die Informationen über die verfügbaren Produkte enthält.
    // warenkorb die Produkte, die der Benutzer bestellt hat
    public void bestellungAufgeben(Warenbestand warenbestand, Warenkorb warenkorb) {
        //Scanner scanner = new Scanner(System.in);
        // Die Methode fragt den Benutzer, ob er etwas bestellen möchte
        if (BenutzerFragen.frageJaNein(scanner, UserMessagesText.FRAGE_OB_BESTELLT_WERDEN_SOLL.toString())) {
            System.out.println();

            // ermöglicht, mehrere Produkte zu bestellen.
            do{
                // Jedes Produkt wird durch einen Aufruf angezeigt
                warenbestand.showWarenBestand();
                System.out.println(UserMessagesText.FRAGE_NACH_GEWUENSCHTEM_PRODUKT);
                String nameMedikament = Suche.sucheMedikamentNachEingabe(scanner);
                System.out.println(UserMessagesText.FRAGE_NACH_GEWUENSCHTER_ANZAHL);
                int menge = scanner.nextInt();

                pruefeUndAktualisiereMedikamentImWarenkorb(nameMedikament, menge, warenbestand, warenkorb);
                // Aktualisierung des Warenbestands

                if (warenkorb.produktList.isEmpty()) {
                    System.out.println(UserMessagesText.LEER_WARENKORB_NACHRICHT);
                }else{
                    System.out.println(UserMessagesText.WARENKORB_STATUS);
                    System.out.println();
                    warenkorb.showWarenkorb();
                }

            }
            while (BenutzerFragen.frageJaNein(scanner, UserMessagesText.FORTFAHREN_MIT_BESTELLUNG.toString()));

            System.out.println(UserMessagesText.BESTELLUNG_ANZEIGE);
            warenkorb.showWarenkorb();
            bestellvorgangAbschliessen(warenbestand, warenkorb);

        }

    }

    public void pruefeUndAktualisiereMedikamentImWarenkorb(String nameMedikament, int gewuenschteMenge, Warenbestand warenbestand, Warenkorb warenkorb){

        // Wenn der Benutzer bereits ein Medikament hinzugefügt hat,
        // ermöglicht dies eine weitere Bearbeitung des vorhandenen Medikaments
        int currentProduktMenge = warenbestand.produkte.get(nameMedikament).getMenge();
        if (warenkorb.produktList.containsKey(nameMedikament)) {

            if (currentProduktMenge - gewuenschteMenge >= 0) {
                int aktualisierteMengeProdukt = warenkorb.produktList.get(nameMedikament) + gewuenschteMenge;
                warenkorb.addProdukte(nameMedikament, aktualisierteMengeProdukt );
                warenbestand.deleteProdukte(nameMedikament, gewuenschteMenge, warenbestand.produkte);

            }else {
                String lagerbestandWarnungText = UserMessagesText.LAGERBESTAND_WARNUNG.toString();
                System.out.println(Farbcodes.ROT.formatText(lagerbestandWarnungText));
            }
        }else {
            if (currentProduktMenge - gewuenschteMenge >= 0) {
                warenkorb.addProdukte(nameMedikament, gewuenschteMenge);
                warenbestand.deleteProdukte(nameMedikament, gewuenschteMenge, warenbestand.produkte);
            }else {
                String lagerbestandWarnungMitVerfuegbarkeitText = UserMessagesText.LAGERBESTAND_WARNUNG_MIT_VERFUEGBARKEIT
                        .format(currentProduktMenge);
                System.out.println(Farbcodes.ROT.formatText(lagerbestandWarnungMitVerfuegbarkeitText));
            }

        }
    }

    public void bestellvorgangAbschliessen(Warenbestand warenbestand, Warenkorb warenkorb){
        // Bestellbestätigung
        // Der Benutzer wird gefragt, ob er die Bestellung bestätigen möchte.
        // Wenn der Benutzer zustimmt, wird die Methode berechneGesamtpreis aufgerufen,
        // um den Gesamtpreis der Bestellung zu berechnen und den Beleg anzuzeigen.

        if (BenutzerFragen.frageJaNein(scanner, UserMessagesText.BESTELL_BESTAETIGUNGS_FRAGE.toString())) {
            berechneGesamtpreis(warenkorb.produktList, warenbestand);
        }else if(BenutzerFragen.frageJaNein(scanner, UserMessagesText.BESTELL_AENDERUNGS_OPTION.toString())) {
            // Wenn der Benutzer die gesamte Bestellung abbrechen möchte,
            // wird der Warenkorb geleert und der aktuelle (leere) Warenkorb angezeigt.
            Set<Map.Entry<String, Integer>> produktList = warenkorb.produktList.entrySet();
            for(Map.Entry<String, Integer> entry: produktList){
                // Aktualisierung des Warenbestands
                String name = entry.getKey();
                Integer menge = entry.getValue();
                warenbestand.addProdukte(name, menge, warenbestand.produkte);
            }
            warenkorb.clearProdukt();
            warenkorb.showWarenkorb();
            warenbestand.showWarenBestand();
        }else{
            if (BenutzerFragen.frageJaNein(scanner, UserMessagesText.FRAGE_HINZUFUEGEN_ODER_REDUZIEREN.toString())) {
                bestellungAufgeben(warenbestand, warenkorb);
            }
            do {
                // Zeigt den aktuellen Inhalt des Warenkorbs an.
                warenkorb.showWarenkorb();
                System.out.println(UserMessagesText.REDUKTIONS_ANFRAGE_PRODUKT);

                String medikamentName = Suche.sucheMedikamentNachEingabe(scanner);

                System.out.println(UserMessagesText.ANFRAGE_REDUZIEREN_MENGE.format(medikamentName));
                int menge = scanner.nextInt();

                // Aktualisiert die Menge des ausgewählten Produkts im Warenkorb.
                warenkorb.aktualisiereBestandNachBestellung(medikamentName, menge);
                warenbestand.addProdukte(medikamentName, menge, warenbestand.produkte);


                // Fragt den Benutzer, ob er weitere Produkte reduzieren möchte.
            }while (BenutzerFragen.frageJaNein(scanner, UserMessagesText.FRAGE_OB_WEITER_REDUZIERT_WERDEN_SOL.toString()));

            berechneGesamtpreis(warenkorb.produktList, warenbestand);

        }
    }

    // produktList, die die Produkte als Schlüssel
    // (Strings) und die zu bestellenden Mengen als Werte (Integers) enthält.
    // warenbestand die Informationen über die verfügbaren Produkte und deren Preise enthält
    // der Klasse AnzeigenBeleg, die für die Anzeige des Bestellbelegs zuständig ist.
    public void berechneGesamtpreis(Map<String, Integer> produktList,
                                    Warenbestand warenbestand) {
        Apotheke apotheke = Apotheke.getInstance();
        //Iterieren über die Produktliste, um den Gesamtpreis zu berechnen.
        for(Map.Entry<String, Integer> entry: produktList.entrySet()){
            // Aktualisierung des Warenbestands
            String produkt = entry.getKey();
            Integer menge = entry.getValue();
            apotheke.warenkorbZumVersenden.addProdukte(produkt, menge);
        }
        //Anzeige des Belegs
        AnzeigenBeleg.anzeigenBeleg(produktList, warenbestand);

    }

}
