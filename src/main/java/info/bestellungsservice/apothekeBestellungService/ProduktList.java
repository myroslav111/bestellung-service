package info.bestellungsservice.apothekeBestellungService;


public enum ProduktList {
    IBU("Ibu"),
    ASPIRIN("Aspirin"),
    PARACETAMOL("Paracetamol"),
    VITAMIN_C("Vitamin C"),
    VITAMIN_D("Vitamin D");


    final String medikamenteName;

    ProduktList(String medikamenteName) {
        this.medikamenteName = medikamenteName;
    }

    public String getMedikamenteName() {
        return medikamenteName;
    }
}
