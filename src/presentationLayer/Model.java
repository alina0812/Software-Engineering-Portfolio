package presentationLayer;

public class Model {

  private Configuration[] modelle = new Configuration[] {
      new Configuration(100, "Passat", 20000),
      new Configuration(101, "Golf", 10000),
      new Configuration(102, "Porsche", 80000),
      new Configuration(103, "Mercedes", 45000)};

  private Configuration[] motoren = new Configuration[] {
      new Configuration(110, "2.0 TDI", 3000, new int[] {100,101}),
      new Configuration(111, "4.0 V8", 8000, new int[] {103}),
      new Configuration(112, "3.5 Boxer", 7000, new int[] {102})};

  private Configuration[] getriebe = new Configuration[] {
      new Configuration(120, "Automatik", 4000, new int[] {100, 102, 103}),
      new Configuration(121, "Handschaltung", 2000, new int[] {100, 101, 102, 103}),
      new Configuration(122, "Doppelkupplungsgetriebe", 8000, new int[] {102, 103})};

  private Configuration[] sitze = new Configuration[] {
      new Configuration(130, "Stoffbezug", 500, new int[] {100, 101}),
      new Configuration(131, "Teilleder", 1000, new int[] {100, 101, 102, 103}),
      new Configuration(132, "Vollleder", 500, new int[] {102, 103})};

  public Configuration[] getModelle() {
    return modelle;
  }

  public Configuration[] getMotoren() {
    return motoren;
  }

  public Configuration[] getGetriebe() {
    return getriebe;
  }

  public Configuration[] getSitze() {
    return sitze;
  }
}
