package view;

import controller.GameControllerNew;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ViewModel;

/**
 * The GUI view based on Jframe.
 */
public class GraphView implements GameView {

  // the view model, which is the read only subset of game model.
  ViewModel model;
  private JFrame frame;
  // TODO: maybe world panel should get its own class.
  private JScrollPane worldScrollPane;
  private JScrollPane infoScrollPane;

  private WorldPanel worldlPanel;

  private JPanel playerInfoPanel;
  private JTextArea playerLabel;

  private JPanel resultPanel;
  private JTextArea resultLabel;

  private JMenuItem loadWorld;
  private JMenuItem quitItem;

  private JMenuBar menuBar;

  private JLabel gameStatus;
  private JLabel turnLabel;
  private JLabel currentPlayerLabel;
  private JButton restartButton;

  private Font largerFont = new Font("Arial", Font.BOLD, 16);

  private boolean isGameGoing = false;

  /**
   * The default constructor.
   * 
   * @param model the read only model which support the data toe display.
   */
  public GraphView(ViewModel model) {
    this.model = model;
    initializeFrame();
  }

  @Override
  public void rest() {
    frame.removeAll();
    initializeFrame();
  }

  private void initializeFrame() {
    frame = new JFrame("Kill Doctor Lucky");
    frame.setSize(1200, 800); // Initial size
    frame.setLocationRelativeTo(null); // in the center
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create Jpanel
    frame.setLayout(new GridBagLayout());
    // set the minimum size of the window.
    frame.setMinimumSize(new Dimension(300, 300));
    // JPanel menuBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    createMenuBar();

    createMenuBar();

    createWorldPanel();
    createInfoPanel();

  }

  private void createMenuBar() {
    loadWorld = new JMenuItem("Load World");

    quitItem = new JMenuItem("Quit");

    menuBar = new JMenuBar();
    menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    menuBar.add(loadWorld);

    menuBar.add(quitItem);
    frame.setJMenuBar(menuBar);

  }

  private void createWorldPanel() {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 0.7; // 70% of the horizontal space for worldPanel
    constraints.weighty = 1.0; // Fill the whole height
    constraints.fill = GridBagConstraints.BOTH;

    worldlPanel = new WorldPanel();
    // worldlPanel.setMinimumSize(new Dimension(300, 300)); // Set minimum size

    worldScrollPane = new JScrollPane(worldlPanel);
    // worldScrollPane.setMinimumSize(new Dimension(300, 300));
    worldScrollPane.setBackground(Color.GRAY);
    worldScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    worldScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    frame.add(worldScrollPane, constraints);
  }

  private void createInfoPanel() {

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 0.3; // 30% of the horizontal space for infoPanel
    constraints.fill = GridBagConstraints.BOTH;

    infoScrollPane = new JScrollPane();
    // infoScrollPane.setMinimumSize(new Dimension(90, 300));
    infoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // Create a panel to be added to the JScrollPane's viewport
    JPanel infoPanelView = new JPanel();
    infoPanelView.setLayout(new GridLayout(3, 1)); // Two rows (top and bottom)
    infoPanelView.setBackground(Color.GREEN);

    infoPanelView.add(createGameStatusPanel());
    infoPanelView.add(createPlayerInfoPanel());
    infoPanelView.add(createResultPanel());

    // Set the view component for the JScrollPane
    infoScrollPane.setViewportView(infoPanelView);
    frame.revalidate();

    frame.add(infoPanelView, constraints);

  }

