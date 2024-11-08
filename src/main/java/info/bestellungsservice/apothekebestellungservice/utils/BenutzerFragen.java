package info.bestellungsservice.apothekebestellungservice.utils;

import java.util.Scanner;

public class BenutzerFragen {

    public static boolean frageJaNein(Scanner scanner, String message) {
        System.out.println(message + "\n\n");
        return Character.toLowerCase(scanner.next().charAt(0)) == 'y';
    }

}
