package model;

/**
 * The pet class, a special moving character with unique moving logic.
 */
public class Pet extends MovableCharacter {

  public Pet(String name, int location) {
    super(name, location);
  }

  /**
   * Generates a formatted string containing the details of the pet.
   *
   * @return The details of the pet.
   */
  public String querryDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Target's pet: ").append(this).append("\n").append("Location: ")
        .append(location).append("\n");
    return stringBuilder.toString();
  }

}
