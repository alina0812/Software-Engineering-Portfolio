package applicationLayer;

public class SubConfiguration {

  private String[] engines;
  private String[] transmissions;
  private String[] seats;

  public SubConfiguration(String[] engines, String[] transmissions, String[] seats) {
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

  public String[] seats() {
    return seats;
  }
}
