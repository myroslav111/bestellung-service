package info.bestellungsservice.apothekeBestellungService;

import info.bestellungsservice.apothekeBestellungService.apotheke.Apotheke;
import info.bestellungsservice.apothekeBestellungService.apotheke.Warenkorb;
import info.bestellungsservice.apothekeBestellungService.kunde.Kunde;
import info.bestellungsservice.apothekeBestellungService.kunde.UserFileManager;
import info.bestellungsservice.apothekeBestellungService.logistikzentrum.Logistikzentrum;
import info.bestellungsservice.apothekeBestellungService.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekeBestellungService.utils.BenutzerAnmeldeDatenAbfragen;
import info.bestellungsservice.apothekeBestellungService.utils.BenutzerUmfrage;
import info.bestellungsservice.apothekeBestellungService.utils.Nachricht;

import java.io.IOException;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Apotheke apotheke = new Apotheke();
        Warenkorb warenkorb = new Warenkorb();
        Warenbestand warenbestand = new Warenbestand();
        UserFileManager userFileManager = new UserFileManager();
        Kunde kunde = new Kunde();


        userFileManager.liestUsers();

        // Fragt den Benutzer, ob er bereits ein Konto besitzt (Ja/Nein)
        if (BenutzerUmfrage.userAuswahlJaOderNein(scanner, "Besitzen Sie bereits ein Konto? y|n")) {
            // wenn ja dann kann man eine bestellung aufgeben
            if (apotheke.benutzerAnmeldungProzess(scanner, apotheke, userFileManager)) {
                apotheke.bestellungAufgeben(warenbestand, warenkorb);
                // wenn nein das program beendet wird
            }else {
                System.out.println("Sie können sich erneut registrieren \n EXIT");
            }
        }else {
            // Überprüft, ob der Benutzername (basierend auf der E-Mail) bereits existiert
            if (userFileManager.kundeEmailNachBedienungSuchen(BenutzerAnmeldeDatenAbfragen.emailAbfragen(scanner))) {
                System.out.println("Schon exestiert \n Wiedercholen Sie Ihre email und passwort");
                if (apotheke.benutzerAnmeldungProzess(scanner, apotheke, userFileManager)) {
                    apotheke.bestellungAufgeben(warenbestand, warenkorb);
                    // wenn nein das program beendet wird
                }else {
                    System.out.println("Sie können sich erneut registrieren \n EXIT");
                }
            }
            // Erstellt ein neues Kundenkonto, wenn der Benutzer noch keines hat
            kunde.setKunde(scanner);
            // kunde in db hinzufügt
            userFileManager.addKunde(kunde);
            Nachricht.benutzerBegrussen(kunde.name);
            // Ermöglicht dem neuen Benutzer, eine Bestellung aufzugeben
            apotheke.bestellungAufgeben(warenbestand, warenkorb);
        }

    }

}
