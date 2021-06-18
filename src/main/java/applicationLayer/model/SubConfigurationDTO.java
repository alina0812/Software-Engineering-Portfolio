package applicationLayer.model;

/**
 * This class provides the data type for the engines, transmissions and seats.
 * In the getConfiguration method, if given a specific model, it get´s filled with engines,
 * transmissions and seats dependent on the selected model.
 */
public class SubConfigurationDTO {

  /**
   * The String Array engines contains the names of the engines configured by the getConfiguration method.
   */
  private final String[] engines;

  /**
   * The String Array transmissions and seats contains the names of the transmissions
   * configured by the getConfiguration method.
   */
  private final String[] transmissions;

  /**
   * The String Array seats contains the names of the seats configured by the getConfiguration method
   */
  private final String[] seats;

  /**
   * Constructor method assigns the inputs to the Arrays.
   * @param engines String Array, filled with the names of the engines.
   * @param transmissions String Array, filled with the names of the transmissions.
   * @param seats String Array, filled with the names of the seats.
   */
  public SubConfigurationDTO(String[] engines, String[] transmissions, String[] seats) {
    this.engines = engines;
    this.transmissions = transmissions;
    this.seats = seats;
  }

  /**
   * returns engines
   * @return String array 'engines'
   */
  public String[] getEngines() {
    return engines;
  }

  /**
   * returns transmissions
   * @return String array 'transmissions'
   */
  public String[] getTransmissions() {
    return transmissions;
  }

  /**
   * returns seats
   * @return String array 'seats'
   */
  public String[] getSeats() {
    return seats;
  }
}
