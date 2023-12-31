package model;

import java.util.ArrayList;

/**
 * Represents a player character in the game.
 */
public class Player extends MovableCharacter {
  private final int capacity;
  private ArrayList<Item> itemList;
  private ControlType controlType;
  private int playerId; // every player has unique id from 0

  private enum ControlType {
    HUMAN, COMPUTER
  }

  /**
   * Creates a new player.
   *
   * @param name           The name of the player.
   * @param location       The initial location of the player.
   * @param itemCapacity   The maximum number of items the player can carry.
   * @param isHumanControl True if the player is controlled by a human, false if
   *                       controlled by the computer.
   * @param id             The unique ID of the player.
   */

  public Player(String name, int location, int itemCapacity, boolean isHumanControl, int id) {
    super(name, location);
    capacity = itemCapacity;
    itemList = new ArrayList<Item>(capacity);
    if (isHumanControl) {
      controlType = ControlType.HUMAN;
    } else {
      controlType = ControlType.COMPUTER;
    }
    playerId = id;
  }

  /**
   * Adds an item to the player's inventory.
   *
   * @param item The item to be added.
   */

  public void addItem(Item item) {
    if (itemList.size() < capacity) {
      itemList.add(item);
    }
  }

  /**
   * Removes the specified item from the player's item list.
   *
   * @param item The item to be removed.
   */
  public void removeItem(Item item) {
    itemList.remove(item);
  }

  /**
   * Retrieves the list of items owned by the player.
   *
   * @return An ArrayList of Item objects representing the player's items.
   */
  public ArrayList<Item> getItemList() {
    return itemList;
  }

  /**
   * Switches the player's control type to computer play.
   */
  public void switchToComputerPlay() {
    this.controlType = ControlType.COMPUTER;
  }

  /**
   * Switches the player's control type to human play.
   */
  public void switchToHumanPlay() {
    this.controlType = ControlType.HUMAN;
  }

  /**
   * Checks if the player is a human player.
   *
   * @return {@code true} if the player is a human player, {@code false}
   *         otherwise.
   */
  public boolean isHumanPlayer() {
    return controlType == ControlType.HUMAN;
  }

  /**
   * Checks if the player is a computer player.
   *
   * @return {@code true} if the player is a computer player, {@code false}
   *         otherwise.
   */
  public boolean isComputerPlayer() {
    return controlType == ControlType.COMPUTER;
  }

  /**
   * Returns detailed information about the player.
   *
   * @return A string containing the player's details.
   */

  public String querryDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("-------------------PLAYER DETAILS-------------------\n");
    stringBuilder.append("Player: ").append(this).append("\n").append("Control Type: ")
        .append(controlType).append("\n").append("Location: ").append(location).append("\n")
        .append("Stock|Capacity: ").append(itemList.size()).append("|").append(capacity)
        .append("\n").append("Items: ").append(itemList).append("\n");

    return stringBuilder.toString();
  }

  @Override
  public String toString() {
    return String.format("No.%d \"%s\"", playerId, name);
  }

  /**
   * Checks if the player has reached the maximum item capacity.
   *
   * @return {@code true} if the player has reached the item capacity,
   *         {@code false} otherwise.
   */
  public boolean reachItemCapacity() {
    return itemList.size() == capacity;
  }

  /**
   * Retrieves the unique identifier of the player.
   *
   * @return The player's unique identifier.
   */
  public int getPlayerId() {
    return this.playerId;
  }
}
