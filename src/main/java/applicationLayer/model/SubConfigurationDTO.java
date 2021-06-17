package applicationLayer.model;

/**
 * This class provides the data type
 */

public class SubConfigurationDTO {

  private String[] engines;
  private String[] transmissions;
  private String[] seats;

  public SubConfigurationDTO(String[] engines, String[] transmissions, String[] seats) {
    this.engines = engines;
    this.transmissions = transmissions;
    this.seats = seats;
  }

  public String[] getEngines() {
    return engines;
  }

  public String[] getTransmissions() {
    return transmissions;
  }

  public String[] getSeats() {
    return seats;
  }
}
