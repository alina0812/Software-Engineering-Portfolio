package dataAccessLayer;

import static org.junit.jupiter.api.Assertions.*;

import dataAccessLayer.model.ConfigurationDAO;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ReadJsonTest {

  @Test
  void getConfigurationDTO() throws IOException {
    ReadJson readJson = new ReadJson();
    ConfigurationDAO configurationDAO = readJson.load_data();
    assertAll(
        () -> assertNotNull(configurationDAO.getModels()),
        () -> assertNotNull(configurationDAO.getEngines()),
        () -> assertNotNull(configurationDAO.getTransmissions()),
        () -> assertNotNull(configurationDAO.getSeats()),
        () -> assertEquals(100, configurationDAO.getModels().get(0).getModel_id()),
        () -> assertEquals("VW Passat", configurationDAO.getModels().get(0).getName()),
        () -> assertEquals(20000, configurationDAO.getModels().get(0).getPrice())
    );

  }
}