package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.enums.Farbcodes;
import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;
import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.utils.AbfrageAnmeldedaten;


import java.util.Scanner;

public class BenutzerService {
    UserFileManager userFileManager = new UserFileManager();

    public boolean login(String email, String psw) {
        // Erstellt eine Instanz von UserFileManager, um auf die Benutzerdaten zuzugreifen
        //UserFileManager userFileManager = new UserFileManager();

        if (userFileManager.isKunde(email, psw)) {
            return true;
        }else {
            String falscheLoginDatenText = UserMessagesText.FALSCHE_LOGIN_DATEN.toString();
            System.out.println(Farbcodes.ROT.formatText(falscheLoginDatenText));
            return false;
        }
    }

    public boolean benutzerAnmeldungProzess(Scanner scanner, Apotheke apotheke, UserFileManager userFileManager){
        int counter = 0;
        boolean kontoAnmeldungErfolgreich = false;
        do {
            counter += 1;
            // Benutzer wird nach E-Mail und Passwort gefragt
            String loginDatenAbfrageText = UserMessagesText.LOGIN_DATEN_ABFRAGE.toString();
            System.out.println(loginDatenAbfrageText);
            String email = AbfrageAnmeldedaten.userInputEmail(scanner);
            String psw = AbfrageAnmeldedaten.userInputPasswort(scanner);

            // Überprüft die Anmeldedaten
            if (login(email, psw)) {
                String loginErfolgreichText = UserMessagesText.LOGIN_ERFOLGREICH.toString();
                System.out.println(Farbcodes.GELB.formatText(loginErfolgreichText));
                // Sucht nach dem Namen des Benutzers basierend auf der E-Mail

                String begruessung = UserMessagesText.APOTHEKEN_BEGRUESSUNG
                        .format(userFileManager.getKundenName(email), userFileManager.getKundenVorname(email));
                System.out.println(Farbcodes.GELB.formatText(begruessung));
                kontoAnmeldungErfolgreich = true;
                for (Kunde kunde: userFileManager.getKundenDatenAsList()){
                    if (kunde.getEmail().equalsIgnoreCase(email)) {
                        int kundennummer = kunde.getKundennummer();
                        apotheke.warenkorbZumVersenden.setKundenummerCurrentWarenkorb(kundennummer);
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
