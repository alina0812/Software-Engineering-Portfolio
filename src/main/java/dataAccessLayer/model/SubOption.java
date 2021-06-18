package dataAccessLayer.model;

/**
 * This class inherits from the SuperOption class. It only applies for engines, transmissions ans seats.
 * They all need an array, which states with which model they are compatible with.
 */
public class SubOption extends SuperOption {

  /**
   * Integer Array containing models numbers to whose the option is compatible with
   */
  private Integer[] compatible_with;

  /**
   * returns model_numbers to whose the option is compatible with
   * @return Integer Array 'compatible_with'
   */
  public Integer[] getCompatible_with() {
    return compatible_with;
  }


}