  // TODO: there should be status that is passed from the controller,
  private JScrollPane createGameStatusPanel() {
    // Create a panel for game status
    JPanel gameStatusPanel = new JPanel();
    gameStatusPanel.setBackground(Color.WHITE);
    gameStatusPanel.setLayout(new BoxLayout(gameStatusPanel, BoxLayout.Y_AXIS));

    // Add components to the game status panel
    gameStatus = new JLabel("Game not begin yet");
    gameStatus.setFont(largerFont);
    gameStatusPanel.add(gameStatus);

    // Add components to the game status panel
    turnLabel = new JLabel("Turn not begin");
    turnLabel.setFont(largerFont);
    gameStatusPanel.add(turnLabel);

    currentPlayerLabel = new JLabel("Not any players yet.");
    currentPlayerLabel.setFont(largerFont);
    gameStatusPanel.add(currentPlayerLabel);

    // Add a restart button only when game
    restartButton = new JButton("Restart");
    gameStatusPanel.add(restartButton);
    restartButton.setVisible(false); // Initially invisible

    // Add a manual area at the bottom of the status panel.

    JLabel spacingLabel = new JLabel("  ");
    spacingLabel.setAlignmentY(Component.TOP_ALIGNMENT);
    spacingLabel.setPreferredSize(new Dimension(spacingLabel.getPreferredSize().width, 20));
    gameStatusPanel.add(spacingLabel);
    
    
    addMenuItem(gameStatusPanel, "Manual");
    addMenuItem(gameStatusPanel, "Right click: move");
    addMenuItem(gameStatusPanel, "Press Q: pick up an item");
    addMenuItem(gameStatusPanel, "Press W: look around");
    addMenuItem(gameStatusPanel, "Press E: teleport pet");
    addMenuItem(gameStatusPanel, "Press A: make an attack attempt");

    // Wrap the gameStatusPanel in a JScrollPane
    JScrollPane gameStatusScrollPane = new JScrollPane(gameStatusPanel);
    gameStatusScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    gameStatusScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    gameStatusScrollPane
        .setPreferredSize(new Dimension(200, gameStatusScrollPane.getPreferredSize().height));

    return gameStatusScrollPane;
  }

  private void addMenuItem(JPanel panel, String text) {
    // Add the actual menu item
    JLabel menuItemLabel = new JLabel(text);
    menuItemLabel.setFont(largerFont.deriveFont(Font.BOLD)); // Customize the style
    menuItemLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    menuItemLabel.setOpaque(true);
    menuItemLabel.setForeground(new Color(135, 206, 250));

    panel.add(menuItemLabel);
  }

  private JPanel createPlayerInfoPanel() {
    playerInfoPanel = new JPanel(new BorderLayout());
    playerInfoPanel.setBackground(Color.LIGHT_GRAY);
    playerLabel = new JTextArea("Player Information");
    playerLabel.setFont(largerFont);
    playerLabel.setEditable(false);

    playerLabel.setFocusable(false);

    JScrollPane playerScrollPane = new JScrollPane(playerLabel);
    playerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    playerScrollPane
        .setPreferredSize(new Dimension(200, playerScrollPane.getPreferredSize().height));

    playerInfoPanel.add(playerScrollPane, BorderLayout.CENTER);
    return playerInfoPanel;

  }

  private JPanel createResultPanel() {
    resultPanel = new JPanel(new BorderLayout());
    resultPanel.setBackground(Color.WHITE);
    resultLabel = new JTextArea("World Information");
    resultLabel.setFont(largerFont);
    resultLabel.setEditable(false);
    // not take focus from the world panel.
    resultLabel.setFocusable(false);

    JScrollPane resultScrollPane = new JScrollPane(resultLabel);
    resultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    resultScrollPane
        .setPreferredSize(new Dimension(200, resultScrollPane.getPreferredSize().height));

    resultPanel.add(resultScrollPane, BorderLayout.CENTER);

    return resultPanel;

  }

  private WorldPanel.RoomRect getClickedRoomRect(Point mousePoint) {
    for (WorldPanel.RoomRect room : worldlPanel.getStoredRoomRect()) {
      if (room.containsPoint(mousePoint)) {
        return room;
      }
    }
    return null;
  }

