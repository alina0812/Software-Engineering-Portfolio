package datalayer_test;

import java.io.FileWriter;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class WriteJson {

    public static void main(String[] args) {

        JsonArrayBuilder modelle = Json.createArrayBuilder();   //array für die Verschiedenen Modelle
        JsonArrayBuilder motoren = Json.createArrayBuilder();   //array für die Verschiedenen Motoren
        JsonArrayBuilder getriebe = Json.createArrayBuilder();   //array für die Verschiedenen Getriebe
        JsonArrayBuilder sitze = Json.createArrayBuilder();   //array für die Verschiedenen Sitze

        JsonObjectBuilder builder = Json.createObjectBuilder(); //JSON Object, das am ende die Datei wird.




        // Anlegen der einzelnen Objekte, jedes Modell ist ein eigenes Objekt mit Attributen: modell_id, name, price
        JsonObjectBuilder modell_passat = Json.createObjectBuilder();   //Eintrag für Passat
        modell_passat.add("modell_id", 100);
        modell_passat.add("name", "Passat");
        modell_passat.add("price", 20000);
        modelle.add(modell_passat);     // Eintrag wird an das array angehängt

        JsonObjectBuilder modell_golf = Json.createObjectBuilder(); //Eintrag für Golf
        modell_golf.add("modell_id", 101);
        modell_golf.add("name", "Golf");
        modell_golf.add("price", 10000);
        modelle.add(modell_golf);       // Eintrag wird an das array angehängt

        JsonObjectBuilder modell_porsche = Json.createObjectBuilder(); //Eintrag für Porsche
        modell_porsche.add("modell_id", 102);
        modell_porsche.add("name", "Porsche");
        modell_porsche.add("price", 80000);
        modelle.add(modell_porsche);    // Eintrag wird an das array angehängt

        JsonObjectBuilder modell_mercedes = Json.createObjectBuilder(); //Eintrag für Mercedes
        modell_mercedes.add("modell_id", 103);
        modell_mercedes.add("name", "Mercedes");
        modell_mercedes.add("price", 45000);
        modelle.add(modell_mercedes);   // Eintrag wird an das array angehängt

        
        // Anlegen der einzelnen Objekte, jeder Motor mit Attributen: motor_id, name, price, kompitabel_mit
        JsonObjectBuilder motor_tdi = Json.createObjectBuilder(); //Eintrag für 2.0 TDI, soll Kompitabel mit Modell_ID 00 und 01 sein.
        motor_tdi.add("modell_id", 110);
        motor_tdi.add("name", "2.0 TDI");
        motor_tdi.add("price", 3000);
        motor_tdi.add("kompitabel_mit", Json.createArrayBuilder().add(100).add(101)); //hier wird in das Attribut kompitabel_mit ein array mit inhalt 00, 01 Geschrieben.

        motoren.add(motor_tdi);   // Eintrag wird an das "motoren" array angehängt

        JsonObjectBuilder motor_v8 = Json.createObjectBuilder(); //Eintrag für 4.0 V8, soll Kompitabel mit Modell_ID 03 sein.
        motor_v8.add("modell_id", 111);
        motor_v8.add("name", "4.0 V8");
        motor_v8.add("price", 8000);
        motor_v8.add("kompitabel_mit", Json.createArrayBuilder().add(103)); //hier wird in das Attribut kompitabel_mit ein array mit inhalt 03 Geschrieben.

        motoren.add(motor_v8);   // Eintrag wird an das "motoren" array angehängt

        JsonObjectBuilder motor_boxer = Json.createObjectBuilder(); //Eintrag für 3.5 Boxer, soll Kompitabel mit Modell_ID 02 sein.
        motor_boxer.add("modell_id", 112);
        motor_boxer.add("name", "3.5 Boxer");
        motor_boxer.add("price", 7000);
        motor_boxer.add("kompitabel_mit", Json.createArrayBuilder().add(102)); //hier wird in das Attribut kompitabel_mit ein array mit inhalt 02 Geschrieben.

        motoren.add(motor_boxer);   // Eintrag wird an das "motoren" array angehängt


        //Objekte für Getriebe:
        JsonObjectBuilder getriebe_auto = Json.createObjectBuilder();
        getriebe_auto.add("modell_id", 120);
        getriebe_auto.add("name", "Automatik");
        getriebe_auto.add("price", 4000);
        getriebe_auto.add("kompitabel_mit", Json.createArrayBuilder().add(100).add(102).add(103));

        getriebe.add(getriebe_auto);

        JsonObjectBuilder getriebe_schalt = Json.createObjectBuilder();
        getriebe_schalt.add("modell_id", 121);
        getriebe_schalt.add("name", "Handschaltung");
        getriebe_schalt.add("price", 2000);
        getriebe_schalt.add("kompitabel_mit", Json.createArrayBuilder().add(100).add(101).add(102).add(103));

        getriebe.add(getriebe_schalt);

        JsonObjectBuilder getriebe_doppelkupplung = Json.createObjectBuilder();
        getriebe_doppelkupplung.add("modell_id", 122);
        getriebe_doppelkupplung.add("name", "Doppelkupplungsgetriebe");
        getriebe_doppelkupplung.add("price", 8000);
        getriebe_doppelkupplung.add("kompitabel_mit", Json.createArrayBuilder().add(102).add(103));

        getriebe.add(getriebe_doppelkupplung);


        //Objekte für Sitze

        JsonObjectBuilder sitz_stoff = Json.createObjectBuilder();
        sitz_stoff.add("modell_id", 130);
        sitz_stoff.add("name", "Stoffbezug");
        sitz_stoff.add("price", 500);
        sitz_stoff.add("kompitabel_mit", Json.createArrayBuilder().add(100).add(101));

        sitze.add(sitz_stoff);

        JsonObjectBuilder sitz_teilleder = Json.createObjectBuilder();
        sitz_teilleder.add("modell_id", 131);
        sitz_teilleder.add("name", "Teilleder");
        sitz_teilleder.add("price", 1000);
        sitz_teilleder.add("kompitabel_mit", Json.createArrayBuilder().add(100).add(101).add(102).add(103));

        sitze.add(sitz_teilleder);

        JsonObjectBuilder sitz_vollleder = Json.createObjectBuilder();
        sitz_vollleder.add("modell_id", 132);
        sitz_vollleder.add("name", "Vollleder");
        sitz_vollleder.add("price", 500);
        sitz_vollleder.add("kompitabel_mit", Json.createArrayBuilder().add(102).add(103));

        sitze.add(sitz_vollleder);


        //alle Arrays werden an das JSON Objekt angehängt.
        builder.add("Modelle",modelle);
        builder.add("Motoren",motoren);
        builder.add("Getriebe",getriebe);
        builder.add("Sitze",sitze);

        JsonObject jo = builder.build();      // Das JSON Objekt wird zur Datei
        //Die Datei wird unter dem Namen Testdatensatz.txt Gespeichert
        try {
            FileWriter fw = new FileWriter("Testdatensatz3.txt");
            JsonWriter jsonWriter = Json.createWriter(fw);
            jsonWriter.writeObject(jo);
            jsonWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }





    }

}
