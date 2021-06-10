package presentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame {

  private final JLabel preisErgebnis;
  private final JComboBox comboBoxModell;
  private final JComboBox comboBoxMotor;
  private final JComboBox comboBoxGetriebe;
  private final JComboBox comboBoxSitze;

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
    JLabel modell = new JLabel("Modell: ");
    modell.setFont(fontBasic);
    String comboBoxListeModell[] = {null, "Audi", "Passat", "Opel"};        // Later on it should pick all options from the model
    comboBoxModell = new JComboBox(comboBoxListeModell);        // empty Option is important to implement
    JLabel motor = new JLabel("Motor: ");
    motor.setFont(fontBasic);
    String comboBoxListeMotor[] = {null, "Audi", "Passat", "Opel"};
    comboBoxMotor = new JComboBox(comboBoxListeMotor);
    JLabel getriebe = new JLabel("Getriebe: ");
    getriebe.setFont(fontBasic);
    String comboBoxListeGetriebe[] = {null, "Audi", "Passat", "Opel"};
    comboBoxGetriebe = new JComboBox(comboBoxListeGetriebe);
    JLabel sitze = new JLabel("Sitze: ");
    sitze.setFont(fontBasic);
    String comboBoxListeSitze[] = {null, "Audi", "Passat", "Opel"};
    comboBoxSitze = new JComboBox(comboBoxListeSitze);
    JLabel preis = new JLabel("Preis: ");
    preis.setFont(fontBig);
    preisErgebnis = new JLabel();
    preisErgebnis.setFont(fontBig);

    panel.add(header, this.createGridBagConstraintsHeader());
    panel.add(option, this.createGridBagConstraints(1, 1));
    panel.add(modell, this.createGridBagConstraints(0, 2));
    panel.add(comboBoxModell, this.createGridBagConstraints(1, 2));
    panel.add(motor, this.createGridBagConstraints(0, 3));
    panel.add(comboBoxMotor, this.createGridBagConstraints(1, 3));
    panel.add(getriebe, this.createGridBagConstraints(0, 4));
    panel.add(comboBoxGetriebe, this.createGridBagConstraints(1, 4));
    panel.add(sitze, this.createGridBagConstraints(0, 5));
    panel.add(comboBoxSitze, this.createGridBagConstraints(1, 5));
    panel.add(preis, this.createGridBagConstraints(0, 6));
    panel.add(preisErgebnis, this.createGridBagConstraints(1, 6));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel, BorderLayout.CENTER);
    this.setTitle("Car configuration");
    this.pack();
    this.setSize(500, 450);
    this.setVisible(true);
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

  public void setPreisErgebnis(int preis) {
    char c = 8364; //Ascii code
    String s = String.valueOf(NumberFormat.getInstance().format(preis)); // Tausender-Punkt
    preisErgebnis.setText(s + " " + c);
  }

  public String getComboBoxModell() {
    return (String) comboBoxModell.getSelectedItem();
  }

  public String getComboBoxMotor() {
    return (String) comboBoxMotor.getSelectedItem();
  }

  public String getComboBoxGetriebe() {
    return (String) comboBoxGetriebe.getSelectedItem();
  }

  public String getComboBoxSitze() {
    return (String) comboBoxSitze.getSelectedItem();
  }

  public void removeItemComboBoxMotor(String motor) {
    comboBoxMotor.removeItem(motor);
  }

  public void removeItemComboBoxGetriebe(String getriebe) {
    comboBoxGetriebe.removeItem(getriebe);
  }

  public void removeItemComboBoxSitze(String sitze) {
    comboBoxSitze.addItem(sitze);
  }

  public void addItemComboBoxMotor(String motor) {
    comboBoxMotor.addItem(motor);
  }

  public void addItemComboBoxGetriebe(String getriebe) {
    comboBoxGetriebe.addItem(getriebe);
  }

  public void addItemComboBoxSitze(String sitze) {
    comboBoxSitze.addItem(sitze);
  }

  public void setSelectedItemComboBoxMotor(String motor) {
    comboBoxMotor.setSelectedItem(motor);
  }

  public void setSelectedItemComboBoxGetriebe(String getriebe) {
    comboBoxGetriebe.setSelectedItem(getriebe);
  }

  public void setSelectedItemComboBoxSitze(String sitze) {
    comboBoxSitze.setSelectedItem(sitze);
  }
}
