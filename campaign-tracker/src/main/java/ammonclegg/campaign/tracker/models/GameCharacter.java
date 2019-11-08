package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.Observable;

/**
 * @author ammonclegg on 8/2/19.
 */
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public abstract class GameCharacter implements GameObject, Comparable<GameCharacter> {
  private Integer id;
  private String name;
  private String description = "";

  private Campaign campaign;

  private PropertyChangeSupport support;

  /**
   * For Deserialization/Serialization
   */
  private GameCharacter() {
    this("Unknown", null);
  }

  public GameCharacter(String name, Campaign campaign) {
    this.name = name;
    this.campaign = campaign;
    support = new PropertyChangeSupport(this);
  }

  @Override
  public Campaign getCampaign() {
    return campaign;
  }

  @Override
  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener pcl) {
    support.removePropertyChangeListener(pcl);
  }

  public Integer getId() {
    return id;
  }

  /**
   * Used in serialization/deserialization
   * @param id The id of the character
   */
  private void setId(Integer id) {
    this.id = id;
  }

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
  public int compareTo(GameCharacter o) {
    int compareResults = getName().compareTo(o.getName());
    if (compareResults != 0) {
      return compareResults;
    }
    return description.compareTo(o.description);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GameCharacter that = (GameCharacter) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(name, that.name) &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, description);
  }

  @Override
  public String toString() {
    return "GameCharacter{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", campaign=" + campaign +
        '}';
  }
}
