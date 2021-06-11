package presentationLayer;

public class Controller {

  private View view;
  private Model model;

  public Controller(View view, Model model) {
    this.view = view;
    this.model = model;

    Configuration[] models = model.getModels(); // Later: ApplicationLayerClass.getModelle();
    for (Configuration c : models) {
      view.addItemComboBoxModel(c.getName());
    }
    Configuration[] engines = model.getEngines();
    for (Configuration c : engines) {
      view.addItemComboBoxEngine(c.getName());
    }
    Configuration[] gears = model.getGears();
    for (Configuration c : gears) {
      view.addItemComboBoxGear(c.getName());
    }
    Configuration[] seats = model.getSeats();
    for (Configuration c : seats) {
      view.addItemComboBoxSeats(c.getName());
    }
    view.setPriceResult(20000);
    view.setVisible(true);
  }
}
