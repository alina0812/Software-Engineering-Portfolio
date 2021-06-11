package presentationLayer;

public class Configuration {

  private int id;
  private String name;
  private int price;
  private int[] compatibleWith;

  public Configuration(int id, String name, int price, int[] compatibleWith) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.compatibleWith = compatibleWith;
  }

  public Configuration(int id, String name, int price) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.compatibleWith = null;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int[] getCompatibleWith() {
    return compatibleWith;
  }

  public void setCompatibleWith(int[] compatibleWith) {
    this.compatibleWith = compatibleWith;
  }
}
