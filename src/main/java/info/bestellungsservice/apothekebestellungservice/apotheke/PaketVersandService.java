package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Logistikzentrum;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.paket.Paket;
import info.bestellungsservice.apothekebestellungservice.paket.PaketBuilder;
import info.bestellungsservice.apothekebestellungservice.paket.PaketBuilderInterface;
import info.bestellungsservice.apothekebestellungservice.utils.Suche;

public class PaketVersandService {

    public void createUndSendPaketAusWarenkorb(Warenbestand warenbestand, Warenkorb warenkorbZumVersenden,
                                               UserFileManager userFileManager) {
        Logistikzentrum logistikzentrum = Logistikzentrum.getInstance();

        double gewichtWarenkorb = warenkorbZumVersenden.getGewichtWarenkorb(warenbestand, warenkorbZumVersenden);
        String zielAdresse = Suche.findeZielAdresse(userFileManager, warenkorbZumVersenden);
        PaketBuilderInterface paketBuilder = new PaketBuilder()
                .paketNummer((int)(Math.random() * 1000))
                .gewicht(gewichtWarenkorb)
                .zielAdresse(zielAdresse)
                .zugestellt(false);
//änderung

        addProdukteZumPaket(warenkorbZumVersenden, paketBuilder);

        Paket paket = paketBuilder.build();

        paket.showPaketZumVersenden();
        logistikzentrum.paketeZumVersenden.add(paket);
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
