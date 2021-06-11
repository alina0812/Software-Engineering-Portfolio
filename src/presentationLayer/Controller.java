package presentationLayer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Controller {

  private View view;
  private Model model;

  public Controller(View view, Model model) {
    this.view = view;
    this.model = model;

    //ApplicationLayerClass.getModels();
    //this.updateComboBoxes;

    String[] models = model.getModels();   // Later: ApplicationLayerClass.getModelle();
    for (String m : models) {
      view.addItemComboBoxModel(m);
    }
    String[] engines = model.getEngines(); // Later: this.updateComboBoxes();
    for (String e : engines) {             //
      view.addItemComboBoxEngine(e);    //
    }
    String[] transmissions = model.getTransmissions();     //
    for (String t : transmissions) {               //
      view.addItemComboBoxTransmission(t);      //
    }
    String[] seats = model.getSeats();     //
    for (String s : seats) {               //
      view.addItemComboBoxSeats(s);     //
    }
    this.calculatePrice();
    view.setVisible(true);

    this.view.addModelSelectionListener(new ModelComboBoxListener());
    this.view.addEngineSelectionListener(new EngineComboBoxListener());
    this.view.addGearSelectionListener(new GearComboBoxListener());
    this.view.addSeatsSelectionListener(new SeatsComboBoxListener());
  }

  private void calculatePrice() {
    System.out.println("Preis berechnen");  // remove if method is fully implemented and working
    int p = 0;                              // this as well

    //int p = ApplicationLayerClass.calculatePrice(model.getModel(), model.getEngine(), model.getGear(), model.getSeat());
    model.setPrice(p);
    view.setPriceResult(model.getPrice());
  }

  private void updateComboBoxes(String model) {
    System.out.println("This method should update all ComboBoxes according to model: " + model);

    // In der ApplicationLayer sollte für Motor/Getriebe/Sitze jeweils einer String-Liste mit passenden Werten zurückgegeben werden
    //ConfigurationDAO = ApplicationLayerClass.getSuitedConfigurations(String model);
    //Klasse configurationDAO hat Attribute List/Array<Motoren>, List/Array<Getriebe> und List/Array<Sitze> mit passenden Gettern und Settern


    //Die ComboBox - Items werden mit den Items in der Liste verglichen und ersetzt
    //an bereits Selektierte Optionen denken
  }



  // ItemListener for all ComboBoxes

  class ModelComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        model.setModel((String) e.getItem());
        System.out.println(model.getModel());
        Controller.this.calculatePrice();
        Controller.this.updateComboBoxes((String) e.getItem());
      }
    }
  }

  class EngineComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        model.setEngine((String) e.getItem());
        System.out.println(model.getEngine());
        Controller.this.calculatePrice();
      }
    }
  }

  class GearComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        model.setTransmission((String) e.getItem());
        Controller.this.calculatePrice();
      }
    }
  }

  class SeatsComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        model.setSeat((String) e.getItem());
        Controller.this.calculatePrice();
      }
    }
  }
}
