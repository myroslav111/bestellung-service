package info.bestellungsservice.apothekebestellungservice.produkt;

import java.math.BigDecimal;

public interface ProduktFactory {
    Produkt createProdukt(String name , int menge, BigDecimal preis, double gewicht);
}
