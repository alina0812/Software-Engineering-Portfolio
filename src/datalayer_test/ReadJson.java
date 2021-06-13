package datalayer_test;

import jdk.jshell.spi.ExecutionControl;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ReadJson {
    private static JsonObject txtObject;

    private static List<Model> models = new ArrayList<>();
    private static List<Engine> engines = new ArrayList<>();
    private static List<Transmission> transmissions = new ArrayList<>();
    private static List<Seat> seats = new ArrayList<>();


    private ReadJson(){
        // no instance of ReadJson should ever be created
    }


    public static void load_data(){
        File jsonInputFile = new File("Data.json");
        InputStream is;

        try{
            //read File into Object txtObject
            is = new FileInputStream(jsonInputFile);
            JsonReader reader = Json.createReader(is);
            txtObject = reader.readObject();
            reader.close();
            
        }catch (
                FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static List<Model> getModels(){
        JsonArray models_json = txtObject.getJsonArray("models");
        for (int i = 0; i < models_json.size(); i++) {
            Model model = new Model();
            JsonObject json_object = (JsonObject) models_json.get(i);
            model.setModel_id(json_object.getInt("model_id"));
            model.setName(json_object.getString("name"));
            model.setPrice(json_object.getInt("price"));
            models.add(model);

        }
        return models;
    }

    public static List<Engine> getEngines(){
        JsonArray engines_json = txtObject.getJsonArray("engines");
        for (int i = 0; i < engines_json.size(); i++) {
            Engine engine = new Engine();
            JsonObject json_object = (JsonObject) engines_json.get(i);
            engine.setModel_id(json_object.getInt("model_id"));
            engine.setName(json_object.getString("name"));
            engine.setPrice(json_object.getInt("price"));
            engine.setCompatible_with(json_object.getJsonArray("compatible_with"));
            engines.add(engine);

        }
        return engines;
    }

    public static List<Transmission> getTransmissions(){
        JsonArray transmissions_json = txtObject.getJsonArray("transmissions");
        for (int i = 0; i < transmissions_json.size(); i++) {
            Transmission transmission = new Transmission();
            JsonObject json_object = (JsonObject) transmissions_json.get(i);
            transmission.setModel_id(json_object.getInt("model_id"));
            transmission.setName(json_object.getString("name"));
            transmission.setPrice(json_object.getInt("price"));
            transmission.setCompatible_with(json_object.getJsonArray("compatible_with"));
            transmissions.add(transmission);

        }
        return transmissions;
    }

    public static List<Seat> getSeats(){

        JsonArray seats_json = txtObject.getJsonArray("seats");
        for (int i = 0; i < seats_json.size(); i++) {
            Seat seat = new Seat();
            JsonObject json_object = (JsonObject) seats_json.get(i);
            seat.setModel_id(json_object.getInt("model_id"));
            seat.setName(json_object.getString("name"));
            seat.setPrice(json_object.getInt("price"));
            seat.setCompatible_with(json_object.getJsonArray("compatible_with"));
            seats.add(seat);

        }
        return seats;
    }

}
