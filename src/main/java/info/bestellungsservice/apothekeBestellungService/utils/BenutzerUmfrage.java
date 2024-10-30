package info.bestellungsservice.apothekeBestellungService.utils;

import java.util.Scanner;

public class BenutzerUmfrage {
    public static boolean userAuswahlJaOderNein(Scanner scanner, String message){
        System.out.println(message);
        boolean fortfahren = (Character.toLowerCase(scanner.next().charAt(0)) == 'y') ? true : false;
        return fortfahren;
    }

    public static void userAnfrageMailPsw(){
        System.out.println("Geben Sie Ihre Email und Passwort ein");
    }
}
