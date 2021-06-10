package presentationLayer;

public class Controller {

  private View view;
  private Model model;

  public Controller(View view, Model model) {
    this.view = view;
    this.model = model;

    Configuration[] modelle = model.getModelle();
    for (Configuration c : modelle) {
      view.addItemComboBoxModell(c.getName());
    }
    Configuration[] motoren = model.getMotoren();
    for (Configuration c : motoren) {
      view.addItemComboBoxMotor(c.getName());
    }
    Configuration[] getriebe = model.getGetriebe();
    for (Configuration c : getriebe) {
      view.addItemComboBoxGetriebe(c.getName());
    }
    Configuration[] sitze = model.getSitze();
    for (Configuration c : sitze) {
      view.addItemComboBoxSitze(c.getName());
    }
    view.setPreisErgebnis(20000);
    view.setVisible(true);
  }
}
