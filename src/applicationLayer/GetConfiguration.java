package applicationLayer;

import datalayer_test.*;

import java.util.Iterator;
import java.util.List;

public class GetConfiguration {
    private static List<Model> modelList = ReadJson.getModels();
    private static List<Engine> engineList = ReadJson.getEngines();
    private static List<Transmission> transmissionList = ReadJson.getTransmissions();
    private static List<Seat> seatList = ReadJson.getSeats();

    public static Configuration getConfiguration(){
        //TODO: Singelton pattern
        String[] models = new String[modelList.size()];
        String[] engines = new String[engineList.size()];
        String[] transmissions = new String[transmissionList.size()];
        String[] seats = new String[seatList.size()];
        //TODO: counter austauschen: gibts da noch andere, schönere möglichkeiten?
        int counter = 0;


        Iterator iModel = modelList.iterator();
        while (iModel.hasNext()){
            Model model = (Model) iModel.next();
            models[counter] = model.getName();
            counter++;
        }
        counter = 0;
        Iterator iEngine = engineList.iterator();
        while (iEngine.hasNext()){
            Engine engine = (Engine) iEngine.next();
            engines[counter] = engine.getName();
            counter++;
        }
        counter = 0;
        Iterator iTransmission = transmissionList.iterator();
        while (iTransmission.hasNext()){
            Transmission transmission = (Transmission) iTransmission.next();
            transmissions[counter] = transmission.getName();
            counter++;
        }
        counter = 0;
        Iterator iSeat = seatList.iterator();
        while (iSeat.hasNext()){
            Seat seat = (Seat) iSeat.next();
            seats[counter] = seat.getName();
            counter++;
        }


        Configuration config = new Configuration(models, engines, transmissions, seats);
        return config;
    }


    public static SubConfiguration getSubConfiguration(String model){
        //TODO: Singelton pattern
        int modelId = 0;
        String[] engines = new String[engineList.size()];
        String[] transmissions = new String[transmissionList.size()];
        String[] seats = new String[seatList.size()];
        int counter = 0;

        for (Model m:modelList) {

            if (model.equals(m.getName())){
                modelId = m.getModel_id();
            }

        }

        if (modelId == 0){
            return null;
            //TODO: exception falseModelName
        }

        Iterator iEngine = engineList.iterator();
        while (iEngine.hasNext()){
            Engine engine = (Engine) iEngine.next();
            int[] compatible_with = engine.getCompatible_with();
            for (int i: compatible_with){
                if (i == modelId) {
                    engines[counter] = engine.getName();
                    System.out.println(engines[counter]);
                    counter++;
                }
            }
        }
        counter = 0;

        Iterator iTransmission = transmissionList.iterator();
        while (iTransmission.hasNext()){
            Transmission transmission = (Transmission) iTransmission.next();
            int[] compatible_with = transmission.getCompatible_with();
            for (int i: compatible_with){
                if (i == modelId) {
                    transmissions[counter] = transmission.getName();
                    System.out.println(transmissions[counter]);
                    counter++;
                }
            }
        }
        counter = 0;

        Iterator iSeat = seatList.iterator();
        while (iSeat.hasNext()){
            Seat seat = (Seat) iSeat.next();
            int[] compatible_with = seat.getCompatible_with();
            for (int i: compatible_with){
                if (i == modelId) {
                    seats[counter] = seat.getName();
                    System.out.println(seats[counter]);
                    counter++;
                }
            }
        }

        SubConfiguration subConfig = new SubConfiguration(engines, transmissions, seats);
        return subConfig;
    }
}
