package info.bestellungsservice.apothekebestellungservice.kunde;

import info.bestellungsservice.apothekebestellungservice.enums.UserMessagesText;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserFileManager {

    private static final String FILE_PATH = "src/main/resources/kunden.txt";

    public List<Kunde> getKundenDatenAsList(){
        List<Kunde> kunden = new ArrayList<>();

        // Versucht, einen BufferedReader zu öffnen, um die Datei zu lesen
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;

            // Liest die Datei zeilenweise
            while ((line = bufferedReader.readLine()) != null){

                // Überspringe leere Zeilen
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] userData = line.split(",");
                int kundennummer = Integer.parseInt(userData[0]);
                String name = userData[1];
                String vorname = userData[2];
                String adresse = userData[3];
                String email = userData[4];
                String passwort = userData[5];

                // Erstellt ein neues Kunde-Objekt mit den gelesenen Daten
                Kunde kunde = new Kunde(kundennummer, name, vorname, adresse, email, passwort);

                // Fügt das Kunde-Objekt zur Liste hinzu
                kunden.add(kunde);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return kunden;
    }

    public boolean isKunde(String userEmail, String userPsw){
        //System.out.println(userEmail + userPsw);
        return getKundenDatenAsList().stream().anyMatch(user -> user.getEmail().equals(userEmail) && user.getPasswort().equals(userPsw));
    }

    public void addKunde(Kunde kunde) {

        // Überprüft, ob der Kunde bereits existiert, basierend auf dem Passwort
        //System.out.println(!isKunde(kunde.getPasswort(), kunde.getPasswort()));
        if (!isKunde(kunde.getPasswort(), kunde.getPasswort())) {
            System.out.println();
            // Wenn der Kunde nicht existiert, wird ein BufferedWriter erstellt, um in die Datei zu schreiben
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                // Liest die bestehenden Benutzer und schreibt sie in die Datei
                bufferedWriter.write(kunde.toString());
                bufferedWriter.newLine();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println(UserMessagesText.EMAIL_EXISTIERT_SCHON);
        }
    }

    public String getKundenName(String email){
        String kundeName = "";
        for(Kunde curKunde: getKundenDatenAsList()){
            if (curKunde.getEmail().equals(email)) {
                kundeName = curKunde.getName();
            }
        }
        return kundeName;
    }

    public String getKundenVorname(String email){
        String kundeVorname = "";
        for(Kunde curKunde: getKundenDatenAsList()){
            if (curKunde.getEmail().equals(email)) {
                kundeVorname = curKunde.getVorname();
            }
        }
        return kundeVorname;
    }

    public boolean checkEmailVorhanden(String email){
        boolean isEmail = false;
        for(Kunde curKunde: getKundenDatenAsList()){
            if (curKunde.getEmail().equals(email)) {
                isEmail = true;
                break;
            }
        }
        return isEmail;
    }
}
