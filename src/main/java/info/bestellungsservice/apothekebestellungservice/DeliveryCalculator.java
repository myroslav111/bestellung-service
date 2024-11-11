package info.bestellungsservice.apothekebestellungservice;

public class DeliveryCalculator {

    public static void berechneLieferkosten(double entfernung, double nutzlast){

        double gesamtPreis = 0.0;
        if (entfernung <=  25 && nutzlast <= 12) {
            int preisNutzlast;
            if (nutzlast > 6) {
                preisNutzlast = 3;
            }else {
                preisNutzlast = 7;
            }
            gesamtPreis = preisNutzlast + entfernung * 0.4;
            System.out.println("Gesamtpreis des Transports: " + Math.round(gesamtPreis * 100.0) / 100.0);

        }else{
            System.out.println("Die Drohne kann nur eine Nutzlast von maximal 12kg transportieren und eine Entfernung von maximal 25km anfliegen.");

        }

    }

}
