package presentationLayer;

import applicationLayer.Configuration;
import applicationLayer.GetConfiguration;
import applicationLayer.SubConfiguration;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import presentationLayer.Model.AvailableConfiguration;
import presentationLayer.Model.SelectedConfiguration;

public class Controller {

  private final View view;
  private final AvailableConfiguration availableConfiguration;
  private final SelectedConfiguration selectedConfiguration;

  public Controller(View view, AvailableConfiguration availableConfiguration,
                    SelectedConfiguration selectedConfiguration) {
    this.view = view;
    this.availableConfiguration = availableConfiguration;
    this.selectedConfiguration = selectedConfiguration;
    availableConfiguration.addObserver(view);
    selectedConfiguration.addObserver(view);

    System.out.println("Call gegen ApplicationLayer  --> getAllConfiguration Data");
    Configuration configuration = GetConfiguration.getConfiguration();
    availableConfiguration.init(configuration.getModels(), configuration.getEngines(), configuration.getTransmissions(), configuration.getSeats());

    this.view.addModelSelectionListener(new ModelComboBoxListener());
    this.view.addEngineSelectionListener(new EngineComboBoxListener());
    this.view.addGearSelectionListener(new TransmissionComboBoxListener());
    this.view.addSeatsSelectionListener(new SeatsComboBoxListener());

    view.setDefaultBackgroundComboBoxEngines();
    view.setDefaultBackgroundComboBoxTransmissions();
    view.setDefaultBackgroundComboBoxSeats();
    view.setVisible(true);

  }

  private void calculatePrice() {
    int p = 0;                              // remove later

    System.out.println("Call gegen Application Layer --> Calculate Price");
    //int p = ApplicationLayerClass.calculatePrice(model.getModel(),
    // model.getEngine(), model.getGear(), model.getSeat());
    System.out.println("Preis ist: " + p);  //// remove if method is fully implemented and working
    selectedConfiguration.setPrice(p);
  }

  private void updateComboBoxes(String model) {
    System.out.println("This method should update all ComboBoxes according to model: " + model);
    System.out.println("Call gegen Application Layer --> Get Configuration for model");
    SubConfiguration subConfiguration = GetConfiguration.getConfiguration(model);
    availableConfiguration.update(subConfiguration.getEngines(), subConfiguration.getTransmissions(), subConfiguration.getSeats());
  }

  // ItemListener for all ComboBoxes

  class ModelComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        System.out.println("Model changed");              // remove Later
        selectedConfiguration.setModel((String) e.getItem());
        Controller.this.updateComboBoxes((String) e.getItem());
        if (selectedConfiguration.areModelEngineTransmissionSeatsSet()) {
          Controller.this.calculatePrice();
        } else if (selectedConfiguration.getPrice() != null) {
          selectedConfiguration.setPrice(null);
        }
      }
    }
  }

  class EngineComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setEngine((String) e.getItem());
        view.setDefaultBackgroundComboBoxEngines();
        if (selectedConfiguration.areModelEngineTransmissionSeatsSet()) {
          Controller.this.calculatePrice();
        } else if (selectedConfiguration.getPrice() != null) {
          selectedConfiguration.setPrice(null);
        }
      }
    }
  }

  class TransmissionComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setTransmission((String) e.getItem());
        view.setDefaultBackgroundComboBoxTransmissions();
        if (selectedConfiguration.areModelEngineTransmissionSeatsSet()) {
          Controller.this.calculatePrice();
        } else if (selectedConfiguration.getPrice() != null) {
          selectedConfiguration.setPrice(null);
        }
      }
    }
  }

  class SeatsComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setSeats((String) e.getItem());
        view.setDefaultBackgroundComboBoxSeats();
        if (selectedConfiguration.areModelEngineTransmissionSeatsSet()) {
          Controller.this.calculatePrice();
        } else if (selectedConfiguration.getPrice() != null) {
          selectedConfiguration.setPrice(null);
        }
      }
    }
  }
}
