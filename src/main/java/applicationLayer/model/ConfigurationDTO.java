package applicationLayer.model;

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
