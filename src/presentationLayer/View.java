package presentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame {

  private final JLabel priceResult;
  private final JComboBox comboBoxModels;
  private final JComboBox comboBoxEngines;
  private final JComboBox comboBoxGears;
  private final JComboBox comboBoxSeats;

  public View() {

    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    panel.setLayout(new GridBagLayout());
    panel.setBackground(new Color(219, 213, 213));

    Font fontBasic = new Font("Verdana", 1, 13);
    Font fontBig = new Font("Verdana", 1, 18);

    JLabel header = new JLabel("Car configuration");
    header.setFont(new Font("Verdana", 1, 25));
    JLabel option = new JLabel("Option: ");
    option.setFont(fontBasic);
    JLabel model = new JLabel("Modell: ");
    model.setFont(fontBasic);
    comboBoxModels = new JComboBox();
    JLabel engine = new JLabel("Motor: ");
    engine.setFont(fontBasic);
    comboBoxEngines = new JComboBox();
    JLabel gear = new JLabel("Getriebe: ");
    gear.setFont(fontBasic);
    comboBoxGears = new JComboBox();
    JLabel seats = new JLabel("Sitze: ");
    seats.setFont(fontBasic);
    comboBoxSeats = new JComboBox();
    JLabel price = new JLabel("Preis: ");
    price.setFont(fontBig);
    priceResult = new JLabel();
    priceResult.setFont(fontBig);

    panel.add(header, this.createGridBagConstraintsHeader());
    panel.add(option, this.createGridBagConstraints(1, 1));
    panel.add(model, this.createGridBagConstraints(0, 2));
    panel.add(comboBoxModels, this.createGridBagConstraints(1, 2));
    panel.add(engine, this.createGridBagConstraints(0, 3));
    panel.add(comboBoxEngines, this.createGridBagConstraints(1, 3));
    panel.add(gear, this.createGridBagConstraints(0, 4));
    panel.add(comboBoxGears, this.createGridBagConstraints(1, 4));
    panel.add(seats, this.createGridBagConstraints(0, 5));
    panel.add(comboBoxSeats, this.createGridBagConstraints(1, 5));
    panel.add(price, this.createGridBagConstraints(0, 6));
    panel.add(priceResult, this.createGridBagConstraints(1, 6));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel, BorderLayout.CENTER);
    this.setTitle("Car configuration");
    this.pack();
    this.setSize(450, 450);
  }

  private GridBagConstraints createGridBagConstraintsHeader() {
    GridBagConstraints c = this.createGridBagConstraints(0, 0);
    c.gridwidth = 2;
    c.ipady = 30;
    return c;
  }

  private GridBagConstraints createGridBagConstraints(int gridx, int gridy) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = gridx;
    c.gridy = gridy;
    c.insets = new Insets(10, 15, 12, 15);
    return c;
  }

  public void setPriceResult(int price) {
    char c = 8364; //Ascii code
    String s = String.valueOf(NumberFormat.getInstance().format(price)); // Tausender-Punkt
    priceResult.setText(s + " " + c);
  }

  public String getComboBoxModels() {
    return (String) comboBoxModels.getSelectedItem();
  }

  public String getComboBoxEngines() {
    return (String) comboBoxEngines.getSelectedItem();
  }

  public String getComboBoxGears() {
    return (String) comboBoxGears.getSelectedItem();
  }

  public String getComboBoxSeats() {
    return (String) comboBoxSeats.getSelectedItem();
  }

  public void removeItemComboBoxEngine(String engine) {
    comboBoxEngines.removeItem(engine);
  }

  public void removeItemComboBoxGear(String gear) {
    comboBoxGears.removeItem(gear);
  }

  public void removeItemComboBoxSeat(String seat) {
    comboBoxSeats.addItem(seat);
  }

  public void addItemComboBoxModel(String model) {
    comboBoxModels.addItem(model);
  }

  public void addItemComboBoxEngine(String engine) {
    comboBoxEngines.addItem(engine);
  }

  public void addItemComboBoxGear(String gear) {
    comboBoxGears.addItem(gear);
  }

  public void addItemComboBoxSeats(String seats) {
    comboBoxSeats.addItem(seats);
  }

  public void setSelectedItemComboBoxEngine(String engine) {
    comboBoxEngines.setSelectedItem(engine);
  }

  public void setSelectedItemComboBoxGear(String gear) {
    comboBoxGears.setSelectedItem(gear);
  }

  public void setSelectedItemComboBoxSeats(String seats) {
    comboBoxSeats.setSelectedItem(seats);
  }

  public void addModelSelectionListener(ItemListener listenForComboBox) {
    comboBoxModels.addItemListener(listenForComboBox);
  }

  public void addEngineSelectionListener(ItemListener listenForComboBox) {
    comboBoxEngines.addItemListener(listenForComboBox);
  }

  public void addGearSelectionListener(ItemListener listenForComboBox) {
    comboBoxGears.addItemListener(listenForComboBox);
  }

  public void addSeatsSelectionListener(ItemListener listenForComboBox) {
    comboBoxSeats.addItemListener(listenForComboBox);
  }
}
