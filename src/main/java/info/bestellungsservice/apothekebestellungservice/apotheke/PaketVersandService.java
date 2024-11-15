package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Logistikzentrum;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.paket.Paket;
import info.bestellungsservice.apothekebestellungservice.paket.PaketBuilder;
import info.bestellungsservice.apothekebestellungservice.paket.PaketBuilderInterface;

public class PaketVersandService {

    public void createUndSendPaketAusWarenkorb(Warenbestand warenbestand, Warenkorb warenkorbZumVersenden,
                                               UserFileManager userFileManager) {
        Logistikzentrum logistikzentrum = Logistikzentrum.getInstance();

        PaketBuilderInterface paketBuilder = new PaketBuilder()
                .paketNummer((int)(Math.random() * 1000))
                .gewicht(warenkorbZumVersenden.getGewichtWarenkorb(warenbestand, warenkorbZumVersenden))
                .zielAdresse(findeZielAdresse(userFileManager, warenkorbZumVersenden))
                .zugestellt(false);


        addProdukteZumPaket(warenkorbZumVersenden, paketBuilder);

        Paket paket = paketBuilder.build();

        paket.showPaketZumVersenden();
        logistikzentrum.paketeZumVersenden.add(paket);
    }

    public String findeZielAdresse(UserFileManager userFileManager, Warenkorb warenkorbZumVersenden) {
        // Durchsuche die Kundendaten, um die Zieladresse für das Paket zu finden
        for (Kunde curKunde: userFileManager.getKundenDatenAsList()){
            if (curKunde.getKundennummer() == warenkorbZumVersenden.getKundenummerCurrentWarenkorb()) {
                return curKunde.getAdresse();
            }
        }
        return null;
    }

    public void addProdukteZumPaket(Warenkorb warenkorbZumVersenden, PaketBuilderInterface paketBuilder){
        // Fügt die Produkte aus dem Warenkorb zum Paket hinzu
        for (String produkt: warenkorbZumVersenden.produktList.keySet()){
            String produktName;
            produktName = produkt;
            int produktMenge = warenkorbZumVersenden.produktList.get(produktName);
            paketBuilder.addWare(produktName, produktMenge);
        }
    }
}
