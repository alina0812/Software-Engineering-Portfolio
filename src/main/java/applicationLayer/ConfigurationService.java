package applicationLayer;

import applicationLayer.model.ConfigurationDTO;
import applicationLayer.model.SubConfigurationDTO;
import dataAccessLayer.ReadJson;
import dataAccessLayer.model.ConfigurationDAO;
import dataAccessLayer.model.SubOption;
import dataAccessLayer.model.SuperOption;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ConfigurationService {

  private final ReadJson readJson;
  private final HashMap<String, SubConfigurationDTO> subConfigurationDTOHashMap;
  private final HashMap<String, Integer> modelPriceHashMap;
  private final HashMap<String, Integer> enginePriceHashMap;
  private final HashMap<String, Integer> transmissionPriceHashMap;
  private final HashMap<String, Integer> seatsPriceMap;

  public ConfigurationService(ReadJson readJson) {
    this.readJson = readJson;
    subConfigurationDTOHashMap = new HashMap<>();
    modelPriceHashMap = new HashMap<>();
    enginePriceHashMap = new HashMap<>();
    transmissionPriceHashMap = new HashMap<>();
    seatsPriceMap = new HashMap<>();
  }

  public int calculatePrice(String model, String engine, String transmission, String seats) {

    int modelPrice;
    int enginePrice;
    int transmissionPrice;
    int seatsPrice;

    modelPrice = modelPriceHashMap.get(model);
    enginePrice = enginePriceHashMap.get(engine);
    transmissionPrice = transmissionPriceHashMap.get(transmission);
    seatsPrice = seatsPriceMap.get(seats);

    return modelPrice + enginePrice + transmissionPrice + seatsPrice;
  }

  public ConfigurationDTO getConfiguration() throws IOException {

    // Get all ConfigurationData
    ConfigurationDAO configurationDAO = readJson.load_data();
    List<SuperOption> modelList = configurationDAO.getModels();
    List<SubOption> engineList = configurationDAO.getEngines();
    List<SubOption> transmissionList = configurationDAO.getTransmissions();
    List<SubOption> seatList = configurationDAO.getSeats();

    if (modelList == null || modelList.isEmpty() || engineList == null || engineList.isEmpty()
        || transmissionList == null || transmissionList.isEmpty() || seatList == null || seatList.isEmpty()) {
      throw new IOException("JSON file does not have the correct syntax");
    }

    String[] models = new String[modelList.size()];
    String[] engines = new String[engineList.size()];
    String[] transmissions = new String[transmissionList.size()];
    String[] seats = new String[seatList.size()];

    // Get all models
    int counter = 0;
    for (SuperOption model : modelList) {
      //Check for mapping errors
      if (model.getName() == null || model.getName().equals("")
          || model.getModel_id() == null || model.getPrice() == null) {
        throw new IOException("JSON file does not have the correct syntax");
      }

      models[counter] = model.getName();
      modelPriceHashMap.put(model.getName(), model.getPrice());
      counter++;
    }

    // Get all engines
    counter = 0;
    for (SubOption engine : engineList) {
      String name = engine.getName();

      //Check for mapping errors
      if (engine.getName() == null || engine.getName().equals("")
          || engine.getModel_id() == null || engine.getPrice() == null
          || engine.getCompatible_with() == null || engine.getCompatible_with().length == 0) {
        throw new IOException("JSON file does not have the correct syntax");
      }

      engines[counter] = name;
      enginePriceHashMap.put(engine.getName(), engine.getPrice());
      counter++;
    }

    //Get all transmissions
    counter = 0;
    for (SubOption transmission : transmissionList) {
      String name = transmission.getName();

      //Check for mapping errors
      if (transmission.getName() == null || transmission.getName().equals("")
          || transmission.getModel_id() == null || transmission.getPrice() == null
          || transmission.getCompatible_with() == null || transmission.getCompatible_with().length == 0) {
        throw new IOException("JSON file does not have the correct syntax");
      }

      transmissions[counter] = name;
      transmissionPriceHashMap.put(transmission.getName(), transmission.getPrice());
      counter++;
    }

    //Get all seats
    counter = 0;
    for (SubOption seat : seatList) {
      String name = seat.getName();

      //Check for mapping errors
      if (seat.getName() == null || seat.getName().equals("")
          || seat.getModel_id() == null || seat.getPrice() == null
          || seat.getCompatible_with() == null || seat.getCompatible_with().length == 0) {
        throw new IOException("JSON file does not have the correct syntax");
      }

      seats[counter] = name;
      seatsPriceMap.put(seat.getName(), seat.getPrice());
      counter++;
    }

    // ConfigurationDTO object with all models, objects and seats
    // config will be returned
    ConfigurationDTO config = new ConfigurationDTO(models, engines, transmissions, seats);


    //fill SubConfiguration-HashMap for easier access to subconfigurations
    subConfigurationDTOHashMap.put("", new SubConfigurationDTO(config.getEngines(),
        config.getTransmissions(), config.getSeats()));
    subConfigurationDTOHashMap.put(null, new SubConfigurationDTO(config.getEngines(),
        config.getTransmissions(), config.getSeats()));

    List<String> compatible = new ArrayList<>();

    for (SuperOption m : modelList) {
      int modelId = m.getModel_id();

      for (SubOption engine : engineList) {
        Integer[] compatible_with = engine.getCompatible_with();
        for (Integer i : compatible_with) {
          if (i == null) {
            throw new IOException("JSON file does not have the correct syntax");
          }
          if (i == modelId) {
            compatible.add(engine.getName());
          }
        }
      }
      if (compatible.isEmpty()) {
        throw new IOException("JSON has not configured engine(s) for model_id: " + modelId);
      }
      String[] engines2 = compatible.toArray(new String[0]);
      compatible.clear();

      for (SubOption transmission : transmissionList) {
        Integer[] compatible_with = transmission.getCompatible_with();
        for (Integer i : compatible_with) {
          if (i == null) {
            throw new IOException("JSON file does not have the correct syntax");
          }
          if (i == modelId) {
            compatible.add(transmission.getName());
          }
        }
      }
      if (compatible.isEmpty()) {
        throw new IOException("JSON has not configured an transmission(s) for model_id: " + modelId);
      }
      String[] transmissions2 = compatible.toArray(new String[0]);
      compatible.clear();

      for (SubOption seat : seatList) {
        Integer[] compatible_with = seat.getCompatible_with();
        for (Integer i : compatible_with) {
          if (i == null) {
            throw new IOException("JSON file does not have the correct syntax");
          }
          if (i == modelId) {
            compatible.add(seat.getName());
          }
        }
      }
      if (compatible.isEmpty()) {
        throw new IOException("JSON has not configured an seats for model_id: " + modelId);
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
