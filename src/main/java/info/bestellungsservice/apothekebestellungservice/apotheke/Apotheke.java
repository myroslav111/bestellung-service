package info.bestellungsservice.apothekebestellungservice.apotheke;

import info.bestellungsservice.apothekebestellungservice.ProduktList;
import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Logistikzentrum;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.paket.Paket;
import info.bestellungsservice.apothekebestellungservice.paket.PaketBuilder;
import info.bestellungsservice.apothekebestellungservice.paket.PaketBuilderInterface;
import info.bestellungsservice.apothekebestellungservice.utils.AnzeigenBeleg;
import info.bestellungsservice.apothekebestellungservice.utils.AbfrageAnmeldedaten;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerFragen;
import info.bestellungsservice.apothekebestellungservice.utils.Nachricht;

import java.util.Map;
import java.util.Scanner;

public class Apotheke implements BestellService {
    public Warenkorb warenkorbZumVersenden = new Warenkorb();


    @Override
    public void addWarenkorb(String produkt, int anzahl) {
    }

    @Override
    public void removeWarenkorb(String produkt, int anzahl) {

    }

    // produktList, die die Produkte als Schlüssel
    // (Strings) und die zu bestellenden Mengen als Werte (Integers) enthält.
    // warenbestand die Informationen über die verfügbaren Produkte und deren Preise enthält
    // der Klasse AnzeigenBeleg, die für die Anzeige des Bestellbelegs zuständig ist.
    @Override
    public void berechneGesamtpreis(Map<String, Integer> produktList,
                                    Warenbestand warenbestand) {
            double betragBestellen = 0;
            //Iterieren über die Produktliste, um den Gesamtpreis zu berechnen.
            for(Map.Entry<String, Integer> entry: produktList.entrySet()){
                betragBestellen += warenbestand.produkte.get(entry.getKey()).getPreis() * entry.getValue();
                // Aktualisierung des Warenbestands
                warenbestand.deleteProdukte(entry.getKey(), entry.getValue(), warenbestand.produkte);
                warenkorbZumVersenden.addProdukte(entry.getKey(), entry.getValue());
            }

        System.out.println("Hier ist Ihre Bestellung:");

            //Anzeige des Belegs
            AnzeigenBeleg.anzeigenBeleg(produktList, warenbestand);
            System.out.println("                 Insgesamt: " + Math.round(betragBestellen * 100.0) / 100.0 + "€");

    }

    @Override
    public void berechneStrecke() {

    }

    @Override
    public void bestellungAbschicken() {

    }

    public String sucheMedikamentNachEingabe(Scanner scanner){
        String nameMedikament = "";
        // Konsumiere den Zeilenumbruch
        scanner.nextLine();
        //entscheidet auf Grundlage von scanner.hasNextInt(), welche Methode aufgerufen wird:
        Object inputUser = scanner.hasNextInt() ? scanner.nextInt() : scanner.nextLine();

        for(ProduktList produkt: ProduktList.values()){
            // Diese Bedingung überprüft, ob inputUser eine Instanz der Klasse Number ist.
            // Da inputUser ein Object sein kann (entweder ein Integer oder String),
            // stellt dies sicher, dass die Eingabe tatsächlich eine Zahl ist und nicht z.B. ein String.
            // Falls inputUser eine Zahl ist, wird es in Number umgewandelt
            //intValue() ist eine Methode der Klasse Number, die den Number-Wert in einen int konvertiert.
            //Prüft, ob inputUser eine Zahl ist.
            //Konvertiert inputUser (falls es eine Zahl ist) in einen int.
            //Vergleicht den Wert dieser Zahl mit der Medikamentennummer des Produkts.

            // Überprüfe, ob inputUser eine Zahl ist
            if(inputUser instanceof Number && ((Number)inputUser).intValue() == produkt.getProduktNummer()){
                // Setze den Medikamentennamen, wenn die Nummer übereinstimmt
                nameMedikament = produkt.getProduktName();
            }else if (produkt.getProduktName().trim().equalsIgnoreCase(inputUser.toString().trim())){

                // Setze den Medikamentennamen, wenn der Name übereinstimmt
                nameMedikament = produkt.getProduktName();
            }else {
                System.out.println("°°°°°°°°°°°°°°°");
            }
        }
        return nameMedikament;
    }

    public void pruefeUndAktualisiereMedikamentImWarenkorb(String nameMedikament, int menge, Warenbestand warenbestand, Warenkorb warenkorb){

        // Wenn der Benutzer bereits ein Medikament hinzugefügt hat,
        // ermöglicht dies eine weitere Bearbeitung des vorhandenen Medikaments
        if (warenkorb.produktList.containsKey(nameMedikament)) {

            if (warenbestand.produkte.get(nameMedikament).getMenge() - menge - warenkorb.produktList.get(nameMedikament) >= 0) {
                warenkorb.addProdukte(nameMedikament, warenkorb.produktList.get(nameMedikament) + menge);
            }else {
                System.out.println("Niedriger Lagerbestand.");
            }
        }else {
            if (!(warenbestand.produkte.get(nameMedikament).getMenge() - menge < 0)) {
                warenkorb.addProdukte(nameMedikament, menge);
            }else {
                System.out.println("Niedriger Lagerbestand. \nVerfügbar sind" + warenbestand.produkte.get(nameMedikament).getMenge() + "St." );
            }

        }
    }

