package applicationLayer;

import static org.junit.jupiter.api.Assertions.*;

import applicationLayer.model.SubConfigurationDTO;
import dataAccessLayer.ReadJson;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigurationServiceTest {

  private ConfigurationService configurationService;

  @BeforeEach
  void init() throws IOException {
    configurationService = new ConfigurationService(new ReadJson());
    configurationService.getConfiguration();
  }

  @Test
  void calculatePrice() {
    assertEquals(16000, configurationService.calculatePrice("VW Golf", "2.0 TDI",
        "Handschaltung", "Teilleder"));
  }

  @Test
  void testGetSubConfiguration() {
    SubConfigurationDTO subConfigurationDTO = configurationService.getSubConfiguration("VW Golf");
    assert(subConfigurationDTO.getEngines().length == 2);
    assertEquals("1.4 TDI", subConfigurationDTO.getEngines()[0]);
  }
}