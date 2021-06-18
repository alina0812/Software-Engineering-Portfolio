package applicationLayer.model;

/**
 * This class inherits from the SubConfigurationDTO class and extends it by a Array which stores the different models.
 * In the getConfigurations method it get´s filled with all options which can be selected.
 * When filled, its contents are the same as those from the JSON file.
 */
public class ConfigurationDTO extends SubConfigurationDTO {

  /**
   * The String Array models stores the names of the models
   */
  private final String[] models;

  /**
   * Constructor method, first calls parent constructor and then assigns given String Array to attribute.
   * @param models String Array, filled with the names of the models.
   * @param engines String Array, filled with the names of the engines.
   * @param transmissions String Array, filled with the names of the transmissions.
   * @param seats String Array, filled with the names of the seats.
   */
  public ConfigurationDTO(String[] models, String[] engines, String[] transmissions, String[] seats) {
    super(engines, transmissions, seats);
    this.models = models;
  }

  /**
   * Getter to get attribute models
   * @return String array with all models
   */
  public String[] getModels() {
    return models;
  }
}
