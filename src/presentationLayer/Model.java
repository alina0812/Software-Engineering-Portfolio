package presentationLayer;

public class Model {

  private String[] models = new String[] {"Passat", "Golf", "Porsche", "Mercedes" };
  private String[] engines = new String[] {"2.0 TDI", "4.0 V8", "3.5 Boxer" };
  private String[] transmissions = new String[] {"Automatik", "Handschaltung", "Doppelkupplungsgetriebe"};
  private String[] seats = new String[] {"Stoffbezug", "Teilleder", "Vollleder"};

  public String[] getModels() {
    return models;
  }

  public void setModels(String[] models) {
    this.models = models;
  }

  public String[] getEngines() {
    return engines;
  }

  public void setEngines(String[] engines) {
    this.engines = engines;
  }

  public String[] getTransmissions() {
    return transmissions;
  }

  public void setTransmissions(String[] transmissions) {
    this.transmissions = transmissions;
  }

  public String[] getSeats() {
    return seats;
  }

  public void setSeats(String[] seats) {
    this.seats = seats;
  }

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
  }
}
