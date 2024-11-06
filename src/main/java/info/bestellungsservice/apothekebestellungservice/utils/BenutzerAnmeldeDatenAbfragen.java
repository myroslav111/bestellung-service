package info.bestellungsservice.apothekebestellungservice.utils;

import java.util.Scanner;

public class BenutzerAnmeldeDatenAbfragen {

    public static String emailAbfragen(Scanner scanner){
        System.out.println("Email-Adresse");
        String email = scanner.next().toLowerCase();
        return email;
    }

    public static String passwortAbfragen(Scanner scanner){
        System.out.println("Passwort");
        String psw = scanner.next();
        return psw;
    }
}
