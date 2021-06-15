package applicationLayer;

import applicationLayer.model.ConfigurationDTO;
import applicationLayer.model.SubConfigurationDTO;
import com.fasterxml.jackson.databind.JsonMappingException;
import dataAccessLayer.model.ConfigurationDAO;
import dataAccessLayer.model.Engine;
import dataAccessLayer.model.Model;
import dataAccessLayer.model.Seat;
import dataAccessLayer.model.Transmission;
import dataAccessLayer.ReadJson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ConfigurationService {

  private final ReadJson readJson;
  private final HashMap<String, SubConfigurationDTO> subConfigurationDTOHashMap;
  private final HashMap<String, Integer> modelPriceHashMap;
  private final HashMap<String, Integer> enginePriceHashMap;
  private final HashMap<String, Integer> transmissionPriceHashMap;
  private final HashMap<String, Integer> seatsPriceMap;

  public ConfigurationService() throws IOException {
    readJson = new ReadJson();
    readJson.load_data();
    subConfigurationDTOHashMap = new HashMap<String, SubConfigurationDTO>();
    modelPriceHashMap = new HashMap<>();
    enginePriceHashMap = new HashMap<>();
    transmissionPriceHashMap = new HashMap<>();
    seatsPriceMap = new HashMap<>();
  }

  public ConfigurationDTO getSubConfiguration() throws IOException {

    // Get all ConfigurationData
    ConfigurationDAO configurationDAO = readJson.getConfigurationDTO();
    List<Model> modelList = configurationDAO.getModels();
    List<Engine> engineList = configurationDAO.getEngines();
    List<Transmission> transmissionList = configurationDAO.getTransmissions();
    List<Seat> seatList = configurationDAO.getSeats();

    if (modelList == null || modelList.isEmpty() || engineList == null || engineList.isEmpty() ||
        transmissionList == null || transmissionList.isEmpty() || seatList == null || seatList.isEmpty()) {
      throw new IOException("JSON file does not have the correct syntax");
    }

    String[] models = new String[modelList.size()];
    String[] engines = new String[engineList.size()];
    String[] transmissions = new String[transmissionList.size()];
    String[] seats = new String[seatList.size()];

    int counter = 0;
    Iterator iModel = modelList.iterator();
    while (iModel.hasNext()) {
      Model model = (Model) iModel.next();
      models[counter] = model.getName();
      modelPriceHashMap.put(model.getName(), model.getPrice());
      counter++;
    }
    counter = 0;
    Iterator iEngine = engineList.iterator();
    while (iEngine.hasNext()) {
      Engine engine = (Engine) iEngine.next();
      engines[counter] = engine.getName();
      enginePriceHashMap.put(engine.getName(), engine.getPrice());
      counter++;
    }
    counter = 0;
    Iterator iTransmission = transmissionList.iterator();
    while (iTransmission.hasNext()) {
      Transmission transmission = (Transmission) iTransmission.next();
      transmissions[counter] = transmission.getName();
      transmissionPriceHashMap.put(transmission.getName(), transmission.getPrice());
      counter++;
    }
    counter = 0;
    Iterator iSeat = seatList.iterator();
    while (iSeat.hasNext()) {
      Seat seat = (Seat) iSeat.next();
      seats[counter] = seat.getName();
      seatsPriceMap.put(seat.getName(), seat.getPrice());
      counter++;
    }

    //config will be returned
    ConfigurationDTO config = new ConfigurationDTO(models, engines, transmissions, seats);


    //fill SubConfiguration-HashMap for easier access to subconfigurations
    subConfigurationDTOHashMap.put("", new SubConfigurationDTO(config.getEngines(),
        config.getTransmissions(), config.getSeats()));
    subConfigurationDTOHashMap.put(null, new SubConfigurationDTO(config.getEngines(),
        config.getTransmissions(), config.getSeats()));

    List<String> compatible = new ArrayList<String>();

    for (Model m : modelList) {
      int modelId = m.getModel_id();

      Iterator iEngine2 = engineList.iterator();
      while (iEngine2.hasNext()) {
        Engine engine = (Engine) iEngine2.next();
        int[] compatible_with = engine.getCompatible_with();
        for (int i : compatible_with) {
          if (i == modelId) {
            compatible.add(engine.getName());
          }
        }
      }
      String[] engines2 = compatible.toArray(new String[0]);
      compatible.clear();

      Iterator iTransmission2 = transmissionList.iterator();
      while (iTransmission2.hasNext()) {
        Transmission transmission = (Transmission) iTransmission2.next();
        int[] compatible_with = transmission.getCompatible_with();
        for (int i : compatible_with) {
          if (i == modelId) {
            compatible.add(transmission.getName());
          }
        }
      }
      String[] transmissions2 = compatible.toArray(new String[0]);
      compatible.clear();

      Iterator iSeat2 = seatList.iterator();
      while (iSeat2.hasNext()) {
        Seat seat = (Seat) iSeat2.next();
        int[] compatible_with = seat.getCompatible_with();
        for (int i : compatible_with) {
          if (i == modelId) {
            compatible.add(seat.getName());
          }
        }
      }
      String[] seats2 = compatible.toArray(new String[0]);
      compatible.clear();

      SubConfigurationDTO subConfig = new SubConfigurationDTO(engines2, transmissions2, seats2);
      subConfigurationDTOHashMap.put(m.getName(), subConfig);
    }
    return config;
  }


  public SubConfigurationDTO getSubConfiguration(String model) {
    return subConfigurationDTOHashMap.get(model);
  }

}
