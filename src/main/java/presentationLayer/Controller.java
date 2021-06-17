package presentationLayer;

import applicationLayer.ConfigurationService;
import applicationLayer.model.ConfigurationDTO;
import applicationLayer.model.SubConfigurationDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import presentationLayer.model.AvailableConfiguration;
import presentationLayer.model.SelectedConfiguration;

/**
 * Controller of MVC pattern <br>
 * Connecting view, models and ConfigurationService in the application layer
 */
public class Controller {

  /**
   * instance of the view of the application
   */
  private final View view;
  /**
   * instance of the model `AvailableConfiguration` to store all available options
   * according to the selected model in the view
   */
  private final AvailableConfiguration availableConfiguration;
  /**
   * instance of the model `SelectedConfiguration` to save the configuration selected in the view
   */
  private final SelectedConfiguration selectedConfiguration;
  /**
   * ConfigurationService (in ApplicationLayer) <br>
   * Used to get all configuration data, calculate the price or to get subconfiguration data for one model
   */
  private final ConfigurationService configurationService;

  /**
   * Instantiates the Controller, sets attributes, gets configuration data from application layer,
   * fills model `available configuration` and sets view visible
   * @param view instance of the view (created in the static void main method)
   * @param availableConfiguration instance of the model availableConfiguration (created in the static void main method)
   * @param selectedConfiguration instance of the model selectedConfiguration (created in the static void main method)
   * @param configurationService instance of the configurationService to connect to the application layer (created in the static void main method)
   * @throws IOException if JSON file is not found or in a wrong syntax
   */
  public Controller(View view, AvailableConfiguration availableConfiguration,
                    SelectedConfiguration selectedConfiguration,
                    ConfigurationService configurationService) throws IOException {
    this.view = view;
    this.availableConfiguration = availableConfiguration;
    this.selectedConfiguration = selectedConfiguration;
    this.configurationService = configurationService;
    availableConfiguration.addObserver(view);
    selectedConfiguration.addObserver(view);

    ConfigurationDTO configurationDTO = configurationService.getConfiguration();
    availableConfiguration.init(configurationDTO.getModels(), configurationDTO.getEngines(),
        configurationDTO.getTransmissions(), configurationDTO.getSeats());

    this.view.addModelSelectionListener(new ModelComboBoxListener());
    this.view.addEngineSelectionListener(new EngineComboBoxListener());
    this.view.addTransmissionSelectionListener(new TransmissionComboBoxListener());
    this.view.addSeatsSelectionListener(new SeatsComboBoxListener());
    this.view.addResetButtonActionListener(new ResetButtonListener());

    view.setVisible(true);
  }

  /**
   * This method is inovked if the combo boxes selected items have changed through user input.
   * Calculates the price in the application layer for the selected configuration,
   * which is available in the model `selectedConfiguration`.
   * Saves the calculated price in the model `selectedConfiguration`.
   */
  private void calculatePrice() {
    int p = configurationService.calculatePrice(selectedConfiguration.getModel(), selectedConfiguration.getEngine(),
        selectedConfiguration.getTransmission(), selectedConfiguration.getSeats());
    selectedConfiguration.setPrice(p);

  }

  /**
   * Invoked after the selected model in the according comboBox in the view has changed. <br>
   * Gets suboption data for the selected model via the application layer <br>
   * If the previously selected configuration data (selected engine, transmission and/or seats) is not part of the new
   * configuration data, the according option in the model `SelectedConfiguration` is set to null
   * Updates then the model `AvailableConfiguration` with the new suboptions <br>
   * @param model model Selected model in the according combo box in the view
   */
  private void updateComboBoxes(String model) {
    SubConfigurationDTO subConfigurationDTO = configurationService.getSubConfiguration(model);
    // update selected options if they are not available anymore
    for (String engine : subConfigurationDTO.getEngines()) {
      if (engine.equals(selectedConfiguration.getEngine())) {
        break;
      }
      selectedConfiguration.setEngine(null);
    }
    for (String transmission : subConfigurationDTO.getTransmissions()) {
      if (transmission.equals(selectedConfiguration.getTransmission())) {
        break;
      }
      selectedConfiguration.setTransmission(null);
    }
    for (String seats : subConfigurationDTO.getSeats()) {
      if (seats.equals(selectedConfiguration.getSeats())) {
        break;
      }
      selectedConfiguration.setSeats(null);
    }
    availableConfiguration.update(subConfigurationDTO.getEngines(),
        subConfigurationDTO.getTransmissions(), subConfigurationDTO.getSeats());
  }

  /**
   * Item listener for the combo box `comboBoxModels` in the view
   */
  class ModelComboBoxListener implements ItemListener {

