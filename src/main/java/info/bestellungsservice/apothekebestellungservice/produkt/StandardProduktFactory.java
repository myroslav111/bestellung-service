package info.bestellungsservice.apothekebestellungservice.produkt;

public class StandardProduktFactory implements ProduktFactory {
    @Override
    public Produkt createProdukt(String name, int menge, double preis, double gewicht) {
        return new Produkt(name, menge, preis, gewicht);
    }
}
