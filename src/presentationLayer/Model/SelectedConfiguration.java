package presentationLayer.Model;

import java.util.Observable;

public class SelectedConfiguration  extends Observable {

  private String model;
  private String engine;
  private String transmission;
  private String seat;
  private int price;

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

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
    this.setChanged();
    this.notifyObservers(this);
  }
}
