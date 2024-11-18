package info.bestellungsservice.apothekebestellungservice.paket;

import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Paket {
    private int paketNummer;
    private double gewicht;
    private String zielAdresse;
    private boolean zugestellt;
    List<HashMap<String, Integer>> paketInhalt = new ArrayList<>();

    public void clearWaren(String nameMedikament){
    }

    public void showPaketZumVersenden(){
        System.out.println(UserMessagesText.PAKETNUMMER.toString() + paketNummer);
        System.out.println(UserMessagesText.GEWICHT.toString() + gewicht);
        System.out.println(UserMessagesText.ZIELADRESSE.toString() + zielAdresse);
        System.out.println(UserMessagesText.STATUS_LIEFERUNG.toString() + zugestellt);
        System.out.println(paketInhalt.toString());

        visualisierePaketInhalt();
    }

    public void visualisierePaketInhalt(){
        // Erstelle eine Liste, um den Inhalt der Produkte im Paket zu speichern für Visualisirung
        List<String> produktInhalt = new ArrayList<>();
        for (HashMap<String, Integer> waren : paketInhalt) {
            for (String produkt : waren.keySet()) {
                int menge = waren.get(produkt);
                for (int i = 0; i < menge; i++) {
                    produktInhalt.add(produkt);
                }
            }
        }

        String[] produktArray = produktInhalt.toArray(produktInhalt.toArray(new String[0]));

        System.out.println(" _______________________");
        System.out.println("| Paket-Vizualisierung: |");
        System.out.println("|_______________________|");
        // Iteriere über das Produkt-Array und gebe jedes Produkt aus
        for (String produkt: produktArray) {
            System.out.println("|-" + produkt + " ");
        }
        System.out.println("|_______________________|");
    }
}
