package dataAccessLayer.model;

import java.util.ArrayList;

/**
 * This class provides a data type for the data from the JSON file.
 */
public class ConfigurationDAO {

  /**
   * Stores all model data
   */
  private ArrayList<SuperOption> models;

  /**
   * Stores all engine data
   */
  private ArrayList<SubOption> engines;

  /**
   * Stores all transmission data
   */
  private ArrayList<SubOption> transmissions;

  /**
   * Stores all seats data
   */
  private ArrayList<SubOption> seats;

  /**
   * get all model data
   * @return an array list of type 'SuperOption' with all model data
   */
  public ArrayList<SuperOption> getModels() {
    return models;
  }

  /**
   * get all engine data
   * @return an array list of type 'SubOption' with all engine data
   */
  public ArrayList<SubOption> getEngines() {
    return engines;
  }

  /**
   * get all transmission data
   * @return an array list of type 'SubOption' with all transmission data
   */
  public ArrayList<SubOption> getTransmissions() {
    return transmissions;
  }

  /**
   * get all seats data
   * @return an array list of type 'SubOption' with all seats data
   */
  public ArrayList<SubOption> getSeats() {
    return  seats;
  }

}
