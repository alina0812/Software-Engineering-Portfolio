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

public class Controller {

  private final View view;
  private final AvailableConfiguration availableConfiguration;
  private final SelectedConfiguration selectedConfiguration;
  private final ConfigurationService configurationService;

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
    this.view.addGearSelectionListener(new TransmissionComboBoxListener());
    this.view.addSeatsSelectionListener(new SeatsComboBoxListener());
    this.view.addResetButtonActionListener(new ResetButtonListener());

    view.setVisible(true);
  }

  private void calculatePrice() {

    int p = configurationService.calculatePrice(selectedConfiguration.getModel(), selectedConfiguration.getEngine(),
        selectedConfiguration.getTransmission(), selectedConfiguration.getSeats());
    selectedConfiguration.setPrice(p);

  }

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

  // ItemListener for all ComboBoxes

  class ModelComboBoxListener implements ItemListener {

    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setModel((String) e.getItem());
        Controller.this.updateComboBoxes((String) e.getItem());
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

  class EngineComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setEngine((String) e.getItem());
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

  class TransmissionComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        selectedConfiguration.setTransmission((String) e.getItem());
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

  class SeatsComboBoxListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      if (e.getStateChange() == ItemEvent.SELECTED || e.getItem() == null) {
        selectedConfiguration.setSeats((String) e.getItem());
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

  class ResetButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      view.setMessageText("<html>Bitte wählen Sie eine Fahrzeugkonfiguration aus<html>");
      view.setDefaultBackgroundComboBoxEngines();
      view.setDefaultBackgroundComboBoxTransmissions();
      view.setDefaultBackgroundComboBoxSeats();

      selectedConfiguration.setModel("");
      selectedConfiguration.setEngine("");
      selectedConfiguration.setTransmission("");
      selectedConfiguration.setSeats("");
      selectedConfiguration.setPrice(null);
    }
  }
}
