package info.bestellungsservice.apothekeBestellungService.utils;

import info.bestellungsservice.apothekeBestellungService.kunde.UserFileManager;

import java.util.Scanner;

public class BenutzerAnmeldeDatenAbfragen {

    public static String emailAbfragen(Scanner scanner){
        System.out.println("Geben Sie Ihre Email");
        String email = scanner.next().toLowerCase();
        return email;
    }

    public static String passwortAbfragen(Scanner scanner){
        System.out.println("Geben Sie Ihre Passwort");
        String psw = scanner.next();
        return psw;
    }
}
