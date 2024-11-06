package info.bestellungsservice.apothekeBestellungService.utils;

import info.bestellungsservice.apothekeBestellungService.logistikzentrum.Warenbestand;


import java.util.Map;

public class AnzeigenBeleg {
    public static void anzeigenBeleg(Map<String, Integer> produktList, Warenbestand warenbestand){
        System.out.println("_____________________________________");
        System.out.println("                Beleg                ");
        System.out.println("_____________________________________");

        for(Map.Entry<String, Integer> entry: produktList.entrySet()){
            System.out.println(entry.getKey());
            System.out.println("                     " + entry.getValue() + "St.  " +
                    (warenbestand.produkte.get(entry.getKey()).getPreis()
                            * entry.getValue()) + "euro");
        }


        System.out.println("_____________________________________");
    }

}
