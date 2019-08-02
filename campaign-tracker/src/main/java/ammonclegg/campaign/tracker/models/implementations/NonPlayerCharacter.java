package ammonclegg.campaign.tracker.models.implementations;

import ammonclegg.campaign.tracker.models.GameCharacter;

import java.util.Objects;

/**
 * @author ammonclegg on 8/2/19.
 */
public class NonPlayerCharacter implements GameCharacter{
  private String name;
  private String type;
  private String description;

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
    NonPlayerCharacter that = (NonPlayerCharacter) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(type, that.type) &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name, type, description);
  }

  @Override
  public String toString() {
    return "NonPlayerCharacter{" +
        "name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
