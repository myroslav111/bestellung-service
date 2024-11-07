# Apotheken Drohnen Lieferung

Eine Online-Apotheke verkauft ihr sortiment und lässt es per Drohnen an Kunden der näheren Umgebung liefern.
Die Apotheke arbeitet mit einem zweistufigen Preismodell: 
ein Teil des Preises hängt von der Nutzlast ab, die der Kunde zum Zielort transportieren möchte, der andere
Teil von der Entfernung des Zielortes zum Logistikzentrum:

- Eine Nutzlast von bis zu 6kg kostet 3€.
- Höhere Nutzlasten schlagen mit 7€ zu buche.
- Jeder Kilometer Entfernung des Zielorts vom Logistikzentrum kostet pauschal 0,40€.
- Die Drohne kann nur eine Entfernung von maximal 25km anfliegen.
- Der eingesetzte Drohnentyp kann nur eine Nutzlast von maximal 12kg transportieren.

<br>

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
3. Erstellen Sie eine Klasse `Warenkorb` mit dem Attribut `List<String> produkte`. und den Methoden
   `addProduct(String produkt)`, `clearProducts()`, `showWarenkorb()`.
4. Implementieren Sie das Interface in der Klasse `Warenkorb`.

6. Ergänze die Methode berechnePreisProdukte(List<String> produkte, Warenbestand warenbestand), die den Gesamtpreis der Produkte zurückgibt
im BestellService Interface und implementieren Sie diese in der Klasse `Warenkorb`.
7. Ergänzen Sie die Methode `bestellungAufgeben(Warenbestand warenbestand, Warenkorb warenkorb)`, die dem Kunden den aktuellen Warenbestand anzeigt,
auffordert die gewünschten Produkte und deren Anzahl einzugeben sowie die ausgewählten Produkte in den Warenkorb legt.



//package: Kunde
1. Erstellen Sie eine Klasse `Kunde` mit den Attributen `kundennummer`, `name`, `vorname`, `adresse`, `email`, `passwort`.
einen Konstruktor sowie Getter und Setter.



//package: Paket
1. Erstellen Sie eine Klasse `Paket` mit den Attributen `paketnummer`, `gewicht`, `String zielAdresse`, `zugestellt` und einer Liste `paketinhalt`.
2. Erstellen Sei einen Konstruktor, der bis auf `zugestellt` alle Felder setzt.
3. Setzen sie `zugestellt` im Konstruktor auf `false`.
4. Ergänzen Sie die Klasse mit den Methoden `addWaren()`, `clearWaren()` sowie Getter für alle Felder.
5. Implementieren Sie einen Setter für das Feld `zugestellt` und setzen sie alle anderen Felder auf final.



//package: Logistikzentrum
1. Erstellen Sie eine Klasse `Logistikzentrum` mit den Attributen `List<Paket> paketeZumVersenden`.
2. Erstelle eine Klasse `Warenbestand` mit Produktnamen und der verfügbaren Menge: { {"Ibu",3} , {"Aspirin",5} , 
{"Paracetamol",2} , {"Vitamin C",8} , {"Vitamin D",7} }.
3. Ergänzen Sie  Setter und Getter für die Attribute.
4. Schreiben Sie eine Methode `showWarenbestand()`, die den aktuellen Warenbestand ausgibt.
5. Erstellen Sie eine Instanz der Klasse `Warenbestand` in der Klasse `Logistikzentrum`.
6. Ergänzen Sie die Felder ProduktMengen der Klasse Warenbestand mit den zugehörigen Mengen und Gewichten.
z.B. ibuprofen_Preis = 5.99, ibuprofen_Gewicht = 0.2.



//Main
1. Erstellen Sie eine Klasse `Main` mit der `main` Methode.
2. Erstellen Sie ein Objekt der Klasse `Apotheke`, `Logistikzentrum`, `Warenkorb` und `Warenbestand` in der main Methode.
3. Lassen Sie den Kunden über die Methode bestellungAufgeben(warenbestand, warenkorb) tätigen.
4. Lassen Sie den Gesamtpreis der Bestellung berechnen und ausgeben.


//Login
1. Erstellen Sie eine Instanz der Klasse `Kunde` in der Klasse `Main` und wählen Sie geeignete Parameter wie `Name`, `EmailAdresse`, etc.
2. Erstellen Sie eine Methode `login(Kunde kunde)` in der Klasse Apotheke, die den Kunden nach seiner Email und dem Passwort fragt 
und dieses mit den Feldern des Objektes `kunde` vergleicht. Wenn die Felder übereinstimmen, soll die Methode ein true zurückgeben.
3. Implementieren Sie die Methode in der Klasse `Main` und lassen Sie den Kunden nur Bestellungen aufgeben, wenn er sich erfolgreich eingeloggt hat.


Erweiterung:
1. Implementieren Sie eine Funktion, die die verfügbarkeit der Produkte überprüft und den Bestand erst temporär 
im Warenkorb während des Bestellvorgangs aktualisiert sowie den Produktbestand nach Abschließen der Bestellung 
endgültig reduziert.

2. Ermöglichen Sie dem User vor Abgabe der Bestellung nochmals den Warenkorb einzusehen und gegebenenfalls zu bearbeiten.
