package info.bestellungsservice.apothekebestellungservice.utils;

import info.bestellungsservice.apothekebestellungservice.enums.Farbcodes;
import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;


import java.util.Map;

public class AnzeigenBeleg {
    public static void anzeigenBeleg(Map<String, Integer> produktList, Warenbestand warenbestand){
        double betragBestellen = 0;
        System.out.println("_____________________________________");
        System.out.println("                Beleg                ");
        System.out.println("_____________________________________");

        for(Map.Entry<String, Integer> entry: produktList.entrySet()){
            betragBestellen += warenbestand.produkte.get(entry.getKey()).getPreis() * entry.getValue();
            System.out.println(entry.getKey());
            System.out.println("                     " + entry.getValue() + "St.  " +
                    (warenbestand.produkte.get(entry.getKey()).getPreis()
                            * entry.getValue()) + "€");
        }

        System.out.println("_____________________________________");

        String gesamtbetragText = UserMessagesText.GESAMTBETRAG.format(Math.round(betragBestellen * 100.0) / 100.0);
        System.out.println(Farbcodes.HELLBLAU.formatText(gesamtbetragText));
    }
}
