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

/**
 * This class is used to implement the UI of the application
 */
public class View extends JFrame implements Observer {

  /**
   * Combo box showing all selectable models
   */
  private final JComboBox<StringWrapper> comboBoxModels;

  /**
   * Combo box showing all selectable engines according to the selected model.
   * If no model is selected, all engines are shown.
   */
  private final JComboBox<StringWrapper> comboBoxEngines;

  /**
   * Combo box showing all selectable transmissions according to the selected model.
   * If no model is selected, all transmissions are shown.
   */
  private final JComboBox<StringWrapper> comboBoxTransmissions;

  /**
   * Combo box showing all selectable seats according to the selected model.
   * If no model is selected, all seats are shown.
   */
  private final JComboBox<StringWrapper> comboBoxSeats;

  /**
   * Label that shows the calculated price if a model, engine, transmission and seats is selected in the combo boxes
   */
  private final JLabel priceResult;

  /**
   * Message box that shows input hints to the user.
   * Messages could be: "Eine oder mehrere Optionen sind für dieses Modell nicht verfügbar. Bitte eine neue Option wählen."
   * if the user changed a model after selecting suboptions and those suboptions are not available anymore,
   * or "Bitte wählen Sie eine Fahrzeugkonfiguration aus" if there are still combo boxes empty
   */
  private final JLabel messages;

  /**
   * Button that resets the view to the start configuration (selected model, engine, transmission and seats are null)
   */
  private final JButton resetButton;

