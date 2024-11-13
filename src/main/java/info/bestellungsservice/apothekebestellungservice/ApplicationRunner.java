package info.bestellungsservice.apothekebestellungservice;

import info.bestellungsservice.apothekebestellungservice.apotheke.Apotheke;
import info.bestellungsservice.apothekebestellungservice.apotheke.Warenkorb;
import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.utils.AbfrageAnmeldedaten;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerFragen;
import info.bestellungsservice.apothekebestellungservice.utils.Nachricht;

import java.util.Scanner;

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

        //userFileManager.liestUsers();
        anmelden(apotheke, userFileManager, warenbestand, warenkorb, kunde);

        versendenBestellungZumLogistik(apotheke, warenbestand, apotheke.warenkorbZumVersenden, userFileManager);
    }

    private void anmelden(Apotheke apotheke, UserFileManager userFileManager, Warenbestand warenbestand,
                          Warenkorb warenkorb, Kunde kunde) {
        String message = "Besitzen Sie bereits ein Konto? (y/n)";
        if (BenutzerFragen.frageJaNein(scanner, message)) {
            startBestellprozess(apotheke, userFileManager, warenbestand, warenkorb);
        } else {
            registrierung(userFileManager, apotheke, warenbestand, warenkorb, kunde);
        }
    }

    private void registrierung(UserFileManager userFileManager, Apotheke apotheke, Warenbestand warenbestand,
                               Warenkorb warenkorb, Kunde kunde) {
        // Überprüft, ob der Benutzername (basierend auf der E-Mail) bereits existiert

        String userEmailInput = BenutzerAnmeldeDatenAbfragen.emailAbfragen(scanner);
        if (userFileManager.kundeEmailNachBedienungSuchen(userEmailInput)) {
            System.out.println("Existiert bereits. \n Wiederholen Sie Ihre email und passwort");
            benutzerOrdering(apotheke, userFileManager, warenbestand, warenkorb);

        }

        // Setzt die eingegebene E-Mail für das neue Kundenkonto
        kunde.setEmail(userEmailInput);
        // Initialisiert das Kundenobjekt
        kunde.setKunde(scanner);
        apotheke.warenkorbZumVersenden.setKundenummerCurrentWarenkorb(kunde.getKundennummer());
        System.out.println(kunde.getKundennummer());
        // kunde in db hinzufügt
        userFileManager.addKunde(kunde);
        Nachricht.begruessung(kunde.name);
        // Ermöglicht dem neuen Benutzer, eine Bestellung aufzugeben
        apotheke.bestellungAufgeben(warenbestand, warenkorb);
    }

    private void startBestellprozess(Apotheke apotheke, UserFileManager userFileManager, Warenbestand warenbestand,
                                     Warenkorb warenkorb) {
        if (apotheke.benutzerAnmeldungProzess(scanner, apotheke, userFileManager)) {
            apotheke.bestellungAufgeben(warenbestand, warenkorb);
            return;
        }
        System.out.println("Drei falsche Versuche. \nBitte wenden Sie sich an Myro.");
    }

    private void versendenBestellungZumLogistik(Apotheke apotheke, Warenbestand warenbestand, Warenkorb warenkorbZumVersenden
            , UserFileManager userFileManager) {
        apotheke.createUndSendPaketAusWarenkorb(warenbestand, warenkorbZumVersenden, userFileManager);
    }


}