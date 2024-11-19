package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.enums.Farbcodes;
import info.bestellungsservice.apothekebestellungservice.enums.ProduktList;
import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.utils.AnzeigenBeleg;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerFragen;


import java.util.Map;
import java.util.Scanner;

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
                String nameMedikament = sucheMedikamentNachEingabe();
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

    public String sucheMedikamentNachEingabe(){
        String nameMedikament = "";
        // Konsumiere den Zeilenumbruch
        scanner.nextLine();
        //entscheidet auf Grundlage von scanner.hasNextInt(), welche Methode aufgerufen wird:
        Object inputUser = scanner.hasNextInt() ? scanner.nextInt() : scanner.nextLine();

        for(ProduktList produkt: ProduktList.values()){
            // Diese Bedingung überprüft, ob inputUser eine Instanz der Klasse Number ist.
            // Da inputUser ein Object sein kann (entweder ein Integer oder String),
            // stellt dies sicher, dass die Eingabe tatsächlich eine Zahl ist und nicht z.B. ein String.
            // Falls inputUser eine Zahl ist, wird es in Number umgewandelt
            //intValue() ist eine Methode der Klasse Number, die den Number-Wert in einen int konvertiert.
            //Prüft, ob inputUser eine Zahl ist.
            //Konvertiert inputUser (falls es eine Zahl ist) in einen int.
            //Vergleicht den Wert dieser Zahl mit der Medikamentennummer des Produkts.

            // Überprüfe, ob inputUser eine Zahl ist
            if(inputUser instanceof Number && ((Number)inputUser).intValue() == produkt.getProduktNummer()){
                // Setze den Medikamentennamen, wenn die Nummer übereinstimmt
                nameMedikament = produkt.getProduktName();
            }else if (produkt.getProduktName().trim().equalsIgnoreCase(inputUser.toString().trim())){

                // Setze den Medikamentennamen, wenn der Name übereinstimmt
                nameMedikament = produkt.getProduktName();
            }else {
                System.out.println();
            }
        }
        return nameMedikament;
    }

    public void pruefeUndAktualisiereMedikamentImWarenkorb(String nameMedikament, int menge, Warenbestand warenbestand, Warenkorb warenkorb){

        // Wenn der Benutzer bereits ein Medikament hinzugefügt hat,
        // ermöglicht dies eine weitere Bearbeitung des vorhandenen Medikaments
        if (warenkorb.produktList.containsKey(nameMedikament)) {

            if (warenbestand.produkte.get(nameMedikament).getMenge() - menge >= 0) {
                warenkorb.addProdukte(nameMedikament, warenkorb.produktList.get(nameMedikament) + menge);
                warenbestand.deleteProdukte(nameMedikament, menge, warenbestand.produkte);

            }else {
                String lagerbestandWarnungText = UserMessagesText.LAGERBESTAND_WARNUNG.toString();
                System.out.println(Farbcodes.ROT.formatText(lagerbestandWarnungText));
            }
        }else {
            if (warenbestand.produkte.get(nameMedikament).getMenge() - menge >= 0) {
                warenkorb.addProdukte(nameMedikament, menge);
                warenbestand.deleteProdukte(nameMedikament, menge, warenbestand.produkte);
            }else {
                String lagerbestandWarnungMitVerfuegbarkeitText = UserMessagesText.LAGERBESTAND_WARNUNG_MIT_VERFUEGBARKEIT
                        .format(warenbestand.produkte.get(nameMedikament).getMenge());
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
            for(Map.Entry<String, Integer> entry: warenkorb.produktList.entrySet()){
                // Aktualisierung des Warenbestands
                warenbestand.addProdukte(entry.getKey(), entry.getValue(), warenbestand.produkte);
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

                String medikamentName = sucheMedikamentNachEingabe();

                System.out.println(UserMessagesText.ANFRAGE_REDUZIEREN_MENGE.format(medikamentName));
                int menge = scanner.nextInt();

                // Aktualisiert die Menge des ausgewählten Produkts im Warenkorb.
                warenkorb.aktualisiereBestandNachBestellung(medikamentName, menge);


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
            apotheke.warenkorbZumVersenden.addProdukte(entry.getKey(), entry.getValue());
        }

        //Anzeige des Belegs
        AnzeigenBeleg.anzeigenBeleg(produktList, warenbestand);

    }

}
