package applicationLayer.model;

public class ConfigurationDTO {

  private String[] models;
  private String[] engines;
  private String[] transmissions;
  private String[] seats;

  public ConfigurationDTO(String[] models, String[] engines, String[] transmissions, String[] seats) {
    this.models = models;
    this.engines = engines;
    this.transmissions = transmissions;
    this.seats = seats;
  }

  public String[] getModels() {
    return models;
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
