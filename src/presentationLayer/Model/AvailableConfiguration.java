package presentationLayer.Model;

import java.util.Observable;

public class AvailableConfiguration extends Observable {

  private String[] models = new String[] {"Passat", "Golf", "Porsche", "Mercedes"}; // remove initialization later
  private String[] engines = new String[] {"2.0 TDI", "4.0 V8", "3.5 Boxer"};
  private String[] transmissions = new String[] {"Automatik", "Handschaltung", "Doppelkupplungsgetriebe"};
  private String[] seats = new String[] {"Stoffbezug", "Teilleder", "Vollleder"};

  public void firstInit() {           // remove this method later
    this.setChanged();
    this.notifyObservers(this);
  }

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
