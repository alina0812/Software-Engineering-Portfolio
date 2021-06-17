package dataAccessLayer.model;

/**
 * This class inherits from the SuperOption class. It only applies for engines, transmissions ans seats.
 * They all need an array, which states with which model they are compatible with.
 */


public class SubOption extends SuperOption {

  private Integer[] compatible_with;

  public Integer[] getCompatible_with() {
    return compatible_with;
  }


}
