package info.bestellungsservice.apothekeBestellungService.kunde;



import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Kunde {
    public int kundennummer;
    public String name;
    public String vorname;
    public String adresse;
    public String email;
    public String passwort;

    public Kunde(){}

    public Kunde(int kundennummer, String name, String vorname, String adresse, String email, String passwort) {
        this.kundennummer = kundennummer;
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.email = email.toLowerCase();
        this.passwort = passwort;
    }

    public void setKunde(Scanner scanner){
            System.out.println("Geben Sie Ihre Name ");
            setName(scanner.next());
            System.out.println("Geben Sie Ihre Vornhame");
            setVorname(scanner.next());
            System.out.println("Geben Sie Ihre Adresse");
            scanner.nextLine();
            setAdresse(scanner.nextLine());

            System.out.println("Geben Sie Ihre Email");
            setEmail(scanner.next());
            System.out.println("Geben Sie Ihre Passwort");
            setPasswort(scanner.next());
            setKundennummer((int) (Math.random() * 1000));
            //System.out.println("Ihre Kundenumer" + this.kundennummer);
    }

    @Override
    public String toString(){
        return String.valueOf(kundennummer) + "," + name + "," + vorname + "," + adresse + "," + email + "," + passwort;
    }
}
