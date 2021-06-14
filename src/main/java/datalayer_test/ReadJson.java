package datalayer_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import datalayer_test.model.ConfigurationDTO;
import java.io.File;
import java.io.IOException;


public class ReadJson {

  private static ConfigurationDTO configurationDTO;


  private ReadJson() {
    // no instance of ReadJson should ever be created
  }


  public static void load_data() {
    File jsonInputFile = new File("Data.json");
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      configurationDTO = objectMapper.readValue(jsonInputFile, ConfigurationDTO.class);
    } catch (IOException e) {

    }
  }

  public static ConfigurationDTO getConfigurationDTO() {
    return configurationDTO;
  }

}
