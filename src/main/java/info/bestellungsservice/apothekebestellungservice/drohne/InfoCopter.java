package info.bestellungsservice.apothekebestellungservice.drohne;

import info.bestellungsservice.apothekebestellungservice.paket.Paket;


public class InfoCopter extends Drohne implements DroneOS {
    public Paket paket;

    public InfoCopter(){
        super();
    }


    @Override
    public void berechneStrecke(String start, String ziel) {

    }

    @Override
    public void beladen(Object paket) {

    }

    @Override
    public void fliege() {

    }

    @Override
    public void entladen(Object paket) {

    }
}
