package datalayer_test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.json.*;
import javax.json.stream.JsonGenerator;

public class WriteJson {

    public static void main(String[] args) {

        JsonArrayBuilder model = Json.createArrayBuilder();   //array für die Verschiedenen model
        JsonArrayBuilder engines = Json.createArrayBuilder();   //array für die Verschiedenen engines
        JsonArrayBuilder transmissions = Json.createArrayBuilder();   //array für die Verschiedenen transmissions
        JsonArrayBuilder seats = Json.createArrayBuilder();   //array für die Verschiedenen seats

        JsonObjectBuilder builder = Json.createObjectBuilder(); //JSON Object, das am ende die Datei wird.




        // Anlegen der einzelnen Objekte, jedes model ist ein eigenes Objekt mit Attributen: model_id, name, price
        JsonObjectBuilder model_passat = Json.createObjectBuilder();   //Eintrag für Passat
        model_passat.add("model_id", 100);
        model_passat.add("name", "Passat");
        model_passat.add("price", 20000);
        model.add(model_passat);     // Eintrag wird an das array angehängt

        JsonObjectBuilder model_golf = Json.createObjectBuilder(); //Eintrag für Golf
        model_golf.add("model_id", 101);
        model_golf.add("name", "Golf");
        model_golf.add("price", 10000);
        model.add(model_golf);       // Eintrag wird an das array angehängt

        JsonObjectBuilder model_porsche = Json.createObjectBuilder(); //Eintrag für Porsche
        model_porsche.add("model_id", 102);
        model_porsche.add("name", "Porsche");
        model_porsche.add("price", 80000);
        model.add(model_porsche);    // Eintrag wird an das array angehängt

        JsonObjectBuilder model_mercedes = Json.createObjectBuilder(); //Eintrag für Mercedes
        model_mercedes.add("model_id", 103);
        model_mercedes.add("name", "Mercedes");
        model_mercedes.add("price", 45000);
        model.add(model_mercedes);   // Eintrag wird an das array angehängt


        // Anlegen der einzelnen Objekte, jeder Motor mit Attributen: motor_id, name, price, compatible_with
        JsonObjectBuilder motor_tdi = Json.createObjectBuilder(); //Eintrag für 2.0 TDI, soll Kompitabel mit model_ID 00 und 01 sein.
        motor_tdi.add("model_id", 110);
        motor_tdi.add("name", "2.0 TDI");
        motor_tdi.add("price", 3000);
        motor_tdi.add("compatible_with", Json.createArrayBuilder().add(100).add(101)); //hier wird in das Attribut compatible_with ein array mit inhalt 00, 01 Geschrieben.

        engines.add(motor_tdi);   // Eintrag wird an das "engines" array angehängt

        JsonObjectBuilder motor_v8 = Json.createObjectBuilder(); //Eintrag für 4.0 V8, soll Kompitabel mit model_ID 03 sein.
        motor_v8.add("model_id", 111);
        motor_v8.add("name", "4.0 V8");
        motor_v8.add("price", 8000);
        motor_v8.add("compatible_with", Json.createArrayBuilder().add(103)); //hier wird in das Attribut compatible_with ein array mit inhalt 03 Geschrieben.

        engines.add(motor_v8);   // Eintrag wird an das "engines" array angehängt

        JsonObjectBuilder motor_boxer = Json.createObjectBuilder(); //Eintrag für 3.5 Boxer, soll Kompitabel mit model_ID 02 sein.
        motor_boxer.add("model_id", 112);
        motor_boxer.add("name", "3.5 Boxer");
        motor_boxer.add("price", 7000);
        motor_boxer.add("compatible_with", Json.createArrayBuilder().add(102)); //hier wird in das Attribut compatible_with ein array mit inhalt 02 Geschrieben.

        engines.add(motor_boxer);   // Eintrag wird an das "engines" array angehängt


        //Objekte für transmissions:
        JsonObjectBuilder transmissions_auto = Json.createObjectBuilder();
        transmissions_auto.add("model_id", 120);
        transmissions_auto.add("name", "Automatik");
        transmissions_auto.add("price", 4000);
        transmissions_auto.add("compatible_with", Json.createArrayBuilder().add(100).add(102).add(103));

        transmissions.add(transmissions_auto);

        JsonObjectBuilder transmissions_schalt = Json.createObjectBuilder();
        transmissions_schalt.add("model_id", 121);
        transmissions_schalt.add("name", "Handschaltung");
        transmissions_schalt.add("price", 2000);
        transmissions_schalt.add("compatible_with", Json.createArrayBuilder().add(100).add(101).add(102).add(103));

        transmissions.add(transmissions_schalt);

        JsonObjectBuilder transmissions_doppelkupplung = Json.createObjectBuilder();
        transmissions_doppelkupplung.add("model_id", 122);
        transmissions_doppelkupplung.add("name", "Doppelkupplungsgetriebe");
        transmissions_doppelkupplung.add("price", 8000);
        transmissions_doppelkupplung.add("compatible_with", Json.createArrayBuilder().add(102).add(103));

        transmissions.add(transmissions_doppelkupplung);


        //Objekte für seats

        JsonObjectBuilder sitz_stoff = Json.createObjectBuilder();
        sitz_stoff.add("model_id", 130);
        sitz_stoff.add("name", "Stoffbezug");
        sitz_stoff.add("price", 500);
        sitz_stoff.add("compatible_with", Json.createArrayBuilder().add(100).add(101));

        seats.add(sitz_stoff);

        JsonObjectBuilder sitz_teilleder = Json.createObjectBuilder();
        sitz_teilleder.add("model_id", 131);
        sitz_teilleder.add("name", "Teilleder");
        sitz_teilleder.add("price", 1000);
        sitz_teilleder.add("compatible_with", Json.createArrayBuilder().add(100).add(101).add(102).add(103));

        seats.add(sitz_teilleder);

        JsonObjectBuilder sitz_vollleder = Json.createObjectBuilder();
        sitz_vollleder.add("model_id", 132);
        sitz_vollleder.add("name", "Vollleder");
        sitz_vollleder.add("price", 500);
        sitz_vollleder.add("compatible_with", Json.createArrayBuilder().add(102).add(103));

        seats.add(sitz_vollleder);


        //alle Arrays werden an das JSON Objekt angehängt.
        builder.add("models",model);
        builder.add("engines",engines);
        builder.add("transmissions",transmissions);
        builder.add("seats",seats);

        JsonObject jo = builder.build();      // Das JSON Objekt wird zur Datei
        //Die Datei wird unter dem Namen Testdatensatz3.json Gespeichert
        try {
            Map<String, Boolean> config = new HashMap<>();
            config.put(JsonGenerator.PRETTY_PRINTING, true);
            JsonWriterFactory writerFactory = Json.createWriterFactory(config);

            FileWriter fw = new FileWriter("Testdatensatz3.json");
            JsonWriter jsonWriter = writerFactory.createWriter(fw);
            jsonWriter.writeObject(jo);
            jsonWriter.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }







    }

}
