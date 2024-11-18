package info.bestellungsservice.apothekebestellungservice.utils;

public class UserMessages {

   public static String apothekenBegruessungText(String kundeName, String vorname){
      String gelbText = "\u001B[33m";
      String resetColor = "\u001B[0m";
      return gelbText + "Willkommen in der Apotheke " + vorname + " " + kundeName + resetColor + "\n";
   }

   public static String kontoAbfrageText(){
      return "Besitzen Sie bereits ein Konto? (y/n)";
   }

   public static String bestellungsBestaetigungText(){
      return "Hier ist Ihre Bestellung:";
   }

   public static String gesamtbetragText(double gesamtbetrag){
      return "                 Insgesamt: " + gesamtbetrag + "€";
   }

   public static String lagerbestandWarnungText(){
      String redText = "\u001B[31m";
      String resetColor = "\u001B[0m";
      return redText + "Niedriger Lagerbestand." + resetColor + "\n";
   }

   public static String lagerbestandWarnungMitVerfuegbarkeitText(int produktMenge){
      String redText = "\u001B[31m";
      String resetColor = "\u001B[0m";
      return redText + "Niedriger Lagerbestand. \nVerfügbar sind" + produktMenge + "St." + resetColor + "\n";
   }

   public static String bestellBestaetigungsFrageText(){
      return "Bestätigen Sie die Bestellung? (y/n)";
   }

   public static String bestellAenderungsOptionText(){
      return "Wollen Sie ganze Bestellung abbrechen (y) \noder nur etwas korrigieren (k)";
   }

   public static String frageHinzufuegenOderReduzierenText(){
      return "Möchten Sie etwas hinzufügen (y) \noder reduzieren (r)?";
   }

   public static String reduktionsAnfrageProduktText(){
      return "Was möchten Sie reduzieren, geben Sie die Produktnummer ein:";
   }

   public static String anfrageReduzierenMengeText(String medikamentName){
      return "Wie viele " + medikamentName + " möchten Sie reduzieren?:";
   }

   public static String frageObWeiterReduziertWerdenSollText(){
      return "Wollen Sie noch etwas reduzieren? (y/n)";
   }

   public static String frageObBestelltWerdenSollText(){
      return "Wollen Sie etwas bestellen? (y/n)";
   }

   public static String frageNachGewuenschtenProduktText(){
      return "\nBitte geben Sie das gewünschte Produkt ein:";
   }

   public static String frageNachGewuenschterAnzahlText(){
      return "\nGeben Sie die gewünschte Anzahl ein:";
   }

   public static String leerWarenkorbNachrichtText(){
      return "\nGeben Sie die gewünschte Anzahl ein:";
   }

   public static String warenkorbStatusText(){
      return "\nIhr aktueller Warenkorb:";
   }

   public static String fortfahrenMitBestellungText(){
      return "Wollen Sie noch etwas bestellen? (y/n)";
   }

   public static String bestellungAnzeigeText(){
      return "Hier ist Ihre Bestellung:\n";
   }

   public static String falscheLoginDatenText(){
      String redText = "\u001B[31m";
      String resetColor = "\u001B[0m";
      return redText + "Email oder Passwort ist falsch" + resetColor + "\n";
   }

   public static String versuchLimitErreichtText(){
      String redText = "\u001B[31m";
      String resetColor = "\u001B[0m";
      return redText + "Drei falsche Versuche. \nBitte wenden Sie sich an Myro." + resetColor + "\n";
   }

   public static String loginDatenAbfrageText(){
      return "Geben Sie ihre Login Daten ein. \n";
   }

   public static String loginErfolgreichText(){
      String gelbText = "\u001B[33m";
      String resetColor = "\u001B[0m";
      return gelbText + "Login erfolgreich!" + resetColor + "\n";
   }

   public static String warenkorbEntferntBestaetigungText(){
      return "Wir haben alle aus Ihrem Warenkorb entfernt";
   }

   public static String reduzierteBestellmengeText(){
      String redText = "\u001B[31m";
      String resetColor = "\u001B[0m";
      return redText + "Sie haben weniger in der Bestellung" + resetColor + "\n";
   }
   public static String statusText(){
      return "Hat gearbeitet";
   }

   public static String emailExistiertSchonText(){
      String blauText = "\u001B[34m";
      String resetColor = "\u001B[0m";
      return blauText + "Es existiert bereits ein Account mit dieser Email.\n" + resetColor + "\n";
   }

   public static String accountExistiertText(){
      String blauText = "\u001B[34m";
      String resetColor = "\u001B[0m";
      return blauText + "Existiert bereits. \n Wiederholen Sie Ihre email und passwort" + resetColor + "\n";
   }

   public static String[] kundenDatenAbfrageText(){
       return new String[]{"Name:", "Vorname:", "Adresse:", "Email:", "Passwort:", "Ihre Kundenummer" };
   }

   public static String[] paketInfoText(){
      return new String[]{"Paketnummer: ", "Gewicht: ", "Zieladresse: " , "Status der Liferung: "};
   }
}

