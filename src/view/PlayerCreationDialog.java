package view;

import controller.GameControllerNew;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.ViewModel;

/**
 *  The dialog to create a player.
 */
public class PlayerCreationDialog extends JDialog {

  private static final long serialVersionUID = 4080458570669687911L;
  private static final int DEFAULT_CAPACITY = 2;
  private JTextField playerNameField;
  private JTextField initialLocationField;
  private JComboBox<Integer> itemCapacityField;
  private JComboBox<String> controlModeComboBox;
  private JButton addButton;
  private ViewModel model;

  /**
   * Constructor for PlayerCreationDialog.
   * @param parentFrame the parent frame
   * @param model the view model
   * @param controller the game controller
   */
  public PlayerCreationDialog(JFrame parentFrame, ViewModel model, GameControllerNew controller) {
    super(parentFrame, "Create Player", true);
    this.model = model;
    initComponents();
    addComponents();
    addListeners(controller);
    setInputVerifier();
    pack();
    setLocationRelativeTo(parentFrame);
  }

  private void initComponents() {
    playerNameField = new JTextField();
    initialLocationField = new JTextField();
    Integer[] capacities = { 1, 2, 3, 4, 5, 6 };
    itemCapacityField = new JComboBox<Integer>(capacities);
    itemCapacityField.setSelectedItem(DEFAULT_CAPACITY);
    controlModeComboBox = new JComboBox<>(new String[] { "HUMAN", "COMPUTER" });
    addButton = new JButton("Add Player");

    // Set initial width for text fields
    playerNameField.setColumns(15);
    initialLocationField.setColumns(15);

  }

  private void addComponents() {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);

    // Label and Text Field for Player Name
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(new JLabel("Player Name:"), constraints);

    constraints.gridx = 1;
    add(playerNameField, constraints);

    // Label and Text Field for Initial Location
    constraints.gridx = 0;
    constraints.gridy = 1;
    add(new JLabel("Initial Location:"), constraints);

    constraints.gridx = 1;
    add(initialLocationField, constraints);

    // Label and Text Field for Item Capacity
    constraints.gridx = 0;
    constraints.gridy = 2;
    add(new JLabel("Item Capacity:"), constraints);

    constraints.gridx = 1;
    add(itemCapacityField, constraints);

    // Label and Combo Box for Control Mode
    constraints.gridx = 0;
    constraints.gridy = 3;
    add(new JLabel("Control Mode:"), constraints);

    constraints.gridx = 1;
    add(controlModeComboBox, constraints);

    // Add Button
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    add(addButton, constraints);
  }

  private void addListeners(GameControllerNew controller) {
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean addedSuccessfully = controller.setNewPlayer(getPlayerName(), getInitialLocation(),
            getItemCapacity(), getControlMode());
        if (addedSuccessfully) {
          int option = JOptionPane.showConfirmDialog(PlayerCreationDialog.this,
              String.format("Player added successfully! There is/are %d player(s) now. "
                  + "Do you want to add more players?", model.getPlayerCount()),
              "Success", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

          if (option == JOptionPane.YES_OPTION) {
            // Clear input fields
            playerNameField.setText("");
            initialLocationField.setText("");
            itemCapacityField.setSelectedItem(DEFAULT_CAPACITY);
          } else {
            // Hide the dialog if user chooses not to add more players
            setVisible(false);
            dispose();
          }
        } else {
          // Handle the case where adding the player was not successful
          JOptionPane.showMessageDialog(PlayerCreationDialog.this,
              "Failed to add player. Please check the input.", "Error", JOptionPane.ERROR_MESSAGE);
        }

      }
    });
  }

  private void setInputVerifier() {
    playerNameField.setInputVerifier(new NonEmptyStringVerifier());
    initialLocationField.setInputVerifier(new LocationVerifier());

  }

  private class LocationVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
      JTextField textField = (JTextField) input;
      String text = textField.getText().trim();

      if (text.isEmpty()) {
        showError("Location cannot be empty.");
        return false;
      }
      try {
        int location = Integer.parseInt(text);
        if (location >= 0 && location < model.getRoomCount()) {
          return true;
        } else {
          showError(String.format("Location must be between 0 and %d.", model.getRoomCount() - 1));
          return false;
        }
      } catch (NumberFormatException e) {
        showError("Invalid input. Please enter a valid integer.");
        return false; // Not a valid integer
      }
    }

    private void showError(String message) {
      JOptionPane.showMessageDialog(PlayerCreationDialog.this, message, "Input Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private class NonEmptyStringVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
      JTextField textField = (JTextField) input;
      String text = textField.getText().trim();

      if (!text.isEmpty()) {
        return true; // Valid input
      } else {
        showError("Name cannot be empty string.");
        return false;
      }
    }

    private void showError(String message) {
      JOptionPane.showMessageDialog(PlayerCreationDialog.this, message, "Input Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * get player name.
   * @return player name
   */
  public String getPlayerName() {
    return playerNameField.getText().trim();
  }

  /**
   * get initial location.
   * @return initial location
   */
  public int getInitialLocation() {
    return Integer.parseInt(initialLocationField.getText().trim());
  }

  /**
   * get item capacity.
   * @return item capacity
   */
  public int getItemCapacity() {
    return (Integer) itemCapacityField.getSelectedItem();
  }

  /**
   * get control mode.
   * @return control mode
   */
  public String getControlMode() {
    return (String) controlModeComboBox.getSelectedItem();
  }

}
