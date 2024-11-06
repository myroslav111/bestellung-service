
Eine Online-Apotheke kauft zahlreiche Drohnen von TÜCOPTER, die sie für
Expressauslieferungen von Pharmazeutika an Kunden der näheren Umgebung einsetzen möchte.
Die Apothele arbeitet mit einem zweistufigen Preismodell; ein Teil des Preises
hängt von der Nutzlast ab, die der Kunde zum Zielort transposrtieren möchte, der andere
Teil von der Entfernung des Zielortes zum Logistikzentrum:


- Eine Nutzlast von bis zu 6kg kostet 3€.
- Höhere Nutzlasten schlagen mit 7€ zu buche.
- Jeder Kilometer Entfernung des Zielorts vom Logistikzentrum kostet pauschal 0,40€.
- Die Drohne kann nur eine Entfernung von maximal 25km anfliegen.
- Der eingesetzte Drohnentyp kann nur eine Nutzlast von maximal 12kg transportieren.

Der Ablauf des gewünschten Programms ist bereits in einem Struktogramm dargestellt.
Die vom Kunden gewünschte Entfernung und Nutzlast werden der Funktion/Methode als
Parameter übergeben.

//package: Drohne
1. Erstellen Sie eine abstrakte Klasse `Drohne` mit den Attributen `maxNutzlast` und `maxEntfernung` sowie Getter und Setter.
2. Erstellen Sie ein Interface `DroneOS` mit den Methoden `berechneStrecke(String start, String ziel)`,
   `beladen(Paket paket)`, `fliegen()`, `entladen(Paket paket)`.
3. Erstellen Sie eine Klasse `InfoCopter` die von `Drohne` erbt und das Interface `DroneOS` implementiert.
4. Ergänzen Sie das Attribut `Paket paket`, implementieren Sie die Methoden des Interfaces in der Klasse `InfoCopter`
   und erstellen Sie einen Konstruktor, der die Attribute `maxNutzlast` und `maxEntfernung` der Elternklasse setzt.


//package: Apotheke
1. Erstellen Sie eine Klasse `Apotheke`.
2. Erstellen Sie ein Interface `BestellService` mit den Methoden `addWarenkorb(String produkt, int anzahl)`,
   `removeWarenkorb(String produkt, int anzahl)`, `berechneGesamtpreis()`, `berechneStrecke()`, `bestellungAbschicken()`.
3. Implementieren Sie das Interface in der Klasse `Apotheke`.
4. Erstellen Sie eine Klasse `Warenkorb` mit dem Attribut `List<String> produkte`. und den Methoden
   `addProduct(String produkt)`, `clearProducts()`, `showWarenkorb()`.
5. Instanziieren Sie in der Klasse `Apotheke` ein Objekt der Klasse `Warenkorb`.


//package: Kunde
1. Erstellen Sie eine Klasse `Kunde` mit den Attributen `kundennummer`, `name`, `vorname`, `adresse`, `email`, `passwort`.
   einen Konstruktor sowie Getter und Setter.

//package: Paket
1. Erstellen Sie eine Klasse `Paket` mit den Attributen `paketnummer`, `gewicht`, `String zielAdresse`, `zugestellt` und einer Liste `paketinhalt`.
2. Erstellen Sei einen Konstruktor, der bis auf `zugestellt` alle Felder setzt.
3. Setzen sie `zugestellt` im Konstruktor auf `false`.
4. Ergänzen Sie die Klasse mit den Methoden `addWaren()`, `clearWaren()` sowie Getter für alle Felder.
5. Implementieren Sie einen Setter für das Feld `zugestellt` und setzen sie alle anderen Felder auf final.

//Logistikzentrum
1. Erstellen Sie eine Klasse `Logistikzentrum` mit den Attributen `List<Paket> paketeZumVersenden`.
2. Erstelle eine Klasse `Warenbestand` mit Produktnamen und der verfügbaren Menge: { {"Ibu",3} , {"Aspirin",5} ,
   {"Paracetamol",2} , {"Vitamin C",8} , {"Vitamin D",7} }.
3. Ergänzen Sie  Setter und Getter für die Attribute.
4. Schreiben Sie eine Methode `showWarenbestand()`, die den aktuellen Warenbestand ausgibt.
5. Erstellen Sie eine Instanz der Klasse `Warenbestand` in der Klasse `Logistikzentrum`.

//Main
1. Erstellen Sie eine Klasse `Main` mit der `main` Methode.
2. Erstellen Sie ein Objekt der Klasse `Apotheke` und ein Objekt der Klasse `Logistikzentrum`.
3. Erstelle eine Methode `bestellungAufgeben()`, die die Bestellung des Kunden entgegennimmt und die Bestellung an das Logistikzentrum weiterleitet.

https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Untitled%20Diagram.drawio#Uhttps%3A%2F%2Fraw.githubusercontent.com%2Fmyroslav111%2FCheatsheet1%2Fmaster%2FUntitled%2520Diagram.drawio