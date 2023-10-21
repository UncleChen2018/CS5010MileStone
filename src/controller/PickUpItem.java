package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

public class PickUpItem extends TurnBaseCommand {
  private int playerId;

  public PickUpItem(int playerId) {
    super();
    this.playerId = playerId;
  }

  @Override
  public void execute(GameModel model, Scanner scan, Appendable out) throws IOException {
    while (true) {
      int curLocation = model.getPlayerLocation(playerId);
      out.append(model.queryRoomItem(curLocation));
      out.append("Enter the item  you want to pick up\n");
      String line = scan.nextLine().trim();
      try {
        int location = Integer.parseInt(line);
        int playerLocation = model.getPlayerLocation(playerId);
        if (model.isNeighbor(location, playerLocation)) {
          model.setPlayerLocation(playerId, location);
          out.append("Move to neighbor successfully.\n");
          turnEnd();
          break;
        } else {
          out.append("Not a valid neighbor, move failed,\n");
        }

      } catch (NumberFormatException e) {
        out.append("Wrong format for an integer, try gain.\n");
      } catch (IndexOutOfBoundsException e) {
        out.append(e.getMessage()).append("\n");
      }
    }

  }

  @Override
  protected void turnBegin() {
    // TODO Auto-generated method stub

  }

  @Override
  protected void turnEnd() {
    // TODO Auto-generated method stub

  }

}