    public void bestellvorgangAbschliessen(Scanner scanner,Warenbestand warenbestand, Warenkorb warenkorb){
        // Bestellbestätigung
        // Der Benutzer wird gefragt, ob er die Bestellung bestätigen möchte.
        // Wenn der Benutzer zustimmt, wird die Methode berechneGesamtpreis aufgerufen,
        // um den Gesamtpreis der Bestellung zu berechnen und den Beleg anzuzeigen.

        if (BenutzerFragen.frageJaNein(scanner, "Bestätigen Sie die Bestellung? (y/n)")) {
            berechneGesamtpreis(warenkorb.produktList, warenbestand);
        }else if(BenutzerFragen.frageJaNein(scanner, "Wollen Sie ganze Bestellung abbrechen (y) \noder nur etwas korrigieren (k)")) {
            // Wenn der Benutzer die gesamte Bestellung abbrechen möchte,
            // wird der Warenkorb geleert und der aktuelle (leere) Warenkorb angezeigt.
            warenkorb.clearProdukt();
            warenkorb.showWarenkorb();
        }else{
            if (BenutzerFragen.frageJaNein(scanner, "Möchten Sie etwas hinzufügen (y) \noder reduzieren (r)?")) {
                bestellungAufgeben(warenbestand, warenkorb);
            }
            do {
                // Zeigt den aktuellen Inhalt des Warenkorbs an.
                warenkorb.showWarenkorb();
                System.out.println("Was möchten Sie reduzieren, geben Sie die Produktnummer ein:");

                String medikamentName = sucheMedikamentNachEingabe(scanner);

                System.out.println("Wie viele " + medikamentName + " möchten Sie reduzieren?:");
                int menge = scanner.nextInt();

                // Aktualisiert die Menge des ausgewählten Produkts im Warenkorb.
                warenkorb.aktualisiereBestandNachBestellung(medikamentName, menge);


                // Fragt den Benutzer, ob er weitere Produkte reduzieren möchte.
            }while (BenutzerFragen.frageJaNein(scanner, "Wollen Sie noch etwas reduzieren? (y/n)"));

            berechneGesamtpreis(warenkorb.produktList, warenbestand);

        }
    }

    // warenbestand die Informationen über die verfügbaren Produkte enthält.
    // warenkorb die Produkte, die der Benutzer bestellt hat
    public void bestellungAufgeben(Warenbestand warenbestand, Warenkorb warenkorb) {
        Scanner scanner = new Scanner(System.in);
        // Die Methode fragt den Benutzer, ob er etwas bestellen möchte
        if (BenutzerFragen.frageJaNein(scanner, "Wollen Sie etwas bestellen? (y/n)")) {
            System.out.println();

            // ermöglicht, mehrere Produkte zu bestellen.
            do{
                // Jedes Produkt wird durch einen Aufruf angezeigt
                warenbestand.showWarenBestand();
                System.out.println("\nBitte geben Sie das gewünschte Produkt ein:");
                String nameMedikament = sucheMedikamentNachEingabe(scanner);
                System.out.println("\nGeben Sie die gewünschte Anzahl ein:");
                int menge = scanner.nextInt();

                pruefeUndAktualisiereMedikamentImWarenkorb(nameMedikament, menge, warenbestand, warenkorb);

                if (warenkorb.produktList.isEmpty()) {
                    System.out.println("\nIhr Warenkorb ist aktuell leer.");
                }else{
                    System.out.println("\nIhr aktueller Warenkorb:");
                    System.out.println();
                    warenkorb.showWarenkorb();
                }

            }
            while (BenutzerFragen.frageJaNein(scanner, "Wollen Sie noch etwas bestellen? (y/n)"));

            System.out.println("Hier ist Ihre Bestellung:\n");
            warenkorb.showWarenkorb();
            bestellvorgangAbschliessen(scanner, warenbestand, warenkorb);

        }

    }

    public boolean login(String email, String psw) {
        // Erstellt eine Instanz von UserFileManager, um auf die Benutzerdaten zuzugreifen
        UserFileManager userFileManager = new UserFileManager();

        if (userFileManager.isKunde(email, psw)) {
            return true;
        }else {
            System.out.println("Email oder Passwort ist falsch");
            return false;
        }
    }

    public boolean benutzerAnmeldungProzess(Scanner scanner,Apotheke apotheke, UserFileManager userFileManager){
        int counter = 0;
        boolean kontoAnmeldungErfolgreich = false;
        do {
            counter += 1;
            // Benutzer wird nach E-Mail und Passwort gefragt
            System.out.println("Geben Sie ihre Login Daten ein. \n");
            String email = AbfrageAnmeldedaten.userInputEmail(scanner);
            String psw = AbfrageAnmeldedaten.userInputPasswort(scanner);

            // Überprüft die Anmeldedaten
            if (apotheke.login(email, psw)) {
                // Sucht nach dem Namen des Benutzers basierend auf der E-Mail
                System.out.println("Login erfolgreich!");
                Nachricht.begruessung(userFileManager.getKundenName(email));
                kontoAnmeldungErfolgreich = true;
                for (Kunde kunde: userFileManager.getKundenDatenAsList()){
                    if (kunde.getEmail().equalsIgnoreCase(email)) {
                        warenkorbZumVersenden.setKundenummerCurrentWarenkorb(kunde.getKundennummer());
                    }
                }
            }

            if (counter >= 3) {
                break;
            }
        }while (!kontoAnmeldungErfolgreich);

        return kontoAnmeldungErfolgreich;
    }

    public void createUndSendPaketAusWarenkorb(Warenbestand warenbestand, Warenkorb warenkorbZumVersenden,
                                               UserFileManager userFileManager) {
        Logistikzentrum logistikzentrum = Logistikzentrum.getInstance();

        PaketBuilderInterface paketBuilder = new PaketBuilder()
                .paketNummer((int)(Math.random() * 1000))
                .gewicht(warenkorbZumVersenden.getGewichtWarenkorb(warenbestand, warenkorbZumVersenden))
                .zielAdresse(findeZielAdresse(userFileManager, warenkorbZumVersenden));


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
