package presentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame {

  public View() {

    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
    panel.setLayout(new GridBagLayout());
    panel.setBackground(new Color(206, 200, 200));

    Font fontBasic = new Font("Verdana", 1, 12);
    Font fontBig = new Font("Verdana", 1, 15);

    JLabel header = new JLabel("Car configuration");
    header.setFont(new Font("Verdana", 1, 20));
    JLabel option = new JLabel("Option: ");
    option.setFont(fontBasic);
    JLabel modell = new JLabel("Modell: ");
    modell.setFont(fontBasic);
    String comboBoxListeModell[] = {"", "Audi", "Passat", "Opel"};        // Later on it should pick all options from the model
    JComboBox comboBoxModell = new JComboBox(comboBoxListeModell);        // empty Option is important to implement
    JLabel motor = new JLabel("Motor: ");
    motor.setFont(fontBasic);
    String comboBoxListeMotor[] = {"", "Audi", "Passat", "Opel"};
    JComboBox comboBoxMotor = new JComboBox(comboBoxListeMotor);
    JLabel getriebe = new JLabel("Getriebe: ");
    getriebe.setFont(fontBasic);
    String comboBoxListeGetriebe[] = {"", "Audi", "Passat", "Opel"};
    JComboBox comboBoxGetriebe = new JComboBox(comboBoxListeGetriebe);
    JLabel sitze = new JLabel("Sitze: ");
    sitze.setFont(fontBasic);
    String comboBoxListeSitze[] = {"", "Audi", "Passat", "Opel"};
    JComboBox comboBoxSitze = new JComboBox(comboBoxListeSitze);
    JLabel preis = new JLabel("Preis: ");
    preis.setFont(fontBig);
    JLabel preisErgebnis = new JLabel("");
    preis.setFont(fontBig);

    panel.add(header, this.createGridBagContraintsHeader());
    panel.add(option, this.createGridBagContraints(1,1));
    panel.add(modell, this.createGridBagContraints(0,2));
    panel.add(comboBoxModell, this.createGridBagContraints(1,2));
    panel.add(motor, this.createGridBagContraints(0,3));
    panel.add(comboBoxMotor, this.createGridBagContraints(1,3));
    panel.add(getriebe, this.createGridBagContraints(0,4));
    panel.add(comboBoxGetriebe, this.createGridBagContraints(1,4));
    panel.add(sitze, this.createGridBagContraints(0,5));
    panel.add(comboBoxSitze, this.createGridBagContraints(1,5));
    panel.add(preis, this.createGridBagContraints(0,6));
    panel.add(preisErgebnis, this.createGridBagContraints(1,6));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel, BorderLayout.CENTER);
    this.setTitle("Car configuration");
    this.pack();
    this.setSize(300, 300);
    this.setVisible(true);
  }

  private GridBagConstraints createGridBagContraintsHeader() {
    GridBagConstraints c = this.createGridBagContraints(0,0);
    c.gridwidth = 2;
    c.ipady = 30;
    return c;
  }

  private GridBagConstraints createGridBagContraints(int gridx, int gridy) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = gridx;
    c.gridy = gridy;
    return c;
  }
}
