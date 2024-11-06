package info.bestellungsservice.apothekebestellungservice.utils;

import java.util.Scanner;

public class BenutzerUmfrage {
    public static boolean userAuswahlJaOderNein(Scanner scanner, String message){
        System.out.println(message);
        System.out.println();
        System.out.println();
        boolean fortfahren = (Character.toLowerCase(scanner.next().charAt(0)) == 'y') ? true : false;
        return fortfahren;
    }

    public static void userAnfrageMailPsw(){
        System.out.println("Geben Sie Ihre Email und Passwort ein");
    }
}
