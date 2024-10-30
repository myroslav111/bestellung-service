package info.bestellungsservice.apothekeBestellungService;

import info.bestellungsservice.apothekeBestellungService.apotheke.Apotheke;
import info.bestellungsservice.apothekeBestellungService.apotheke.Warenkorb;
import info.bestellungsservice.apothekeBestellungService.kunde.Kunde;
import info.bestellungsservice.apothekeBestellungService.logistikzentrum.Logistikzentrum;
import info.bestellungsservice.apothekeBestellungService.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekeBestellungService.utils.BenutzerUmfrage;


import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Apotheke apotheke = new Apotheke();
        Logistikzentrum logistikzentrum = new Logistikzentrum();
        Warenkorb warenkorb = new Warenkorb();
        Warenbestand warenbestand = new Warenbestand();
        Kunde kunde = new Kunde();

        kunde.setKunde(scanner);



        System.out.println("Geben Sie Ihre Email");
        String email = scanner.next();
        System.out.println("Geben Sie Ihre Passwort");
        String psw = scanner.next();



        if (apotheke.login(kunde, email, psw)) {
            System.out.println("Willkommen in der Apotheke " + kunde.name);
            apotheke.bestellungAufgeben(warenbestand, warenkorb);
            warenbestand.showWarenBestand();

        } else {
            System.out.println("Login fehlgeschlagen.");
        }

    }

}
