package presentationLayer.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SelectedConfigurationTest {

  @Test
  void areModelEngineTransmissionSeatsSet() {
    SelectedConfiguration selectedConfiguration = new SelectedConfiguration();
    selectedConfiguration.setModel("a");
    selectedConfiguration.setEngine("b");
    selectedConfiguration.setTransmission("c");
    selectedConfiguration.setSeats("d");
    assertTrue(selectedConfiguration.areModelEngineTransmissionSeatsSet());
  }

  @Test
  void areModelEngineTransmissionSeatsSet2() {
    SelectedConfiguration selectedConfiguration = new SelectedConfiguration();
    selectedConfiguration.setModel("a");
    selectedConfiguration.setEngine("b");
    selectedConfiguration.setTransmission("c");
    assertFalse(selectedConfiguration.areModelEngineTransmissionSeatsSet());
  }
}