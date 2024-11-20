package info.bestellungsservice.apothekebestellungservice.utils;

import info.bestellungsservice.apothekebestellungservice.apotheke.Warenkorb;
import info.bestellungsservice.apothekebestellungservice.enums.ProduktList;
import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;

import java.util.Scanner;

public class Suche {

    public static String sucheMedikamentNachEingabe(Scanner scanner) {
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

    public static String findeZielAdresse(UserFileManager userFileManager, Warenkorb warenkorbZumVersenden) {
        // Durchsuche die Kundendaten, um die Zieladresse für das Paket zu finden
        for (Kunde curKunde: userFileManager.getKundenDatenAsList()){
            if (curKunde.getKundennummer() == warenkorbZumVersenden.getKundenummerCurrentWarenkorb()) {
                return curKunde.getAdresse();
            }
        }
        return null;
    }
}
