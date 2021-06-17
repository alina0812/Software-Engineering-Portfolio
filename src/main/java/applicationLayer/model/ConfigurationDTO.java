package applicationLayer.model;

/**
 * This class inherits from the SubConfigurationDTO class and extends it by a Array which stores the different models.
 * In the getConfigurations method it get´s filled with all options which can be selected.
 * When filled, its contents are the same as those from the JSON file.
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
