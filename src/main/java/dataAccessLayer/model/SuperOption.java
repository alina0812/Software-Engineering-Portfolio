package dataAccessLayer.model;

/**
 * This parent-class provides a basic structure for the Subtype. The Data for the models gets saved here.
 */
public class SuperOption {

  /**
   * model_id of the option
   */
  private Integer model_id;

  /**
   * name of the option
   */
  private String name;

  /**
   * price of the option
   */
  private Integer price;

  /**
   * returns price of the option
   * @return Integer price
   */
  public Integer getModel_id() {
    return model_id;
  }

  /**
   * returns name of the option
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * returns price of the option
   * @return Integer price
   */
  public Integer getPrice() {
    return price;
  }
}
