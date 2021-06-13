package presentationLayer.Model;

import java.util.Observable;

public class AvailableConfiguration extends Observable {

  private String[] models;
  private String[] engines;
  private String[] transmissions;
  private String[] seats;

  public void init(String[] models, String[] engines, String[] transmissions, String[] seats) {
    this.models = models;
    this.engines = engines;
    this.transmissions = transmissions;
    this.seats = seats;
    this.setChanged();
    this.notifyObservers(this);
  }

  public void update(String[] engines, String[] transmissions, String[] seats) {
    this.engines = engines;
    this.transmissions = transmissions;
    this.seats = seats;
    this.setChanged();
    this.notifyObservers(this);
  }

  public String[] getModels() {

    return models;
  }

  public String[] getEngines() {

    return engines;
  }

  public String[] getTransmissions() {

    return transmissions;
  }

  public String[] getSeats() {

    return seats;
  }
}
