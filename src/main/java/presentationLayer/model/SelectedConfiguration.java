package presentationLayer.model;

import java.util.Observable;

/**
 * This model stores the data of the selected options of the combo boxes in the view.
 * If all options are set, the method 'areModelEngineTransmissionSeatsSet' returns true and the price is calculated via
 * the controller and the application layer. Then the price is saved in the attribute 'price'.
 * The class implemented the observer pattern to update the price field in the view if the price has changed.
 *
 * In a later version of this application this class could be used to save the selected car configuration in a database
 */
public class SelectedConfiguration  extends Observable {

  /**
   * Selected model in the combo box 'comboBoxModels' in the view
   */
  private String model;
  /**
   * Selected engine in the combo box 'comboBoxEngines' in the view
   */
  private String engine;
  /**
   * Selected transmission in the combo box 'comboBoxTransmissions' in the view
   */
  private String transmission;
  /**
   * Selected seats in the combo box 'comboBoxSeats' in the view
   */
  private String seats;
  /**
   * Price for the selected model, engine, transmission and seats.
   * Calculated via the controller and application layer.
   * Is at least one item of model, engine, transmission and seats null, the price is null as well
   */
  private Integer price;


  /**
   * returns model
   * @return value of the attribute 'model'
   */
  public String getModel() {
    return model;
  }

  /**
   * sets the attribute 'model'
   * @param model selected model in the view or null
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * returns engine
   * @return value of the attribute 'engine'
   */
  public String getEngine() {
    return engine;
  }

  /**
   * sets the attribute 'engine'
   * @param engine selected engine in the view or null
   */
  public void setEngine(String engine) {
    this.engine = engine;
  }

  /**
   * returns transmission
   * @return value of the attribute 'transmission'
   */
  public String getTransmission() {
    return transmission;
  }

  /**
   * sets the attribute 'transmission'
   * @param transmission selected transmission in the view or null
   */
  public void setTransmission(String transmission) {
    this.transmission = transmission;
  }

  /**
   * returns seats
   * @return value of attribute 'seats'
   */
  public String getSeats() {
    return seats;
  }

  /**
   * sets the attribute 'seats'
   * @param seats selected seats in the view or null
   */
  public void setSeats(String seats) {
    this.seats = seats;
  }

  /**
   * returns price
   * @return value of the attribute 'price'.
   */
  public Integer getPrice() {
    return price;
  }

  /**
   * sets attribute 'price'
   * @param price Calculated price for the selected model, engine, transmission and seats.
   *              If one of the attributes null, the price is null as well
   */
  public void setPrice(Integer price) {
    this.price = price;
    this.setChanged();
    this.notifyObservers(this);
  }

  /**
   * returns true, if the attributes model, engine, transmission and seats have values that are neither null nur empty.
   * Else returns false.
   * @return true or false
   */
  public boolean areModelEngineTransmissionSeatsSet() {
    return this.model != null && !this.model.isEmpty()
        && this.engine != null && !this.engine.isEmpty()
        && this.transmission != null && !this.transmission.isEmpty()
        && this.seats != null && !this.seats.isEmpty();
  }
}
