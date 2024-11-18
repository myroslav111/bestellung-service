package info.bestellungsservice.apothekebestellungservice.utils;

import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;

import java.util.Scanner;

public class AbfrageAnmeldedaten {

    public static String userInputEmail(Scanner scanner){
        System.out.println(UserMessagesText.EMAIL);
        return scanner.next().toLowerCase();
    }

    public static String userInputPasswort(Scanner scanner){
        System.out.println(UserMessagesText.PASSWORT);
        return scanner.next();
    }
}
