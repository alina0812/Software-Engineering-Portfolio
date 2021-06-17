package applicationLayer.model;

/**
 * This class inherits from the SubConfigurationDTO class, wich contains Arrays for the different engines, transmissions and seats
 */

public class ConfigurationDTO extends SubConfigurationDTO {

  private final String[] models;

  public ConfigurationDTO(String[] models, String[] engines, String[] transmissions, String[] seats) {
    super(engines, transmissions, seats);
    this.models = models;
  }

  public String[] getModels() {
    return models;
  }
}
