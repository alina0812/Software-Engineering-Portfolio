package presentationLayer;

import static java.awt.Font.BOLD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboPopup;
import presentationLayer.model.AvailableConfiguration;
import presentationLayer.model.SelectedConfiguration;

public class View extends JFrame implements Observer {

  private final JLabel priceResult;
  private final JComboBox<String> comboBoxModels;
  private final JComboBox<String> comboBoxEngines;
  private final JComboBox<String> comboBoxTransmissions;
  private final JComboBox<String> comboBoxSeats;
  private final JLabel messages;
  private final JButton resetButton;

  public View() {

    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    panel.setLayout(new GridBagLayout());
    panel.setBackground(new Color(232, 226, 226, 255));

    JLabel header = new JLabel("Fahrzeugkonfiguration", SwingConstants.CENTER);
    header.setFont(new Font("Avenir Next LT Pro", BOLD, 26));

    messages = new JLabel("<html>Bitte wählen Sie eine Fahrzeugkonfiguration aus<html>");
    CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
        new EmptyBorder(2, 10, 2, 10));
    messages.setBorder(new TitledBorder(compoundBorder, "Hinweise"));
    messages.setFont(new Font("Verdana", BOLD, 13));
    messages.setPreferredSize(new Dimension(250, 100));

    resetButton = new JButton("reset");


    Font fontBasic = new Font("Verdana", BOLD, 13);
    JLabel option = new JLabel("Option: ");
    option.setFont(fontBasic);
    JLabel model = new JLabel("Modell: ");
    model.setFont(fontBasic);
    comboBoxModels = new JComboBox<>();
    comboBoxModels.setPreferredSize(new Dimension(180, 25));
    JLabel engine = new JLabel("Motor: ");
    engine.setFont(fontBasic);
    comboBoxEngines = new JComboBox<>();
    comboBoxEngines.setPreferredSize(new Dimension(180, 25));
    comboBoxEngines.setRenderer(new MyCellRenderer());
    JLabel transmission = new JLabel("Getriebe: ");
    transmission.setFont(fontBasic);
    comboBoxTransmissions = new JComboBox<>();
    comboBoxTransmissions.setPreferredSize(new Dimension(180, 25));
    comboBoxTransmissions.setRenderer(new MyCellRenderer());
    JLabel seats = new JLabel("Sitze: ");
    seats.setFont(fontBasic);
    comboBoxSeats = new JComboBox<>();
    comboBoxSeats.setPreferredSize(new Dimension(180, 25));
    comboBoxSeats.setRenderer(new MyCellRenderer());

    Font fontBig = new Font("Verdana", BOLD, 18);
    JLabel price = new JLabel("Preis: ");
    price.setFont(fontBig);
    priceResult = new JLabel();
    priceResult.setFont(fontBig);

    // Colour for pop-up menu
    Color c = new Color(129, 177, 177, 176);
    BasicComboPopup popup = (BasicComboPopup) comboBoxModels.getAccessibleContext().getAccessibleChild(0);
    popup.getList().setSelectionBackground(c);
    BasicComboPopup popup2 = (BasicComboPopup) comboBoxEngines.getAccessibleContext().getAccessibleChild(0);
    popup2.getList().setSelectionBackground(c);
    BasicComboPopup popup3 = (BasicComboPopup) comboBoxTransmissions.getAccessibleContext().getAccessibleChild(0);
    popup3.getList().setSelectionBackground(c);
    BasicComboPopup popup4 = (BasicComboPopup) comboBoxSeats.getAccessibleContext().getAccessibleChild(0);
    popup4.getList().setSelectionBackground(c);

