package applicationLayer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ReadJson {


    public static void main(String[] args) {



        File jsonInputFile = new File("\\Users\\Luis\\Downloads\\Software-Engineering-Portfolio\\Testdatensatz3.json"); // change Path!
        InputStream is;

        try{
            //read File into Object txtObject
            is = new FileInputStream(jsonInputFile);
            JsonReader reader = Json.createReader(is);
            JsonObject txtObject = reader.readObject();
            reader.close();


            //Divide the Object into Arrays
            JsonArray Modelle = txtObject.getJsonArray("Modelle");
            JsonArray Motoren = txtObject.getJsonArray("Motoren");
            JsonArray Getriebe = txtObject.getJsonArray("Getriebe");
            JsonArray Sitze = txtObject.getJsonArray("Sitze");



            //Test for Arrays
            System.out.println("---------------Modelle--------------------");
            for (int i = 0; i < txtObject.getJsonArray("Modelle").size(); i++){

                System.out.println(Modelle.get(i));
            }

            System.out.println("---------------Motoren--------------------");
            for (int i = 0; i < txtObject.getJsonArray("Motoren").size(); i++){
                System.out.println(Motoren.get(i));
            }

            System.out.println("---------------Getriebe--------------------");
            for (int i = 0; i < txtObject.getJsonArray("Getriebe").size(); i++){
                System.out.println(Getriebe.get(i));
            }

            System.out.println("---------------Sitze--------------------");
            for (int i = 0; i < txtObject.getJsonArray("Sitze").size(); i++){

                System.out.println(Sitze.get(i));
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }



    }

}
