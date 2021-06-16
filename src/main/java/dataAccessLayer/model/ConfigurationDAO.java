package dataAccessLayer.model;

import java.util.ArrayList;

public class ConfigurationDAO {

  private ArrayList<SuperOption> models;
  private ArrayList<SubOption> engines;
  private ArrayList<SubOption> transmissions;
  private ArrayList<SubOption> seats;

  public ArrayList<SuperOption> getModels() {
    return models;
  }

  public ArrayList<SubOption> getEngines() {
    return engines;
  }

  public ArrayList<SubOption> getTransmissions() {
    return transmissions;
  }

  public ArrayList<SubOption> getSeats() {
    return  seats;
  }

}
