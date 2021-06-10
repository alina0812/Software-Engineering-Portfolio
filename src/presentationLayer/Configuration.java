package presentationLayer;

public class Configuration {

  private int id;
  private String name;
  private int price;
  private int[] kompatibel_mit;

  public Configuration(int id, String name, int price, int[] kompatibel_mit) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.kompatibel_mit = kompatibel_mit;
  }

  public Configuration(int id, String name, int price) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.kompatibel_mit = null;
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

  public int[] getKompatibel_mit() {
    return kompatibel_mit;
  }

  public void setKompatibel_mit(int[] kompatibel_mit) {
    this.kompatibel_mit = kompatibel_mit;
  }
}
