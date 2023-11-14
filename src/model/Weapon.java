package model;

/**
 * Items in the world. Store its damage, name, and room index where it's now in.
 * Items can also be taken by a player, who becomes its owner, and the location
 * is set to null for its no longer belong to certain space.
 */
public class Weapon implements Item {
  private final int itemId;
  private final int itemDamage;
  private final String itemName;
  private int storedLocation;
  private Player owner;

  /**
   * Initializes a weapon with the given attributes.
   *
   * @param itemId            The unique identifier for the item.
   * @param itemName          The name of the item.
   * @param itemDamage        The damage value of the item.
   * @param itemLocationIndex The index of the room where the item is located.
   */
  public Weapon(int itemId, String itemName, int itemDamage, int itemLocationIndex) {
    this.itemId = itemId;
    this.itemDamage = itemDamage;
    this.itemName = itemName;
    this.storedLocation = itemLocationIndex;
    this.owner = null; // No one owns it at the start.
  }

  /**
   * Generates a formatted string containing the details of the item.
   *
   * @return The details of the item.
   */
  public String queryDetails() {
    if (owner != null) {
      return String.format("Item [itemId = %d, itemName = %s, itemDamage = %d, owner = \"%s\"]",
          itemId, itemName, itemDamage, owner.getName());

    } else {
      return String.format(
          "Item [itemId = %d, itemName = %s, itemDamage = %d, storedLocation = %s]", itemId,
          itemName, itemDamage, storedLocation);
    }
  }

  
  /** 
   * @return String
   */
  @Override
  public String toString() {
    return String.format("No.%d \"%s\" Damage:%d", itemId, itemName, itemDamage);
  }

  public int getItemDamage() {
    return itemDamage;
  }

  public String getItemName() {
    return itemName;
  }

  public int getStoredLoacation() {
    return storedLocation;
  }

  public void setStoredLoacation(int index) {
    this.storedLocation = index;
  }

  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  /**
   * Generates a formatted string containing the details of the item's location.
   *
   * @return The details of the item's location.
   */

  public String querryLocationDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("-------------------Item DETAILS-------------------\n");
    stringBuilder.append("Item: ").append(this).append("\n").append("Damage: ").append(itemDamage)
        .append("\n").append("Location: ")
        .append(storedLocation != -1 ? storedLocation : "Be Taken").append("\n").append("Owner: ")
        .append(owner).append("\n");

    return stringBuilder.toString();
  }

  public int getItemId() {
    return this.itemId;
  }

}
