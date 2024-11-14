package info.bestellungsservice.apothekebestellungservice.kunde;



import info.bestellungsservice.apothekebestellungservice.utils.UserMessages;
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
            System.out.println(UserMessages.kundenDatenAbfrageText()[0]);
            setName(scanner.next());
            System.out.println(UserMessages.kundenDatenAbfrageText()[1]);
            setVorname(scanner.next());
            System.out.println(UserMessages.kundenDatenAbfrageText()[2]);
            scanner.nextLine();
            setAdresse(scanner.nextLine());
            if (getEmail() == null) {
                System.out.println(UserMessages.kundenDatenAbfrageText()[3]);
                setEmail(scanner.next());
            }
            System.out.println(UserMessages.kundenDatenAbfrageText()[4]);
            setPasswort(scanner.next());
            setKundennummer((int) (Math.random() * 1000));
            //System.out.println(UserMessages.kundenDatenAbfrageText()[5] + this.kundennummer);

    }

    @Override
    public String toString(){
        return String.valueOf(kundennummer) + "," + name + "," + vorname + "," + adresse + "," + email + "," + passwort;
    }

}
