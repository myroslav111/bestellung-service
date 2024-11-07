package info.bestellungsservice.apothekebestellungservice;

import info.bestellungsservice.apothekebestellungservice.apotheke.Apotheke;
import info.bestellungsservice.apothekebestellungservice.apotheke.Warenkorb;
import info.bestellungsservice.apothekebestellungservice.kunde.Kunde;
import info.bestellungsservice.apothekebestellungservice.kunde.UserFileManager;
import info.bestellungsservice.apothekebestellungservice.logistikzentrum.Warenbestand;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerAnmeldeDatenAbfragen;
import info.bestellungsservice.apothekebestellungservice.utils.BenutzerUmfrage;
import info.bestellungsservice.apothekebestellungservice.utils.Nachricht;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        ApplicationRunner app = new ApplicationRunner();
        app.run();
    }
}
