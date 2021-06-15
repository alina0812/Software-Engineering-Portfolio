package dataAccessLayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dataAccessLayer.model.ConfigurationDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ReadJson {

  private ConfigurationDAO configurationDAO;

  public ReadJson() {
  }


  public void load_data() throws IOException {
    if (configurationDAO == null) {
      File jsonInputFile = new File("Data.json");
      ObjectMapper objectMapper = new ObjectMapper();
      if (!jsonInputFile.exists()) {
        throw new FileNotFoundException("File 'Data.json' not found");
      }
      configurationDAO = objectMapper.readValue(jsonInputFile, ConfigurationDAO.class);
    }
  }

  public ConfigurationDAO getConfigurationDTO() {
    return configurationDAO;
  }

}
