package dataAccessLayer.model;

import java.util.ArrayList;

public class ConfigurationDAO {

  private ArrayList<Model> models;
  private ArrayList<Engine> engines;
  private ArrayList<Transmission> transmissions;
  private ArrayList<Seat> seats;

  public ArrayList<Model> getModels() {
    return models;
  }

  public ArrayList<Engine> getEngines() {
    return engines;
  }

  public ArrayList<Transmission> getTransmissions() {
    return transmissions;
  }

  public ArrayList<Seat> getSeats() {
    return  seats;
  }

}
