package info.bestellungsservice.apothekebestellungservice.produkt;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Produkt {

    private String produktName;
    private int menge;
    private BigDecimal preis;
    private double gewicht;

    public Produkt(String produktName, int menge, BigDecimal preis, double gewicht) {
        this.produktName = produktName;
        this.menge = menge;
        this.preis = preis;
        this.gewicht = gewicht;
    }


}
