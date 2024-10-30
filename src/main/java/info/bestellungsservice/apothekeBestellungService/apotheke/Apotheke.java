package info.bestellungsservice.apothekeBestellungService.apotheke;

import info.bestellungsservice.apothekeBestellungService.ProduktList;
import info.bestellungsservice.apothekeBestellungService.kunde.Kunde;
import info.bestellungsservice.apothekeBestellungService.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekeBestellungService.utils.AnzeigenBeleg;
import info.bestellungsservice.apothekeBestellungService.utils.BenutzerUmfrage;

import java.util.Map;
import java.util.Scanner;

public class Apotheke implements BestellService {
    public Warenkorb warenkorb = new Warenkorb();


    @Override
    public void addWarenkorb(String produkt, int anzahl) {

    }

    @Override
    public void removeWarenkorb(String produkt, int anzahl) {

    }

    @Override
    // produktList, die die Produkte als Schlüssel
    // (Strings) und die zu bestellenden Mengen als Werte (Integers) enthält.
    // warenbestand die Informationen über die verfügbaren Produkte und deren Preise enthält
    // der Klasse AnzeigenBeleg, die für die Anzeige des Bestellbelegs zuständig ist.
    public void berechneGesamtpreis(Map<String, Integer> produktList,
                                    Warenbestand warenbestand) {
            double betragBestellen = 0;
            //Iterieren über die Produktliste:
            for(Map.Entry<String, Integer> entry: produktList.entrySet()){
                betragBestellen += warenbestand.produkte.get(entry.getKey()).getPreis() * entry.getValue();
                // Aktualisierung des Warenbestands
                warenbestand.entfernenProdukte(entry.getKey(), entry.getValue(), warenbestand.produkte);
            }

            //Anzeige des Belegs
            AnzeigenBeleg.anzeigenBeleg(produktList, warenbestand);
            System.out.println("                     Insgesamt: " + betragBestellen);

    }

    @Override
    public void berechneStrecke() {

    }

    @Override
    public void bestellungAbschicken() {

    }

    // warenbestand die Informationen über die verfügbaren Produkte enthält.
    // warenkorb die Produkte, die der Benutzer bestellt hat
    public void bestellungAufgeben(Warenbestand warenbestand, Warenkorb warenkorb) {
        Scanner scanner = new Scanner(System.in);
        // Die Methode fragt den Benutzer, ob er etwas bestellen möchte
        if (BenutzerUmfrage.userAuswahlJaOderNein(scanner, "Wollen Sie etwas bestellen y/n")) {
            System.out.println("Das haben wir im Angebot:");
            // Jedes Produkt wird durch einen Aufruf von produkt.getMedikamenteName() angezeigt.
            for(ProduktList produkt: ProduktList.values()){
                System.out.println(produkt.getMedikamenteName());
            }

            // ermöglicht, mehrere Produkte zu bestellen.
            do{
                System.out.println("Was wollen Sie bestellen");
                scanner.nextLine();
                String nameMedikament = scanner.nextLine();
                System.out.println("Wie viele Stück");
                int menge = scanner.nextInt();
                warenkorb.addProdukte(nameMedikament, menge);
            }
            while (BenutzerUmfrage.userAuswahlJaOderNein(scanner, "Wollen Sie noch etwas bestellen?"));

            System.out.println("Hier ist Ihre Bestellung");
            // Bestellübersicht
            warenkorb.showWarenkorb();
            // Bestellbestätigung
            // Der Benutzer wird gefragt, ob er die Bestellung bestätigen möchte.
            // Wenn der Benutzer zustimmt, wird die Methode berechneGesamtpreis aufgerufen,
            // um den Gesamtpreis der Bestellung zu berechnen und den Beleg anzuzeigen.
            if (BenutzerUmfrage.userAuswahlJaOderNein(scanner, "Bestätigen Sie die Bestellung? y/n")) {
                berechneGesamtpreis(warenkorb.produktList, warenbestand);
            }
            //warenbestand.showWarenBestand();


        }

    }

    public boolean login(Kunde kunde, String email, String psw) {
        // E-Mail und Passwort aus der Benutzereingabe anfordern und mit den Kundendaten vergleichen
        if (kunde.getEmail().equals(email) && kunde.getPasswort().equals(psw)) {
            return true;
        }
        // Gibt true, wenn E-Mail und Passwort übereinstimmen
        return  false;
    }
}
