package presentationLayer.model;

import java.util.Observable;

/**
 * This class stores all available configuration data, which are selectable in the combo boxes in the view.
 * I a model is selected in the view, the configuration data `engines, transmissions and seats` show suboptions according to the selected model.
 * If no model is selected in the view, all available options for engines, transmissions and seats are shown.
 * This model uses the observer pattern to update the view, if some options have changed
 */
public class AvailableConfiguration extends Observable {

  /**
   * String array of selectable models
   */
  private String[] models;
  /**
   * String array of selectable engines according to the selected model in the view.
   * If no model is selected, all available engines are shown.
   */
  private String[] engines;
  /**
   * String array of selectable transmissions according to the selected model in the view.
   * If no model is selected, all available transmissions are shown.
   */
  private String[] transmissions;
  /**
   * String array of selectable seats according to the selected model in the view.
   * If no model is selected, all available seats are shown.
   */
  private String[] seats;

  /**
   * This method is only called one time to initiate the combo boxes in the view
   * @param models all available model options, which are going to be shown in the according combo box in the view
   * @param engines all available engine options, which are going to be shown in the according combo box in the view
   * @param transmissions all available transmission options, which are going to be shown in the according combo box in the view
   * @param seats all available seats options, which are going to be shown in the according combo box in the view
   */
  public void init(String[] models, String[] engines, String[] transmissions, String[] seats) {
    this.models = models;
    this.engines = engines;
    this.transmissions = transmissions;
    this.seats = seats;
    this.setChanged();
    this.notifyObservers(this);
  }

  /**
   * This method is called to update the combo boxes in the view with suboptions after a selected model in the view
   * has changed
   * @param engines available engine options according to the selected model in the view.
   *                Shown in the `comboBoxEngines` in the view.
   * @param transmissions available transmission options according to the selected model in the view.
   *                      Shown in the `comboBoxTransmissions` in the view.
   * @param seats available seat options according to the selected model in the view.
   *              Shown in the `comboBoxSeats` in the view.
   */
  public void update(String[] engines, String[] transmissions, String[] seats) {
    this.engines = engines;
    this.transmissions = transmissions;
    this.seats = seats;
    this.setChanged();
    this.notifyObservers(this);
  }

  /**
   * returns all available models
   * @return value of the attribute `models`
   */
  public String[] getModels() {

    return models;
  }

  /**
   * returns available engines according to the selected model in the view
   * @return value of the attribute `engines`
   */
  public String[] getEngines() {

    return engines;
  }

  /**
   * returns available transmissions according to the selected model in the view
   * @return value of the attribute `transmissions`
   */
  public String[] getTransmissions() {

    return transmissions;
  }

  /**
   * returns available seats according to the selected model in the view
   * @return value of the attribute `seats`
   */
  public String[] getSeats() {

    return seats;
  }
}
