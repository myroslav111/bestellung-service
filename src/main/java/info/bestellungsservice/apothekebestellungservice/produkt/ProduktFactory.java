package info.bestellungsservice.apothekebestellungservice.produkt;

public interface ProduktFactory {
    Produkt createProdukt(String name ,int menge, double preis, double gewicht);
}
