package info.bestellungsservice.apothekebestellungservice.utils;

public class Nachricht {

    public static void begruessung(String kundeName, String vorname) {
        System.out.println();
        System.out.println(UserMessages.apothekenBegruessungText(kundeName, vorname));
    }
}