  /**
   * Constructor that instantiates the view layout
   */
  public View() {

    // panel layout
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    panel.setLayout(new GridBagLayout());
    panel.setBackground(new Color(232, 226, 226, 255));

    // header
    JLabel header = new JLabel("Fahrzeugkonfiguration", SwingConstants.CENTER);
    header.setFont(new Font("Avenir Next LT Pro", BOLD, 26));

    // message box
    messages = new JLabel("<html>Bitte wählen Sie eine Fahrzeugkonfiguration aus<html>");
    CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK),
        new EmptyBorder(2, 10, 2, 10));
    messages.setBorder(new TitledBorder(compoundBorder, "Hinweise"));
    messages.setFont(new Font("Verdana", BOLD, 13));
    messages.setPreferredSize(new Dimension(250, 100));

    // reset button
    resetButton = new JButton("reset");

    // combo boxes
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

    // price result
    Font fontBig = new Font("Verdana", BOLD, 18);
    JLabel price = new JLabel("Preis: ");
    price.setFont(fontBig);
    priceResult = new JLabel();
    priceResult.setFont(fontBig);

    // Color for pop-up menu
    Color c = new Color(129, 177, 177, 176);
    BasicComboPopup popup = (BasicComboPopup) comboBoxModels.getAccessibleContext().getAccessibleChild(0);
    popup.getList().setSelectionBackground(c);
    BasicComboPopup popup2 = (BasicComboPopup) comboBoxEngines.getAccessibleContext().getAccessibleChild(0);
    popup2.getList().setSelectionBackground(c);
    BasicComboPopup popup3 = (BasicComboPopup) comboBoxTransmissions.getAccessibleContext().getAccessibleChild(0);
    popup3.getList().setSelectionBackground(c);
    BasicComboPopup popup4 = (BasicComboPopup) comboBoxSeats.getAccessibleContext().getAccessibleChild(0);
    popup4.getList().setSelectionBackground(c);

    // fields added to the panel via GridBagLayout
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

    // Frame
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel, BorderLayout.CENTER);
    this.setTitle("Fahrzeugkonfiguration");
    this.pack();
    this.setSize(700, 450);
  }

  /**
   * Creates GridBagConstraints for the header field
   *
   * @return GridBagConstraints for the header
   */
  private GridBagConstraints createGridBagConstraintsHeader() {
    GridBagConstraints c = this.createGridBagConstraints(0, 0);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 3;
    c.ipady = 30;
    return c;
  }

  /**
   * Creates GridBagConstraints for the combo boxes and the according labels
   *
   * @param gridx position at the x-axis
   * @param gridy position at the y-axis
   * @return GridBagConstraints for the combo box / label
   */
  private GridBagConstraints createGridBagConstraints(int gridx, int gridy) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = gridx;
    c.gridy = gridy;
    c.insets = new Insets(10, 15, 12, 15);
    return c;
  }

  /**
   * Creates a GridBagConstraint for the message box
   *
   * @return GridBagConstraints for the message box
   */
  private GridBagConstraints createGridBagConstraintsMessageBox() {
    GridBagConstraints c = this.createGridBagConstraints(2, 1);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridheight = 3;
    return c;
  }

  /**
   * Creates a GridBagConstraint for the reset button
   *
   * @return GridBagConstraints for the reset button
   */
  private GridBagConstraints createGridBagConstraintsResetButton() {
    GridBagConstraints c = this.createGridBagConstraints(2, 6);
    c.fill = GridBagConstraints.HORIZONTAL;
    return c;
  }

  /**
   * Sets the price label to the calculated price or to null if not all combo boxes are set.
   * Adds the euro sign and the thousands separator to the number
   *
   * @param price calculated price for the selected options in the combo boxes or null if not all combo boxes are set
   */
  public void setPriceResult(Integer price) {
    if (price != null) {
      char c = 8364; //Ascii code for euro sign
      String s = NumberFormat.getInstance().format(price); // thousands separator
      priceResult.setText(s + " " + c);
    } else if (priceResult.getText() != null) {
      priceResult.setText(null);
    }
  }

  /**
   * Sets the label messages to a text
   *
   * @param text to be shown in the label 'messages'
   */
  public void setMessageText(String text) {
    messages.setText(text);
  }

  /**
   * Checks if the label 'messages' is empty or not.
   * If it is empty, it opens the possibility to set a new content into it.
   *
   * @return true if the content of the label 'messages' is null or empty. Else returns false.
   */
  public boolean isMessageBoxEmpty() {
    return messages.getText() == null || messages.getText().isEmpty();
  }

  /**
   * Changes the background color of the combo box 'comboBoxEngines' to the default color.
   * The background color of the combo box 'comboBoxModels' is never changed,
   * so it picks the background color from there
   */
  public void setDefaultBackgroundComboBoxEngines() {
    this.comboBoxEngines.setBackground(comboBoxModels.getBackground());
  }

  /**
   * Changes the background color of the combo box 'comboBoxTransmissions' to the default color.
   * The background color of the combo box 'comboBoxModels' is never changed, so it picks the background color from there
   */
  public void setDefaultBackgroundComboBoxTransmissions() {
    this.comboBoxTransmissions.setBackground(comboBoxModels.getBackground()); //default color

  }

  /**
   * Changes the background color of the combo box 'comboBoxSeats' to the default color.
   * The background color of the combo box 'comboBoxModels' is never changed, so it picks the background color from there
   */
  public void setDefaultBackgroundComboBoxSeats() {
    this.comboBoxSeats.setBackground(comboBoxModels.getBackground()); //default color
  }

  /**
   * Adds an item listener to the combo box 'comboBoxModels'
   *
   * @param listenForComboBox item listener (inner class of the controller)
   */
  public void addModelSelectionListener(ItemListener listenForComboBox) {
    comboBoxModels.addItemListener(listenForComboBox);
  }

  /**
   * Adds an item listener to the combo box 'comboBoxEngines'
   *
   * @param listenForComboBox item listener (inner class of the controller)
   */
  public void addEngineSelectionListener(ItemListener listenForComboBox) {
    comboBoxEngines.addItemListener(listenForComboBox);
  }

  /**
   * Adds an item listener to the combo box 'comboBoxTransmissions'
   *
   * @param listenForComboBox item listener (inner class of the controller)
   */
  public void addTransmissionSelectionListener(ItemListener listenForComboBox) {
    comboBoxTransmissions.addItemListener(listenForComboBox);
  }

  /**
   * Adds an item listener to the combo box 'comboBoxSeats'
   *
   * @param listenForComboBox item listener (inner class of the controller)
   */
  public void addSeatsSelectionListener(ItemListener listenForComboBox) {
    comboBoxSeats.addItemListener(listenForComboBox);
  }

  /**
   * Adds an action listener to the button 'resetButton'
   *
   * @param actionListener action listener (inner class of the controller)
   */
  public void addResetButtonActionListener(ActionListener actionListener) {
    resetButton.addActionListener(actionListener);
  }

  /**
   * Called if one of the models 'AvailableConfiguration' or 'SelectedConfiguration' has changed.
   * Updates the combo boxes and the price labels according to the new values
   *
   * @param o   Observable (object of the class 'AvailableConfiguration' or 'SelectedConfiguration')
   * @param arg argument (object of the class 'AvailableConfiguration' or 'SelectedConfiguration')
   */
  public void update(Observable o, Object arg) {
    // ComboBoxes
    if (o instanceof AvailableConfiguration) {
      if (this.comboBoxModels.getItemCount() == 0) {         // --> All Boxes are still empty
        // Setup ComboBoxes
        String[] models = ((AvailableConfiguration) arg).getModels();
        this.comboBoxModels.addItem(new StringWrapper(null));
        for (String m : models) {
          this.comboBoxModels.addItem(new StringWrapper(m));
        }
        String[] engines = ((AvailableConfiguration) arg).getEngines();
        this.comboBoxEngines.addItem(new StringWrapper(null));
        for (String e : engines) {
          this.comboBoxEngines.addItem(new StringWrapper(e));
        }
        String[] transmissions = ((AvailableConfiguration) arg).getTransmissions();
        comboBoxTransmissions.addItem(new StringWrapper(null));
        for (String t : transmissions) {
          this.comboBoxTransmissions.addItem(new StringWrapper(t));
        }
        String[] seats = ((AvailableConfiguration) arg).getSeats();
        comboBoxSeats.addItem(new StringWrapper(null));
        for (String s : seats) {
          this.comboBoxSeats.addItem(new StringWrapper(s));
        }

        //Update ComboBoxes
      } else {
        StringWrapper selectedEngine = (StringWrapper) comboBoxEngines.getSelectedItem();
        StringWrapper selectedTransmission = (StringWrapper) comboBoxTransmissions.getSelectedItem();
        StringWrapper selectedSeats = (StringWrapper) comboBoxSeats.getSelectedItem();

        comboBoxEngines.removeAllItems();
        comboBoxTransmissions.removeAllItems();
        comboBoxSeats.removeAllItems();

        String[] engines = ((AvailableConfiguration) arg).getEngines();
        StringWrapper currentSelectedEngine = null;
        this.comboBoxEngines.addItem(new StringWrapper(null));
        for (String e : engines) {
          StringWrapper eWrapper = new StringWrapper(e);
          this.comboBoxEngines.addItem(eWrapper);
          if (!Objects.equals(selectedEngine, null) && Objects.equals(selectedEngine.toString(), eWrapper.toString())) {
            currentSelectedEngine = eWrapper;
          }
        }
        if (currentSelectedEngine != null) {
          comboBoxEngines.setSelectedItem(currentSelectedEngine);
        } else if (selectedEngine != null && selectedEngine.toString() != null && comboBoxModels.getSelectedItem() != null) {
          comboBoxEngines.setBackground(new Color(217, 50, 50, 163));
          this.setMessageText("<html>Eine oder mehrere gewählte Optionen sind für dieses Modell nicht verfügbar. "
              + "Bitte eine neue Option wählen.<html>");
        }
        String[] transmissions = ((AvailableConfiguration) arg).getTransmissions();
        StringWrapper currentSelectedTransmission = null;
        comboBoxTransmissions.addItem(new StringWrapper(null));
        for (String t : transmissions) {
          StringWrapper tWrapper = new StringWrapper(t);
          this.comboBoxTransmissions.addItem(tWrapper);
          if (!Objects.equals(selectedTransmission, null)
              && Objects.equals(selectedTransmission.toString(), tWrapper.toString())) {
            currentSelectedTransmission = tWrapper;
          }
        }
        if (currentSelectedTransmission != null) {
          comboBoxTransmissions.setSelectedItem(currentSelectedTransmission);
        } else if (selectedTransmission != null && selectedTransmission.toString() != null
            && comboBoxModels.getSelectedItem() != null) {
          comboBoxTransmissions.setBackground(new Color(217, 50, 50, 163));
          this.setMessageText("<html>Eine oder mehrere Optionen sind für dieses Modell nicht verfügbar. "
              + "Bitte eine neue Option wählen.<html>");
        }
        String[] seats = ((AvailableConfiguration) arg).getSeats();
        StringWrapper currentSelectedSeat = null;
        comboBoxSeats.addItem(new StringWrapper(null));
        for (String s : seats) {
          StringWrapper sWrapper = new StringWrapper(s);
          this.comboBoxSeats.addItem(sWrapper);
          if (!Objects.equals(selectedSeats, null) && Objects.equals(selectedSeats.toString(), sWrapper.toString())) {
            currentSelectedSeat = sWrapper;
          }
        }
        if (currentSelectedSeat != null) {
          comboBoxSeats.setSelectedItem(currentSelectedSeat);
        } else if (selectedSeats != null && selectedSeats.toString() != null
            && comboBoxModels.getSelectedItem() != null) {
          comboBoxSeats.setBackground(new Color(217, 50, 50, 163));
          this.setMessageText("<html>Eine oder mehrere gewählte Optionen sind für dieses Modell nicht verfügbar. "
              + "Bitte eine neue Option wählen.<html>");
        }
      }

      // Update Price
    } else if (o instanceof SelectedConfiguration) {
      this.setPriceResult(((SelectedConfiguration) arg).getPrice());

      //the following is important for resetting
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

  /**
   * Inner class StringWrapper <br>
   * Used to save the value 'null' in the comboBoxes.
   * With normal Strings, the item listeners would not be invoked if the value is set to 'null'
   */
  static class StringWrapper {
    final String s;

    /**
     * Casts the String s to a object of StringWrapper containing s
     * Important: Also 'null' is going to be casted into an object of StringWrapper
     * Via the toString method the StringWrapper object can be casted back to a String (if it is not containing null)
     *
     * @param s String to be casted into the StringWrapper
     */
    public StringWrapper(String s) {
      this.s = s;
    }

    /**
     * toString method to cast a StringWrapper object back to the original String
     *
     * @return s
     */
    @Override
    public String toString() {
      return s;
    }
  }

  /**
   * Inner class to add a cell renderer to the combo boxes.
   * Changes the color of the popup menu to a wished color even if the background of the combo box is not default.
   */
  static class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
    public MyCellRenderer() {
      setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

      if (value != null) {
        setText(value.toString());
      } else {
        setText(null);
      }
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