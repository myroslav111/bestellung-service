package info.bestellungsservice.apothekebestellungservice.enums;

public enum UserMessagesText {
    APOTHEKEN_BEGRUESSUNG("Willkommen in der Apotheke %s %s"),
    KONTO_ABFRAGE("Besitzen Sie bereits ein Konto? (y/n)"),
    BESTELLUNGS_BESTAETIGUNG("Hier ist Ihre Bestellung:"),
    GESAMTBETRAG("                 Insgesamt: %s€"),
    LAGERBESTAND_WARNUNG("Niedriger Lagerbestand."),
    LAGERBESTAND_WARNUNG_MIT_VERFUEGBARKEIT("Niedriger Lagerbestand. \nVerfügbar sind %s St."),
    BESTELL_BESTAETIGUNGS_FRAGE("Bestätigen Sie die Bestellung? (y/n)"),
    BESTELL_AENDERUNGS_OPTION("Wollen Sie ganze Bestellung abbrechen (y) \noder nur etwas korrigieren (k)"),
    FRAGE_HINZUFUEGEN_ODER_REDUZIEREN("Möchten Sie etwas hinzufügen (y) \noder reduzieren (r)?"),
    REDUKTIONS_ANFRAGE_PRODUKT("Was möchten Sie reduzieren, geben Sie die Produktnummer ein:"),
    ANFRAGE_REDUZIEREN_MENGE("Wie viele %s möchten Sie reduzieren?:"),
    FRAGE_OB_WEITER_REDUZIERT_WERDEN_SOL("Wollen Sie noch etwas reduzieren? (y/n)"),
    FRAGE_OB_BESTELLT_WERDEN_SOLL("Wollen Sie etwas bestellen? (y/n)"),
    FRAGE_NACH_GEWUENSCHTEM_PRODUKT("\nBitte geben Sie das gewünschte Produkt ein:"),
    FRAGE_NACH_GEWUENSCHTER_ANZAHL("\nGeben Sie die gewünschte Anzahl ein:"),
    LEER_WARENKORB_NACHRICHT("\nGeben Sie die gewünschte Anzahl ein:"),
    WARENKORB_STATUS("\nIhr aktueller Warenkorb:"),
    WARENKORB_STATUS_IST_LEER("\nIhr aktueller Warenkorb ist leer"),
    FORTFAHREN_MIT_BESTELLUNG("Wollen Sie noch etwas bestellen? (y/n)"),
    BESTELLUNG_ANZEIGE("Hier ist Ihre Bestellung:\n"),
    FALSCHE_LOGIN_DATEN("Email oder Passwort ist falsch"),
    VERSUCH_LIMIT_ERREICHT("Drei falsche Versuche. \nBitte wenden Sie sich an Myro."),
    LOGIN_DATEN_ABFRAGE("Geben Sie ihre Login Daten ein. \n"),
    LOGIN_ERFOLGREICH("Login erfolgreich!"),
    WARENKORB_ENTFERNT_BESTAETIGUNG("Wir haben alle aus Ihrem Warenkorb entfernt"),
    REDUZIERTE_BESTELLMENGE("Sie haben weniger in der Bestellung"),
    STATUS("Hat gearbeitet"),
    EMAIL_EXISTIERT_SCHON("Es existiert bereits ein Account mit dieser Email.\n"),
    ACCOUNT_EXISTIERT("Existiert bereits. \n Wiederholen Sie Ihre email und passwort"),
    REGISTRATION_PROMPT("Registrieren Sie sich erneut");


    private final String message;

    UserMessagesText(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }

    @Override
    public String toString() {
        return message;
    }
}
