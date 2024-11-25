package info.bestellungsservice.apothekebestellungservice.produkt;

public interface ProduktFactory {
    Produkt createMedikament(String name , int menge, double preis, double gewicht);
}
