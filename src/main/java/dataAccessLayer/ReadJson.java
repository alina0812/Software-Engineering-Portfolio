package dataAccessLayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import dataAccessLayer.model.ConfigurationDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is used to read the JSON File.
 */

public class ReadJson {

  /**
   * Method reads the JSON file and maps the data into the ConfigurationDAO object
   * @return ConfigurationDAO object containing all configuration data
   * @throws IOException if JSON file is in a wrong syntax
   */
  public ConfigurationDAO load_data() throws IOException {
    File jsonInputFile = new File("Data.json");
    ObjectMapper objectMapper = new ObjectMapper();
    if (!jsonInputFile.exists()) {
      throw new FileNotFoundException("File 'Data.json' not found");
    }
    // Mapping into class ConfigurationDAO
    return objectMapper.readValue(jsonInputFile, ConfigurationDAO.class);
  }

}
