package info.bestellungsservice.apothekebestellungservice;

public enum ProduktList {
    IBU("Ibu", 1),
    ASPIRIN("Aspirin", 2),
    PARACETAMOL("Paracetamol", 3),
    VITAMIN_C("Vitamin_C", 4),
    VITAMIN_D("Vitamin_D", 5);

    final String produktName;
    final int produktNummer;

    ProduktList(String produktName, int produktNummer) {
        this.produktName = produktName;
        this.produktNummer = produktNummer;
    }

    public String getProduktName() {
        return produktName;
    }

    public int getProduktNummer() {
        return produktNummer;
    }

    public static int getProduktNummerByName(String name){
        int num = -1;
        for(ProduktList produkt: ProduktList.values()){
            if (produkt.getProduktName().equals(name)){
                num = produkt.getProduktNummer();
            }
        }
        return num;
    }

}
