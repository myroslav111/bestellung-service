package info.bestellungsservice.apothekeBestellungService.produkt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produkt {

    private String produktName;
    private int menge;
    private double preis;
    private double gewicht;

    public Produkt( int menge, double preis, double gewicht) {
        this.produktName = produktName;
        this.menge = menge;
        this.preis = preis;
        this.gewicht = gewicht;
    }
}
