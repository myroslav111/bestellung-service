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
import java.util.logging.Logger;

public class ApplicationRunner {

    private Scanner scanner;

    public ApplicationRunner() {
        this.scanner = new Scanner(System.in);
    }


    public void run() {
        Apotheke apotheke = new Apotheke();
        Warenkorb warenkorb = new Warenkorb();
        Warenbestand warenbestand = new Warenbestand();
        UserFileManager userFileManager = new UserFileManager();
        Kunde kunde = new Kunde();

        userFileManager.liestUsers();

        // Fragt den Benutzer, ob er bereits ein Konto besitzt (Ja/Nein)
        checkIfUserSayYes(apotheke, userFileManager, warenbestand, warenkorb, kunde);
    }

    private void checkIfUserSayYes(Apotheke apotheke, UserFileManager userFileManager, Warenbestand warenbestand, Warenkorb warenkorb, Kunde kunde) {
        if (BenutzerUmfrage.userAuswahlJaOderNein(scanner,"Besitzen Sie bereits ein Konto? y|n")) {
            benutzerOrdering(apotheke, userFileManager, warenbestand, warenkorb);

            return;
        }

        ohneBenutzerForfahren(userFileManager, apotheke, warenbestand, warenkorb, kunde);
    }

    private void ohneBenutzerForfahren(UserFileManager userFileManager, Apotheke apotheke, Warenbestand warenbestand, Warenkorb warenkorb, Kunde kunde) {
        // Überprüft, ob der Benutzername (basierend auf der E-Mail) bereits existiert
        if (userFileManager.kundeEmailNachBedienungSuchen(BenutzerAnmeldeDatenAbfragen.emailAbfragen(scanner))) {
            System.out.println("Schon exestiert \n Wiedercholen Sie Ihre email und passwort");
            benutzerOrdering(apotheke, userFileManager, warenbestand, warenkorb);
        }
        // Erstellt ein neues Kundenkonto, wenn der Benutzer noch keines hat
        kunde.setKunde(scanner);
        // kunde in db hinzufügt
        userFileManager.addKunde(kunde);
        Nachricht.benutzerBegrussen(kunde.name);
        // Ermöglicht dem neuen Benutzer, eine Bestellung aufzugeben
        apotheke.bestellungAufgeben(warenbestand, warenkorb);
    }

    private void benutzerOrdering(Apotheke apotheke, UserFileManager userFileManager, Warenbestand warenbestand, Warenkorb warenkorb) {
        if (apotheke.benutzerAnmeldungProzess(scanner, apotheke, userFileManager)) {
            apotheke.bestellungAufgeben(warenbestand, warenkorb);
            // wenn nein das program beendet wird
            return;
        }

        System.out.println("Sie können sich erneut registrieren \n EXIT");
    }
}
