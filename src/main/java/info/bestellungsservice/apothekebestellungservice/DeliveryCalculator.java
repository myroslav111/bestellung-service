package info.bestellungsservice.apothekebestellungservice;

public class DeliveryCalculator {

    public static void calculatePreisDelivery(double distance, double nutzlast){

        double gesamtpreis = 0.0;
        if (distance <=  25 && nutzlast <= 12) {
            int preis_nutzlast;
            if (nutzlast > 6) {
                preis_nutzlast = 3;
            }else {
                preis_nutzlast = 7;
            }
            gesamtpreis = preis_nutzlast + distance * 0.4;
            System.out.println("Gesamtpreis des Transports: " + Math.round(gesamtpreis * 100.0) / 100.0);

        }else{
            System.out.println("Die Drohne kann nur eine Nutzlast von maximal 12kg transportieren und eine Entfernung von maximal 25km anfliegen");

        }

    }

}
