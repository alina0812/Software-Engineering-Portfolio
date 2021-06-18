package applicationLayer.model;

/**
 * This class provides the data type for the engines, transmissions and seats.
 * In the getConfiguration method, if given a specific model, it get´s filled with engines,
 * transmissions and seats dependent on the selected model.
 */

public class SubConfigurationDTO {

  /**
   * The String Arrays engines, transmissions and seats contain the names of the engines, transmissions and seats
   * configured by the getConfiguration method.
   */
  private String[] engines;
  private String[] transmissions;
  private String[] seats;

  /**
   * Constructor method assigns the inputs to the Arrays
   * @param engines String Array, filled with the names of the engines
   * @param transmissions String Array, filled with the names of the transmissions
   * @param seats String Array, filled with the names of the seats
   */
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
