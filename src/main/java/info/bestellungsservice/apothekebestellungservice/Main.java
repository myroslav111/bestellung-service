package info.bestellungsservice.apothekebestellungservice;

import info.bestellungsservice.apothekebestellungservice.apotheke.Apotheke;
import info.bestellungsservice.apothekebestellungservice.apotheke.Warenkorb;
import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerAnmeldeDatenAbfragen;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerUmfrage;
import info.bestellungsservice.apothekebestellungservice.utils.Nachricht;

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