    /**
     * Invoked if the selected item of the `modelComboBox` in the view has changed. <br>
     * Sets the attribute `model` in the model `SelectedConfiguration` to the selected option. <br>
     * Calls then the method `updateComboBoxes` to update the `AvailableConfiguration` data
     * with suboption for the selected model <br>
     * If all configuration data in `selectedConfiguration` is set, the price for the configuration is going to be calculated.
     * Else the price is `null` and the user is called to select configurations
     * @param e Selected item of the combo box `comboBoxModels` in the view
     */
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        if (e.getItem() == null) {
          selectedConfiguration.setModel(null);
          Controller.this.updateComboBoxes(null);
        } else {
          selectedConfiguration.setModel(e.getItem().toString());
          Controller.this.updateComboBoxes(e.getItem().toString());
        }
        if (selectedConfiguration.areModelEngineTransmissionSeatsSet()) {
          Controller.this.calculatePrice();
          view.setMessageText(null);
        } else {
          if (selectedConfiguration.getPrice() != null) {
            selectedConfiguration.setPrice(null);
          }
          if (view.isMessageBoxEmpty()) {
            view.setMessageText("<html>Bitte wählen Sie eine Fahrzeugkonfiguration aus<html>");
          }
        }
      }
    }
  }

  /**
   * Item listener for the combo box `comboBoxEngines` in the view
   */
  class EngineComboBoxListener implements ItemListener {

    /**
     * Invoked if the selected item of the `engineComboBox` in the view has changed. <br>
     * Sets the attribute `engine` in the model `SelectedConfiguration` to the selected option. <br>
     * If all configuration data in `selectedConfiguration` is set, the price for the configuration is going to be calculated.
     * Else the price is `null` and the user is called to select configurations
     * @param e selected item of the combo box `comboBoxEngines` in the view
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        if (e.getItem() == null) {
          selectedConfiguration.setEngine(null);
        } else {
          selectedConfiguration.setEngine(e.getItem().toString());
        }
        view.setDefaultBackgroundComboBoxEngines();
        if (selectedConfiguration.areModelEngineTransmissionSeatsSet()) {
          Controller.this.calculatePrice();
          view.setMessageText(null);
        } else {
          if (selectedConfiguration.getPrice() != null) {
            selectedConfiguration.setPrice(null);
          }
          if (view.isMessageBoxEmpty()) {
            view.setMessageText("<html>Bitte wählen Sie eine Fahrzeugkonfiguration aus<html>");
          }
        }
      }
    }
  }

  /**
   * Item listener for the combo box `comboBoxTransmissions` in the view
   */
  class TransmissionComboBoxListener implements ItemListener {

    /**
     * Invoked if the selected item of the `transmissionComboBox` in the view has changed. <br>
     * Sets the attribute `transmission` in the model `SelectedConfiguration` to the selected option. <br>
     * If all configuration data in `selectedConfiguration` is set, the price for the configuration is going to be calculated.
     * Else the price is `null` and the user is called to select configurations
     * @param e selected item in the combo box `comboBoxTransmissions` in the view
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        if (e.getItem() == null) {
          selectedConfiguration.setTransmission(null);
        } else {
          selectedConfiguration.setTransmission(e.getItem().toString());
        }
        view.setDefaultBackgroundComboBoxTransmissions();
        if (selectedConfiguration.areModelEngineTransmissionSeatsSet()) {
          Controller.this.calculatePrice();
          view.setMessageText(null);
        } else {
          if (selectedConfiguration.getPrice() != null) {
            selectedConfiguration.setPrice(null);
          }
          if (view.isMessageBoxEmpty()) {
            view.setMessageText("<html>Bitte wählen Sie eine Fahrzeugkonfiguration aus<html>");
          }
        }
      }
    }
  }

  /**
   * Item listener for the combo box `comboBoxSeats` in the view
   */
  class SeatsComboBoxListener implements ItemListener {

    /**
     * Invoked if the selected item of the `seatsComboBox` in the view has changed. <br>
     * Sets the attribute `seats` in the model `SelectedConfiguration` to the selected option. <br>
     * If all configuration data in `selectedConfiguration` is set, the price for the configuration is going to be calculated.
     * Else the price is `null` and the user is called to select configurations
     * @param e selected item in the combo box `comboBoxSeats`
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        if (e.getItem() == null) {
          selectedConfiguration.setSeats(null);
        } else {
          selectedConfiguration.setSeats(e.getItem().toString());
        }
        view.setDefaultBackgroundComboBoxSeats();
        if (selectedConfiguration.areModelEngineTransmissionSeatsSet()) {
          Controller.this.calculatePrice();
          view.setMessageText(null);
        } else {
          if (selectedConfiguration.getPrice() != null) {
            selectedConfiguration.setPrice(null);
          }
          if (view.isMessageBoxEmpty()) {
            view.setMessageText("<html>Bitte wählen Sie eine Fahrzeugkonfiguration aus<html>");
          }
        }
      }
    }
  }

  /**
   * Action listener for the button `resetButton` in the view
   */
  class ResetButtonListener implements ActionListener {

    /**
     * Invoked it the button `resetButton` in the view is clicked.
     * Resets the options in the model `selectedConfiguration` to default values so that all combo boxes in the view are empty
     * @param e Button clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      view.setMessageText("<html>Bitte wählen Sie eine Fahrzeugkonfiguration aus<html>");
      view.setDefaultBackgroundComboBoxEngines();
      view.setDefaultBackgroundComboBoxTransmissions();
      view.setDefaultBackgroundComboBoxSeats();

      selectedConfiguration.setModel(null);
      selectedConfiguration.setEngine(null);
      selectedConfiguration.setTransmission(null);
      selectedConfiguration.setSeats(null);
      selectedConfiguration.setPrice(null);
    }
  }
}
