package presentationLayer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import presentationLayer.Model.AvailableConfiguration;
import presentationLayer.Model.SelectedConfiguration;

public class Controller {

  private View view;
  private AvailableConfiguration availableConfiguration;
  private SelectedConfiguration selectedConfiguration;

  public Controller(View view, AvailableConfiguration availableConfiguration, SelectedConfiguration selectedConfiguration) {
    this.view = view;
    this.availableConfiguration = availableConfiguration;
    this.selectedConfiguration = selectedConfiguration;
    availableConfiguration.addObserver(view);
    selectedConfiguration.addObserver(view);

    //ApplicationLayerClass.getModels();
    //this.updateComboBoxes;

    availableConfiguration.firstInit(); // remove Later
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
    selectedConfiguration.setPrice(p);
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
        selectedConfiguration.setModel((String) e.getItem());
        Controller.this.updateComboBoxes((String) e.getItem());
        Controller.this.calculatePrice();
      }
    }
  }

  class EngineComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setEngine((String) e.getItem());
        Controller.this.calculatePrice();
      }
    }
  }

  class GearComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setTransmission((String) e.getItem());
        Controller.this.calculatePrice();
      }
    }
  }

  class SeatsComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setSeat((String) e.getItem());
        Controller.this.calculatePrice();
      }
    }
  }
}
