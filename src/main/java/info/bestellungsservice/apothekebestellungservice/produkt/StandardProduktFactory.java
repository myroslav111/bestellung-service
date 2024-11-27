package info.bestellungsservice.apothekebestellungservice.produkt;

import java.math.BigDecimal;

public class StandardProduktFactory implements ProduktFactory {
    @Override
    public Produkt createProdukt(String name, int menge, BigDecimal preis, double gewicht) {
        return new Produkt(name, menge, preis, gewicht);
    }
}
