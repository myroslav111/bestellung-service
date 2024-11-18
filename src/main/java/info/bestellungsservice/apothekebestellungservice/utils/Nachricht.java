package info.bestellungsservice.apothekebestellungservice.utils;

import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;

public class Nachricht {

    public static void begruessung(String kundeName, String vorname) {
        System.out.println();
        System.out.println(UserMessagesText.APOTHEKEN_BEGRUESSUNG.format(kundeName, vorname));
    }
}
