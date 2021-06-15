package dataAccessLayer;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataAccessLayer.model.ConfigurationDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ReadJson {

  private static ConfigurationDAO configurationDAO;

  private ReadJson() {}


  public static void load_data() {
    if (configurationDAO == null){
      File jsonInputFile = new File("Data.json");
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        if (!jsonInputFile.exists()) {
          throw new FileNotFoundException("File 'Data.json' not found");
        }
        configurationDAO = objectMapper.readValue(jsonInputFile, ConfigurationDAO.class);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static ConfigurationDAO getConfigurationDTO() {
    return configurationDAO;
  }

}