    panel.add(header, this.createGridBagConstraintsHeader());
    panel.add(messages, this.createGridBagConstraintsMessageBox());
    panel.add(resetButton, this.createGridBagConstraintsResetButton());
    panel.add(option, this.createGridBagConstraints(1, 1));
    panel.add(model, this.createGridBagConstraints(0, 2));
    panel.add(comboBoxModels, this.createGridBagConstraints(1, 2));
    panel.add(engine, this.createGridBagConstraints(0, 3));
    panel.add(comboBoxEngines, this.createGridBagConstraints(1, 3));
    panel.add(transmission, this.createGridBagConstraints(0, 4));
    panel.add(comboBoxTransmissions, this.createGridBagConstraints(1, 4));
    panel.add(seats, this.createGridBagConstraints(0, 5));
    panel.add(comboBoxSeats, this.createGridBagConstraints(1, 5));
    panel.add(price, this.createGridBagConstraints(0, 6));
    panel.add(priceResult, this.createGridBagConstraints(1, 6));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel, BorderLayout.CENTER);
    this.setTitle("Fahrzeugkonfiguration");
    this.pack();
    this.setSize(700, 450);
  }

  private GridBagConstraints createGridBagConstraintsHeader() {
    GridBagConstraints c = this.createGridBagConstraints(0, 0);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 3;
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

  private GridBagConstraints createGridBagConstraintsMessageBox() {
    GridBagConstraints c = this.createGridBagConstraints(2, 1);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridheight = 3;
    return c;
  }

  private GridBagConstraints createGridBagConstraintsResetButton() {
    GridBagConstraints c = this.createGridBagConstraints(2, 6);
    c.fill = GridBagConstraints.HORIZONTAL;
    return c;
  }

  public void setPriceResult(Integer price) {
    if (price != null) {
      char c = 8364; //Ascii code
      String s = NumberFormat.getInstance().format(price); // Tausender-Punkt
      priceResult.setText(s + " " + c);
    } else if (!priceResult.getText().equals("")) {
      priceResult.setText("");
    }
  }

  public void setMessageText(String text) {
    messages.setText(text);
  }

  public boolean isMessageBoxEmpty(){
    return messages.getText() == null || messages.getText().equals("");
  }

  public void setDefaultBackgroundComboBoxEngines() {
    this.comboBoxEngines.setBackground(comboBoxModels.getBackground()); //default color
  }

  public void setDefaultBackgroundComboBoxTransmissions() {
    this.comboBoxTransmissions.setBackground(comboBoxModels.getBackground()); //default color

  }

  public void setDefaultBackgroundComboBoxSeats() {
    this.comboBoxSeats.setBackground(comboBoxModels.getBackground()); //default color
  }


  public void addModelSelectionListener(ItemListener listenForComboBox) {
    comboBoxModels.addItemListener(listenForComboBox);
  }

  public void addEngineSelectionListener(ItemListener listenForComboBox) {
    comboBoxEngines.addItemListener(listenForComboBox);
  }

  public void addGearSelectionListener(ItemListener listenForComboBox) {
    comboBoxTransmissions.addItemListener(listenForComboBox);
  }

  public void addSeatsSelectionListener(ItemListener listenForComboBox) {
    comboBoxSeats.addItemListener(listenForComboBox);
  }

  public void addResetButtonActionListener(ActionListener actionListener) {
    resetButton.addActionListener(actionListener);
  }

  public void update(Observable o, Object arg) {
    System.out.println("Observer reached");
    // ComboBoxes
    if (o instanceof AvailableConfiguration) {
      System.out.println("Arg is AvailableConfiguration");
      if (this.comboBoxModels.getItemCount() == 0) {            // --> All Boxes are still empty
        // Setup ComboBoxes
        String[] models = ((AvailableConfiguration) arg).getModels();
        this.comboBoxModels.addItem("");
        for (String m : models) {
          this.comboBoxModels.addItem(m);
        }
        String[] engines = ((AvailableConfiguration) arg).getEngines();
        this.comboBoxEngines.addItem("");
        for (String e : engines) {
          this.comboBoxEngines.addItem(e);
        }
        String[] transmissions = ((AvailableConfiguration) arg).getTransmissions();
        comboBoxTransmissions.addItem("");
        for (String t : transmissions) {
          this.comboBoxTransmissions.addItem(t);
        }
        String[] seats = ((AvailableConfiguration) arg).getSeats();
        comboBoxSeats.addItem("");
        for (String s : seats) {
          this.comboBoxSeats.addItem(s);
        }
        //Update ComboBoxes
      } else {
        System.out.println("update ComboBoxes in view");

        String selectedEngine = (String) comboBoxEngines.getSelectedItem();
        String selectedTransmission = (String) comboBoxTransmissions.getSelectedItem();
        String selectedSeats = (String) comboBoxSeats.getSelectedItem();


        comboBoxEngines.removeAllItems();
        comboBoxTransmissions.removeAllItems();
        comboBoxSeats.removeAllItems();

        String[] engines = ((AvailableConfiguration) arg).getEngines();
        String currentSelectedEngine = null;
        this.comboBoxEngines.addItem("");
        for (String e : engines) {
          this.comboBoxEngines.addItem(e);
          if (!Objects.equals(selectedEngine, "") && Objects.equals(selectedEngine, e)) {
            currentSelectedEngine = e;
          }
        }
        if (currentSelectedEngine != null) {
          comboBoxEngines.setSelectedItem(currentSelectedEngine);
        } else if (!selectedEngine.equals("") && comboBoxModels.getSelectedItem() != null) {
          comboBoxEngines.setBackground(new Color(217, 50, 50, 163));
          this.setMessageText("<html>Eine oder mehrere gewählte Optionen sind für dieses Modell nicht verfügbar. "
              + "Bitte eine neue Option wählen.<html>");
        }
        String[] transmissions = ((AvailableConfiguration) arg).getTransmissions();
        String currentSelectedTransmission = null;
        comboBoxTransmissions.addItem("");
        for (String t : transmissions) {
          this.comboBoxTransmissions.addItem(t);
          if (!Objects.equals(selectedTransmission, "") && Objects.equals(selectedTransmission, t)) {
            currentSelectedTransmission = t;
          }
        }
        if (currentSelectedTransmission != null) {
          comboBoxTransmissions.setSelectedItem(currentSelectedTransmission);
        } else if (!Objects.equals(selectedTransmission, "") && comboBoxModels.getSelectedItem() != null) {
          comboBoxTransmissions.setBackground(new Color(217, 50, 50, 163));
          this.setMessageText("<html>Eine oder mehrere Optionen sind für dieses Modell nicht verfügbar. "
              + "Bitte eine neue Option wählen.<html>");
        }
        String[] seats = ((AvailableConfiguration) arg).getSeats();
        String currentSelectedSeat = null;
        comboBoxSeats.addItem("");
        for (String s : seats) {
          this.comboBoxSeats.addItem(s);
          if (!Objects.equals(selectedSeats, "") && Objects.equals(selectedSeats, s)) {
            currentSelectedSeat = s;
          }
        }
        if (currentSelectedSeat != null) {
          comboBoxSeats.setSelectedItem(currentSelectedSeat);
        } else if (!Objects.equals(selectedSeats, "") && comboBoxModels.getSelectedItem() != null) {
          comboBoxSeats.setBackground(new Color(217, 50, 50, 163));
          this.setMessageText("<html>Eine oder mehrere gewählte Optionen sind für dieses Modell nicht verfügbar. Bitte eine neue Option wählen.<html>");
        }

      }
      // Update Price
    } else if (o instanceof SelectedConfiguration) {
      System.out.println("Preis eintragen");
      this.setPriceResult(((SelectedConfiguration) arg).getPrice());

      //the following is only important for resetting
      if (!Objects.equals(comboBoxEngines.getSelectedItem(), ((SelectedConfiguration) arg).getEngine())) {
        comboBoxEngines.setSelectedItem(((SelectedConfiguration) arg).getEngine());
      }
      if (!Objects.equals(comboBoxTransmissions.getSelectedItem(), ((SelectedConfiguration) arg).getTransmission())) {
        comboBoxTransmissions.setSelectedItem(((SelectedConfiguration) arg).getTransmission());
      }
      if (!Objects.equals(comboBoxSeats.getSelectedItem(), ((SelectedConfiguration) arg).getSeats())) {
        comboBoxSeats.setSelectedItem(((SelectedConfiguration) arg).getSeats());
      }
      if (!Objects.equals(comboBoxModels.getSelectedItem(), ((SelectedConfiguration) arg).getModel())) {
        comboBoxModels.setSelectedItem(((SelectedConfiguration) arg).getModel());
      }
    }
  }

  static class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
    public MyCellRenderer() {
      setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

      // Empty string would be ignored --> replace with empty ascii character
      if (value == null || value.toString().equals("")) {
        char c = 0;
        value = Character.toString(c);
      }

      // background color overwrites background of comboBox
      setText(value.toString());
      Color background = Color.WHITE;

      JList.DropLocation dropLocation = list.getDropLocation();

      if (isSelected && !(dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index)) {
        background = new Color(129, 177, 177, 176);
      }

      setBackground(background);

      return this;
    }
  }
}