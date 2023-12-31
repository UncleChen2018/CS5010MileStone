package view;

import controller.GameControllerNew;

/**
 * The mock view for test controller.
 */
public class MockGraphView implements GameView {

  private StringBuilder log;

  /**
   * Constructor for MockGraphView.
   * 
   * @param log the log to be checked later.
   */
  public MockGraphView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void drawMap(GameControllerNew controller) {
    log.append("drawMap called").append("\n");
  }

  @Override
  public void displayAddPlayer(GameControllerNew controller) {
    log.append("displayAddPlayer called").append("\n");
  }

  @Override
  public void disPlaySetGameMaxTurn(GameControllerNew controller) {
    log.append("disPlaySetGameMaxTurn called").append("\n");
  }

  @Override
  public void showWelcomeMessage() {
    log.append("showWelcomeMessage called").append("\n");
  }

  @Override
  public void showFarewellMessage() {
    log.append("showFarewellMessage called").append("\n");
  }

  @Override
  public void display() {
    log.append("display called").append("\n");
  }

  @Override
  public Readable getInputSource() {
    log.append("getInputSource called").append("\n");
    return null; // Replace with any appropriate return value
  }

  @Override
  public Appendable getOutputDestination() {
    log.append("getOutputDestination called").append("\n");
    return null; // Replace with any appropriate return value
  }

  @Override
  public boolean requiresGuiOutput() {
    log.append("requiresGuiOutput called").append("\n");
    return true;
  }

  @Override
  public void configureView(GameControllerNew controller) {
    log.append("configureView called").append("\n");
  }

  @Override
  public void upateResult(String result) {
    log.append("upateResult called, result = ").append(result).append("\n");
  }

  @Override
  public void refresh() {
    log.append("refresh called").append("\n");
  }

  @Override
  public void updateStatusLabel() {
    log.append("updateStatusLabel called").append("\n");
  }

  @Override
  public void rest() {
    log.append("rest called").append("\n");
  }

  @Override
  public void showGameEnd(GameControllerNew controller) {
    log.append("showGameEnd called").append("\n");
  }

  // Implement other methods of the GameView interface with logging
}
