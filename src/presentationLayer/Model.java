package presentationLayer;

public class Model {

  private Configuration[] models = new Configuration[] {
      new Configuration(100, "Passat", 20000),
      new Configuration(101, "Golf", 10000),
      new Configuration(102, "Porsche", 80000),
      new Configuration(103, "Mercedes", 45000)};

  private Configuration[] engines = new Configuration[] {
      new Configuration(110, "2.0 TDI", 3000, new int[] {100,101}),
      new Configuration(111, "4.0 V8", 8000, new int[] {103}),
      new Configuration(112, "3.5 Boxer", 7000, new int[] {102})};

  private Configuration[] gears = new Configuration[] {
      new Configuration(120, "Automatik", 4000, new int[] {100, 102, 103}),
      new Configuration(121, "Handschaltung", 2000, new int[] {100, 101, 102, 103}),
      new Configuration(122, "Doppelkupplungsgetriebe", 8000, new int[] {102, 103})};

  private Configuration[] seats = new Configuration[] {
      new Configuration(130, "Stoffbezug", 500, new int[] {100, 101}),
      new Configuration(131, "Teilleder", 1000, new int[] {100, 101, 102, 103}),
      new Configuration(132, "Vollleder", 500, new int[] {102, 103})};

  public Configuration[] getModels() {
    return models;
  }

  public Configuration[] getEngines() {
    return engines;
  }

  public Configuration[] getGears() {
    return gears;
  }

  public Configuration[] getSeats() {
    return seats;
  }


  private String model;
  private String engine;
  private String gear;
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

  public String getGear() {
    return gear;
  }

  public void setGear(String gear) {
    this.gear = gear;
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
