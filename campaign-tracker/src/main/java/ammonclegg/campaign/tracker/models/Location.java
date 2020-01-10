package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ammonclegg on 8/2/19.
 */
@JsonIdentityInfo(
    generator = ObjectIdGenerators.IntSequenceGenerator.class,
    property = "id"
)
public class Location implements GameObject, Comparable<Location> {
  private Campaign campaign;

  @JsonIgnore
  private Integer id;
  private String name;
  private String description = "";
  private Location parentLocation = null;
  private Set<GameCharacter> characters = new TreeSet<>();
  private Set<GameEvent> gameEvents = new HashSet<>();

  @JsonIgnore
  private PropertyChangeSupport support;

  /**
   * For Deserialization/Serialization
   */
  private Location() {
    this( "Unknown");
  }

  public Location (String name) {
    this(null, name);
  }

  public Location(Campaign campaign, String name) {
    this(campaign, name, null);
  }

  public Location(Campaign campaign, String name, Location parentLocation) {
    this.campaign = campaign;
    this.name = name;
    this.parentLocation = parentLocation;
    support = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

  public void removePropertyChangeListener(PropertyChangeListener pcl) {
    support.removePropertyChangeListener(pcl);
  }

  @Override
  public Campaign getCampaign() {
    return campaign;
  }

  @Override
  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  public Integer getId() {
    return id;
  }

  /**
   * Used in serialization/deserialization
   * @param id The id of the location
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

  public Location getParentLocation() {
    return parentLocation;
  }

  public void setParentLocation(Location parentLocation) {
    this.parentLocation = parentLocation;
  }

  public Set<GameCharacter> getCharacters() {
    return characters;
  }

  public void setCharacters(Set<GameCharacter> characters) {
    this.characters = characters;
  }

  public Set<GameEvent> getGameEvents() {
    return gameEvents;
  }

  public void setGameEvents(Set<GameEvent> gameEvents) {
    this.gameEvents = gameEvents;
  }

  @Override
  public int compareTo(Location o) {
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
    Location location = (Location) o;
    return Objects.equals(id, location.id) &&
        Objects.equals(name, location.name) &&
        Objects.equals(description, location.description) &&
        Objects.equals(parentLocation, location.parentLocation) &&
        Objects.equals(characters, location.characters) &&
        Objects.equals(gameEvents, location.gameEvents);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, description, parentLocation, characters, gameEvents);
  }

  @Override
  public String toString() {
    return "Location{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", parentLocation=" + parentLocation +
        ", characters=" + characters +
        ", gameEvents=" + gameEvents +
        '}';
  }
}
