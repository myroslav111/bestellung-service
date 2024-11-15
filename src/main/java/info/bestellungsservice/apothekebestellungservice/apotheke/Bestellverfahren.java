package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.ProduktList;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.utils.AnzeigenBeleg;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerFragen;
import info.bestellungsservice.apothekebestellungservice.utils.UserMessages;

import java.util.Map;
import java.util.Scanner;

public class Bestellverfahren {
    Scanner scanner = new Scanner(System.in);

    // warenbestand die Informationen über die verfügbaren Produkte enthält.
    // warenkorb die Produkte, die der Benutzer bestellt hat
    public void bestellungAufgeben(Warenbestand warenbestand, Warenkorb warenkorb) {
        //Scanner scanner = new Scanner(System.in);
        // Die Methode fragt den Benutzer, ob er etwas bestellen möchte
        if (BenutzerFragen.frageJaNein(scanner, UserMessages.frageObBestelltWerdenSollText())) {
            System.out.println();

            // ermöglicht, mehrere Produkte zu bestellen.
            do{
                // Jedes Produkt wird durch einen Aufruf angezeigt
                warenbestand.showWarenBestand();
                System.out.println(UserMessages.frageNachGewuenschtenProduktText());
                String nameMedikament = sucheMedikamentNachEingabe();
                System.out.println(UserMessages.frageNachGewuenschterAnzahlText());
                int menge = scanner.nextInt();

                pruefeUndAktualisiereMedikamentImWarenkorb(nameMedikament, menge, warenbestand, warenkorb);

                if (warenkorb.produktList.isEmpty()) {
                    System.out.println(UserMessages.leerWarenkorbNachrichtText());
                }else{
                    System.out.println(UserMessages.warenkorbStatusText());
                    System.out.println();
                    warenkorb.showWarenkorb();
                }

            }
            while (BenutzerFragen.frageJaNein(scanner, UserMessages.fortfahrenMitBestellungText()));

            System.out.println(UserMessages.bestellungAnzeigeText());
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
                System.out.println("\n \n");
            }
        }
        return nameMedikament;
    }

    public void pruefeUndAktualisiereMedikamentImWarenkorb(String nameMedikament, int menge, Warenbestand warenbestand, Warenkorb warenkorb){

        // Wenn der Benutzer bereits ein Medikament hinzugefügt hat,
        // ermöglicht dies eine weitere Bearbeitung des vorhandenen Medikaments
        if (warenkorb.produktList.containsKey(nameMedikament)) {

            if (warenbestand.produkte.get(nameMedikament).getMenge() - menge - warenkorb.produktList.get(nameMedikament) >= 0) {
                warenkorb.addProdukte(nameMedikament, warenkorb.produktList.get(nameMedikament) + menge);
            }else {
                System.out.println(UserMessages.lagerbestandWarnungText());
            }
        }else {
            if (!(warenbestand.produkte.get(nameMedikament).getMenge() - menge < 0)) {
                warenkorb.addProdukte(nameMedikament, menge);
            }else {
                System.out.println(UserMessages.lagerbestandWarnungMitVerfuegbarkeitText(warenbestand.produkte.get(nameMedikament).getMenge()) );
            }

        }
    }

    public void bestellvorgangAbschliessen(Warenbestand warenbestand, Warenkorb warenkorb){
        // Bestellbestätigung
        // Der Benutzer wird gefragt, ob er die Bestellung bestätigen möchte.
        // Wenn der Benutzer zustimmt, wird die Methode berechneGesamtpreis aufgerufen,
        // um den Gesamtpreis der Bestellung zu berechnen und den Beleg anzuzeigen.

        if (BenutzerFragen.frageJaNein(scanner, UserMessages.bestellBestaetigungsFrageText())) {
            berechneGesamtpreis(warenkorb.produktList, warenbestand);
        }else if(BenutzerFragen.frageJaNein(scanner, UserMessages.bestellAenderungsOptionText())) {
            // Wenn der Benutzer die gesamte Bestellung abbrechen möchte,
            // wird der Warenkorb geleert und der aktuelle (leere) Warenkorb angezeigt.
            warenkorb.clearProdukt();
            warenkorb.showWarenkorb();
        }else{
            if (BenutzerFragen.frageJaNein(scanner, UserMessages.frageHinzufuegenOderReduzierenText())) {
                bestellungAufgeben(warenbestand, warenkorb);
            }
            do {
                // Zeigt den aktuellen Inhalt des Warenkorbs an.
                warenkorb.showWarenkorb();
                System.out.println(UserMessages.reduktionsAnfrageProduktText());

                String medikamentName = sucheMedikamentNachEingabe();

                System.out.println(UserMessages.anfrageReduzierenMengeText(medikamentName));
                int menge = scanner.nextInt();

                // Aktualisiert die Menge des ausgewählten Produkts im Warenkorb.
                warenkorb.aktualisiereBestandNachBestellung(medikamentName, menge);


                // Fragt den Benutzer, ob er weitere Produkte reduzieren möchte.
            }while (BenutzerFragen.frageJaNein(scanner, UserMessages.frageObWeiterReduziertWerdenSollText()));

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
        double betragBestellen = 0;
        //Iterieren über die Produktliste, um den Gesamtpreis zu berechnen.
        for(Map.Entry<String, Integer> entry: produktList.entrySet()){
            betragBestellen += warenbestand.produkte.get(entry.getKey()).getPreis() * entry.getValue();
            // Aktualisierung des Warenbestands
            warenbestand.deleteProdukte(entry.getKey(), entry.getValue(), warenbestand.produkte);
            apotheke.warenkorbZumVersenden.addProdukte(entry.getKey(), entry.getValue());
        }

        System.out.println(UserMessages.bestellungsBestaetigungText());

        //Anzeige des Belegs
        AnzeigenBeleg.anzeigenBeleg(produktList, warenbestand);

        System.out.println(UserMessages.gesamtbetragText(Math.round(betragBestellen * 100.0) / 100.0));

    }

}
