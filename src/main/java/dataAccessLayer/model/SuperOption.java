package dataAccessLayer.model;

/**
 * This parent-class provides a basic structure for the Subtype. The Data for the models get´s saved here.
 */

public class SuperOption {

  private Integer model_id;
  private String name;
  private Integer price;

  public Integer getModel_id() {
    return model_id;
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }

}
