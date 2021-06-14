package presentationLayer.model;

import java.util.Observable;

public class SelectedConfiguration  extends Observable {

  private String model;
  private String engine;
  private String transmission;
  private String seats;
  private Integer price;

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getEngine() {
    return engine;
  }

  public void setEngine(String engine) {
    this.engine = engine;
  }

  public String getTransmission() {
    return transmission;
  }

  public void setTransmission(String transmission) {
    this.transmission = transmission;
  }

  public String getSeats() {
    return seats;
  }

  public void setSeats(String seats) {
    this.seats = seats;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
    this.setChanged();
    this.notifyObservers(this);
  }

  public boolean areModelEngineTransmissionSeatsSet() {
    if (this.model != null && !this.model.isEmpty()
        && this.engine != null && !this.engine.isEmpty()
        && this.transmission != null && !this.transmission.isEmpty()
        && this.seats != null && !this.seats.isEmpty()) {
      return true;
    }
    return false;
  }
}
