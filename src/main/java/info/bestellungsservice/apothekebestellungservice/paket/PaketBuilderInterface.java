package info.bestellungsservice.apothekebestellungservice.paket;

public interface PaketBuilderInterface extends Builder<Paket> {
    PaketBuilderInterface paketNummer(int paketNummer);
    PaketBuilderInterface gewicht(double gewicht);
    PaketBuilderInterface zielAdresse(String zielAdresse);
    void addWare(String waren, int menge);
    PaketBuilderInterface zugestellt(boolean zugestellt);

}