  // draw the world based on the model.
  @Override
  public void drawMap(GameControllerNew controller) {

    worldlPanel.removeAll();
    worldlPanel.setModel(model);
    worldlPanel.getRoomRect(model);
    worldlPanel.revalidate();
    worldlPanel.repaint();
    worldlPanel.addMouseListener(new MouseAdapter() {
      private boolean dialogShown = false;

      @Override
      public void mouseClicked(MouseEvent e) {
        Point mousePoint = e.getPoint();

        if (e.getButton() == MouseEvent.BUTTON1) {
          handleLeftClick(mousePoint);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
          handleRightClick(mousePoint);
        }
      }

      private void handleLeftClick(Point mousePoint) {
        WorldPanel.RoomRect clickedRoom = getClickedRoomRect(mousePoint);
        if (clickedRoom != null) {
          resultLabel.setText(model.queryRoomDetails(clickedRoom.getIndex()));
        }

        if (worldlPanel.targetMark.containsPoint(mousePoint)) {
          playerLabel.setText(model.queryTargetDetails());
        }

        for (int i = 0; i < model.getPlayerCount(); i++) {
          if (worldlPanel.playerMarkList.get(i).containsPoint(mousePoint)) {
            playerLabel.setText(model.queryPlayerDetails(i));
          }
        }
      }

      private void handleRightClick(Point mousePoint) {
        if (!dialogShown) { // Check if the dialog has not been shown
          WorldPanel.RoomRect clickedRoom = getClickedRoomRect(mousePoint);
          if (clickedRoom != null && !model.isGameOverWithWinner()
              && !model.isGameOverWithMaxTurn()) {

            int toMove = clickedRoom.getIndex();
            int base = model.getPlayerLocation(model.getCurrentPlayer());
            if (!model.isNeighbor(toMove, base)) {
              JOptionPane.showMessageDialog(frame, "You must choose a neighbor room to move.",
                  "Invalid Move", JOptionPane.WARNING_MESSAGE);
            }

            else {
              int option = JOptionPane.showConfirmDialog(frame,
                  "Do you want to move to Room " + clickedRoom.getIndex() + "?\n\n"
                      + model.getRoomName(clickedRoom.getIndex()),
                  "Move to Room", JOptionPane.YES_NO_OPTION);

              if (option == JOptionPane.YES_OPTION) {
                String result = controller.processPlayerCommand("moveto", clickedRoom.getIndex());
                resultLabel.setText(result);
              }
            }
            dialogShown = true;
            // Schedule a timer to reset the flag after a certain time (e.g., 2 seconds)
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
              @Override
              public void run() {
                dialogShown = false; // Reset the flag after the specified time
                timer.cancel(); // Cancel the timer
              }
            }, 200); // 200 milliseconds = 0.2 seconds
          }

        }
      }
    });

    worldlPanel.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        Point mousePoint = e.getPoint();
        String tooltipText = null;
        for (WorldPanel.RoomRect room : worldlPanel.getStoredRoomRect()) {
          if (room.containsPoint(mousePoint)) {
            tooltipText = "Room " + room.getIndex() + ": " + model.getRoomName(room.getIndex());
            break;
          }
        }
        worldlPanel.setToolTipText(tooltipText);
      }
    });

    worldlPanel.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        // controller.handleKeyPress(e);

        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();

        System.out.println("Key Pressed - Code: " + keyCode + ", Char: " + keyChar);

      }

      @Override
      public void keyReleased(KeyEvent e) {
        // handleKeyRelease(e);

        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();

        System.out.println("Key Released - Code: " + keyCode + ", Char: " + keyChar);
      }
    });

    worldlPanel.setFocusable(true); // Make sure the panel is focused to receive key events
    worldlPanel.requestFocusInWindow();

  }

  // wait the controller's call to update the panel
  // that is also when game begins

  @Override
  public void updateStatusLabel() {
    int currentTurn = model.getCurrentTurn();
    restartButton.setVisible(true);

    if (model.isGameOverWithWinner()) {
      // here make the turn freeze to display the player.
      gameStatus.setText("Game Over, Target is killed");
      turnLabel.setText(String.format("Turn ends in: %d", currentTurn));
      int winnerId = model.getWinner();
      currentPlayerLabel
          .setText(String.format("Currnt Player: %s", model.getPlayerString(winnerId)));
    } else {
      if (model.getCurrentTurn() == model.getMaxTurn()) {
        gameStatus.setText("Game Over, Target wins");
        turnLabel.setText(String.format("Turn ends in: %d", currentTurn));
        currentPlayerLabel.setText("No player can take turns");
      } else {
        int currentPlayer = model.getCurrentPlayer();
        gameStatus.setText("Wait for the next move");
        turnLabel.setText(String.format("Turn: %d (%d action(s) left)", currentTurn + 1,
            model.getMaxTurn() - currentTurn));
        currentPlayerLabel
            .setText(String.format("Currnt Player: %s", model.getPlayerString(currentPlayer)));
      }
    }

    playerLabel.setText(model.queryPlayerDetails(model.getCurrentPlayer()));

    refresh();

  }

  @Override
  public void showGameEnd(GameControllerNew controller) {
    int option = JOptionPane.showConfirmDialog(frame, "The game has ended. Do you want to restart?",
        "Game Over", JOptionPane.YES_NO_OPTION);

    if (option == JOptionPane.YES_OPTION) {
      controller.loadWorldFile(null);

    }
    refresh();

  }

  @Override
  public void displayAddPlayer(GameControllerNew controller) {
    JOptionPane.showMessageDialog(frame,
        "You must create at least one player before the game begins.", "No Players",
        JOptionPane.INFORMATION_MESSAGE);
    PlayerCreationDialog dialog = new PlayerCreationDialog(frame, model, controller);
    dialog.setVisible(true);

  }

  @Override
  public void disPlaySetGameMaxTurn(GameControllerNew controller) {
    JOptionPane.showMessageDialog(frame, "You must set the game Max Turn before the game begins",
        "Max Turn not Set", JOptionPane.INFORMATION_MESSAGE);
    SetMaxTurnDialog dialog = new SetMaxTurnDialog(frame, model, controller);
    dialog.setVisible(true);

  }

  @Override
  public void configureView(GameControllerNew controller) {
    loadWorld.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser("./");

        // Create a file filter to show only .txt files
        FileFilter txtFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
        fileChooser.setFileFilter(txtFilter);

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
          // User selected a file
          File selectedFile = fileChooser.getSelectedFile();
          // Assuming you have a reference to your GameController
          if (controller != null) {

            controller.loadWorldFile(selectedFile.getPath());
            // System.out.println(model.getWorldName());
          }
        }
      }
    });

    quitItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.exitGame();
      }
    });

    restartButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int option = JOptionPane.showConfirmDialog(frame,
            "Are you sure you want to restart the game?\n"
                + "This will reset players and max turns.",
            "Restart Game", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
          // User clicked Yes, restart the game
          controller.loadWorldFile(null);

          frame.repaint();
        }
      }
    });

  }

  @Override
  public void showWelcomeMessage() {
    String welcomeMessage = "Welcome to the Game!\n\n" + "This game was created by Eric Chen.\n"
        + "It is based on Java's Swing GUI library.\n" + "Enjoy playing!";
    JOptionPane.showMessageDialog(frame, welcomeMessage, "Welcome",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showFarewellMessage() {
    JOptionPane.showMessageDialog(frame, "Thank you for playing the game!", "Farewell",
        JOptionPane.INFORMATION_MESSAGE);
  }

  // make the GUI visible
  @Override
  public void display() {
    // frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void refresh() {
    frame.repaint();

  }

  @Override
  public Readable getInputSource() {
    return null;
  }

  @Override
  public Appendable getOutputDestination() {
    return null;
  }

  @Override
  public boolean requiresGuiOutput() {
    return true;
  }

}
