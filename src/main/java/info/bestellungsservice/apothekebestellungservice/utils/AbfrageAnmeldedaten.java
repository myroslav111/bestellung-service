package info.bestellungsservice.apothekebestellungservice.utils;


import java.util.Scanner;

public class AbfrageAnmeldedaten {

    public static String userInputEmail(Scanner scanner){
        System.out.println("Email: ");
        return scanner.next().toLowerCase();
    }

    public static String userInputPasswort(Scanner scanner){
        System.out.println("Passwort");
        return scanner.next();
    }
}
