package info.bestellungsservice.apothekeBestellungService;



public enum ProduktList {
    IBU("Ibu", 1),
    ASPIRIN("Aspirin", 2),
    PARACETAMOL("Paracetamol", 3),
    VITAMIN_C("Vitamin C", 4),
    VITAMIN_D("Vitamin D", 5);


    final String medikamenteName;
    final int medikamenteNummer;


    ProduktList(String medikamenteName, int medikamenteNummer) {
        this.medikamenteName = medikamenteName;
        this.medikamenteNummer = medikamenteNummer;
    }

    public String getMedikamenteName() {

        return medikamenteName;
    }

    public int getMedikamenteNummer() {
        return medikamenteNummer;
    }

    public static int showProduktnummerByBedinung(String name){
        int num = -1;
        for(ProduktList produkt: ProduktList.values()){
            if (produkt.getMedikamenteName().equals(name)){
                num = produkt.getMedikamenteNummer();
            }
        }
        return num;
    }

    public static void showMedikamenteName() {
        for(ProduktList produkt: ProduktList.values()){
            System.out.println(produkt.getMedikamenteName() + "-->" + "Bestellungsnummer______" + produkt.getMedikamenteNummer() );
        }
    }
}
