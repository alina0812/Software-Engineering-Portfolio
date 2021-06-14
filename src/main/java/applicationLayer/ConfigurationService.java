package applicationLayer;

import applicationLayer.model.Configuration;
import applicationLayer.model.SubConfiguration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dataAccessLayer.model.ConfigurationDTO;
import dataAccessLayer.model.Engine;
import dataAccessLayer.model.Model;
import dataAccessLayer.ReadJson;
import dataAccessLayer.model.Seat;
import dataAccessLayer.model.Transmission;

public class ConfigurationService {


  public static Configuration getConfiguration() {
    ReadJson.load_data();
    ConfigurationDTO configurationDTO = ReadJson.getConfigurationDTO();
    List<Model> modelList = configurationDTO.getModels();
    List<Engine> engineList = configurationDTO.getEngines();
    List<Transmission> transmissionList = configurationDTO.getTransmissions();
    List<Seat> seatList = configurationDTO.getSeats();
    //TODO: Singelton pattern
    String[] models = new String[modelList.size()];
    String[] engines = new String[engineList.size()];
    String[] transmissions = new String[transmissionList.size()];
    String[] seats = new String[seatList.size()];
    //TODO: counter austauschen: gibts da noch andere, schönere möglichkeiten?
    int counter = 0;


    Iterator iModel = modelList.iterator();
    while (iModel.hasNext()) {
      Model model = (Model) iModel.next();
      models[counter] = model.getName();
      counter++;
    }
    counter = 0;
    Iterator iEngine = engineList.iterator();
    while (iEngine.hasNext()) {
      Engine engine = (Engine) iEngine.next();
      engines[counter] = engine.getName();
      counter++;
    }
    counter = 0;
    Iterator iTransmission = transmissionList.iterator();
    while (iTransmission.hasNext()) {
      Transmission transmission = (Transmission) iTransmission.next();
      transmissions[counter] = transmission.getName();
      counter++;
    }
    counter = 0;
    Iterator iSeat = seatList.iterator();
    while (iSeat.hasNext()) {
      Seat seat = (Seat) iSeat.next();
      seats[counter] = seat.getName();
      counter++;
    }


    Configuration config = new Configuration(models, engines, transmissions, seats);
    return config;
  }


  public static SubConfiguration getConfiguration(String model) {
    //TODO: Singelton pattern
    ConfigurationDTO configurationDTO = ReadJson.getConfigurationDTO();
    List<Model> modelList = configurationDTO.getModels();
    List<Engine> engineList = configurationDTO.getEngines();
    List<Transmission> transmissionList = configurationDTO.getTransmissions();
    List<Seat> seatList = configurationDTO.getSeats();
    List<String> compatible = new ArrayList<String>();


    if (model == null || model.equals("")) {
      Configuration config = ConfigurationService.getConfiguration();
      String[] config_engines = config.getEngines();
      String[] config_transmissions = config.getTransmissions();
      String[] config_seats = config.getSeats();
      SubConfiguration subConfig = new SubConfiguration(config_engines, config_transmissions, config_seats);

      return subConfig;
    } else {

      int modelId = 0;
      int counter = 0;
      for (Model m : modelList) {
        if (model.equals(m.getName())) {
          modelId = m.getModel_id();
        }
      }

      if (modelId == 0) {
        return null;
        //TODO: exception falseModelName
      }

      Iterator iEngine = engineList.iterator();
      while (iEngine.hasNext()) {
        Engine engine = (Engine) iEngine.next();
        int[] compatible_with = engine.getCompatible_with();
        for (int i : compatible_with) {
          if (i == modelId) {
            compatible.add(engine.getName());
          }
        }
      }
      String[] engines = compatible.toArray(new String[0]);
      compatible.clear();

      Iterator iTransmission = transmissionList.iterator();
      while (iTransmission.hasNext()) {
        Transmission transmission = (Transmission) iTransmission.next();
        int[] compatible_with = transmission.getCompatible_with();
        for (int i : compatible_with) {
          if (i == modelId) {
            compatible.add(transmission.getName());
          }
        }
      }
    String[] transmissions = compatible.toArray(new String[0]);
    compatible.clear();

      Iterator iSeat = seatList.iterator();
      while (iSeat.hasNext()) {
        Seat seat = (Seat) iSeat.next();
        int[] compatible_with = seat.getCompatible_with();
        for (int i : compatible_with) {
          if (i == modelId) {
            compatible.add(seat.getName());
          }
        }
      }
    String[] seats = compatible.toArray(new String[0]);
    compatible.clear();

    SubConfiguration subConfig = new SubConfiguration(engines, transmissions, seats);
    return subConfig;
    }
  }
}
