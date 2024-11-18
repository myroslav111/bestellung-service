package info.bestellungsservice.apothekebestellungservice.enums;

public enum Farbcodes {
    ROT("\u001B[31m"),
    GRUEN("\u001B[32m"),
    GELB( "\u001B[33m"),
    BLAU( "\u001B[34m"),
    LILA( "\u001B[35m"),
    HELLBLAU( "\u001B[36m"),
    WEISS("\u001B[37m");

    private final String code; // ANSI-Code der Farbe

    Farbcodes(String code) {
        this.code = code;
    }

    public String formatText(String text) {
        return code + text + "\u001B[0m";
    }
}
