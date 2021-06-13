package applicationLayer;

public class Configuration {

  private String[] models;
  private String[] engines;
  private String[] transmissions;
  private String[] seats;

  public Configuration(String[] models, String[] engines, String[] transmissions, String[] seats) {
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

  public String[] seats() {
    return seats;
  }
}
