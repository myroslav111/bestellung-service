package info.bestellungsservice.apothekebestellungservice.utils;

import info.bestellungsservice.apothekebestellungservice.enums.Farbcodes;
import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class AnzeigenBeleg {
    public static void anzeigenBeleg(Map<String, Integer> produktList, Warenbestand warenbestand){
        BigDecimal gesamtBetragBestellung = BigDecimal.ZERO;
        System.out.println("_____________________________________");
        System.out.println("                Beleg                ");
        System.out.println("_____________________________________");

        for (Map.Entry<String, Integer> entry : produktList.entrySet()) {
            String produktName = entry.getKey();
            int anzahl = entry.getValue();

            BigDecimal gesamtProduktPreis = warenbestand.produkte.get(produktName).getPreis().multiply(BigDecimal.valueOf(anzahl));

            gesamtBetragBestellung = gesamtBetragBestellung.add(gesamtProduktPreis);

            System.out.println(produktName);
            System.out.println("                     " + anzahl + "St.  " + gesamtProduktPreis.setScale(2, RoundingMode.HALF_UP) + "€");
        }

        System.out.println("_____________________________________");


        String gesamtbetragText = UserMessagesText.GESAMTBETRAG.format(gesamtBetragBestellung.setScale(2, RoundingMode.HALF_UP));
        System.out.println(Farbcodes.HELLBLAU.formatText(gesamtbetragText));
    }
}
