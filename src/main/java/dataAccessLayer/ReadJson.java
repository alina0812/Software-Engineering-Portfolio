package dataAccessLayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dataAccessLayer.model.ConfigurationDAO;
import java.io.File;
import java.io.IOException;


public class ReadJson {

  private static ConfigurationDAO configurationDAO;

  private ReadJson() {
    // no instance of ReadJson should ever be created
  }


  public static void load_data() {
    File jsonInputFile = new File("Data.json");
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      configurationDAO = objectMapper.readValue(jsonInputFile, ConfigurationDAO.class);
    } catch (IOException e) {

    }
  }

  public static ConfigurationDAO getConfigurationDTO() {
    return configurationDAO;
  }

}
