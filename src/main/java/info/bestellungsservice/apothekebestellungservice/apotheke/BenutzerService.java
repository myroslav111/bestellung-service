package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.utils.AbfrageAnmeldedaten;
import info.bestellungsservice.apothekebestellungservice.utils.Nachricht;
import info.bestellungsservice.apothekebestellungservice.utils.UserMessages;

import java.util.Scanner;

public class BenutzerService {
    UserFileManager userFileManager = new UserFileManager();

    public boolean login(String email, String psw) {
        // Erstellt eine Instanz von UserFileManager, um auf die Benutzerdaten zuzugreifen
        //UserFileManager userFileManager = new UserFileManager();

        if (userFileManager.isKunde(email, psw)) {
            return true;
        }else {
            System.out.println(UserMessages.falscheLoginDatenText());
            return false;
        }
    }

    public boolean benutzerAnmeldungProzess(Scanner scanner, Apotheke apotheke, UserFileManager userFileManager){
        int counter = 0;
        boolean kontoAnmeldungErfolgreich = false;
        do {
            counter += 1;
            // Benutzer wird nach E-Mail und Passwort gefragt
            System.out.println(UserMessages.loginDatenAbfrageText());
            String email = AbfrageAnmeldedaten.userInputEmail(scanner);
            String psw = AbfrageAnmeldedaten.userInputPasswort(scanner);

            // Überprüft die Anmeldedaten
            if (login(email, psw)) {

                // Sucht nach dem Namen des Benutzers basierend auf der E-Mail
                System.out.println(UserMessages.loginErfolgreichText());
                Nachricht.begruessung(userFileManager.getKundenName(email), userFileManager.getKundenVorname(email));
                kontoAnmeldungErfolgreich = true;
                for (Kunde kunde: userFileManager.getKundenDatenAsList()){
                    if (kunde.getEmail().equalsIgnoreCase(email)) {
                        apotheke.warenkorbZumVersenden.setKundenummerCurrentWarenkorb(kunde.getKundennummer());
                    }
                }
            }

            if (counter >= 3) {
                break;
            }
        }while (!kontoAnmeldungErfolgreich);

        return kontoAnmeldungErfolgreich;
    }
}
