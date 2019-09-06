package ammonclegg.campaign.tracker.models.implementations;

import ammonclegg.campaign.tracker.models.GameCharacter;

import java.util.Objects;

/**
 * @author ammonclegg on 8/2/19.
 */
public class PlayerCharacter implements GameCharacter {
  private String name;
  private String description;

  @Override
  public String getName() {

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PlayerCharacter that = (PlayerCharacter) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    return "PlayerCharacter{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
