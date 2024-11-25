package info.bestellungsservice.apothekebestellungservice;

import info.bestellungsservice.apothekebestellungservice.apotheke.Apotheke;
import info.bestellungsservice.apothekebestellungservice.apotheke.Warenkorb;
import info.bestellungsservice.apothekebestellungservice.enums.Farbcodes;
import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;
import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.produkt.ProduktFactory;
import info.bestellungsservice.apothekebestellungservice.produkt.StandardProduktFactory;
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
        Apotheke apotheke = Apotheke.getInstance();
        Warenkorb warenkorb = new Warenkorb();
        ProduktFactory factory = new StandardProduktFactory();
        Warenbestand warenbestand = new Warenbestand(factory);
        UserFileManager userFileManager = new UserFileManager();
        Kunde kunde = new Kunde();

        boolean isUserLoggedIn = anmelden(apotheke, userFileManager);
        if (isUserLoggedIn) {
            apotheke.bestellverfahren.bestellungAufgeben(warenbestand, warenkorb);
        }else{
            System.out.println(UserMessagesText.REGISTRATION_PROMPT);

            registrierung(userFileManager, apotheke, kunde);
            apotheke.bestellverfahren.bestellungAufgeben(warenbestand, warenkorb);

        }

        boolean isWarenkorbEmpty = warenkorb.produktList.isEmpty();
        if (isWarenkorbEmpty) return;

        sendBestellungToLogistikzentrum(apotheke, warenbestand, userFileManager);
    }



    private void sendBestellungToLogistikzentrum(Apotheke apotheke, Warenbestand warenbestand
            , UserFileManager userFileManager) {
        apotheke.paketVersandService.createUndSendPaketAusWarenkorb(warenbestand, apotheke.warenkorbZumVersenden, userFileManager);
    }


    private boolean checkUserRegistriert(Apotheke apotheke, UserFileManager userFileManager) {
        boolean isUserRegistriert = apotheke.benutzerService.benutzerAnmeldungProzess(scanner, apotheke, userFileManager);
        if (isUserRegistriert) return true;
        System.out.println(Farbcodes.ROT.formatText(UserMessagesText.VERSUCH_LIMIT_ERREICHT.toString()));
        return false;
    }

    private boolean anmelden(Apotheke apotheke, UserFileManager userFileManager) {
        String message = UserMessagesText.KONTO_ABFRAGE.toString();
        if (BenutzerFragen.frageJaNein(scanner, message)) {
            return checkUserRegistriert(apotheke, userFileManager);
        }
        return false;
    }

    private void registrierung(UserFileManager userFileManager, Apotheke apotheke, Kunde kunde) {
        // Überprüft, ob der Benutzername (basierend auf der E-Mail) bereits existiert
        String userEmailInput = AbfrageAnmeldedaten.userInputEmail(scanner);
        if (userFileManager.checkEmailVorhanden(userEmailInput)) {
            System.out.println(UserMessagesText.ACCOUNT_EXISTIERT);
            checkUserRegistriert(apotheke, userFileManager);
        } else {
            // Setzt die eingegebene E-Mail für das neue Kundenkonto
            kunde.setEmail(userEmailInput);
            // Initialisiert das Kundenobjekt
            kunde.setKunde(scanner);
            apotheke.warenkorbZumVersenden.setKundenummerCurrentWarenkorb(kunde.getKundennummer());
            System.out.println(kunde.getKundennummer());
            // kunde in db hinzufügt
            userFileManager.addKunde(kunde);
            Nachricht.begruessung(kunde.name, kunde.vorname);
        }
    }

}