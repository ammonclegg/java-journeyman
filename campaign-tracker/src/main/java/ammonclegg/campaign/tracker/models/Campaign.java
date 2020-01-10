package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.collections.FXCollections;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * @author ammonclegg on 8/2/19.
 */
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "name"
)
public class Campaign {
  // Constants for Property change listeners
  public static final String CHARACTERS = "characters";
  public static final String LOCATIONS = "locations";
  public static final String DESCRIPTION = "description";
  public static final String NAME = "name";


  private String name;
  private String description;
  private List<Location> locations = new ArrayList<>();
  private List<GameCharacter> characters = new ArrayList<>();

  @JsonIgnore
  private PropertyChangeSupport support;

  public Campaign() {
    support = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

  public void removePropertyChangeListener(PropertyChangeListener pcl) {
    support.removePropertyChangeListener(pcl);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    support.firePropertyChange(NAME, this.name, name);
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    support.firePropertyChange(DESCRIPTION, this.description, description);
    this.description = description;
  }

  public List<Location> getLocations() {
    return locations;
  }

  /**
   * Used for serialization/deserialization
   * @param locations
   */
  void setLocations(List<Location> locations) {
    support.firePropertyChange(LOCATIONS, this.locations, locations);
    for (Location location: locations) {
      location.setCampaign(this);
    }
    this.locations = FXCollections.observableList(locations);
  }

  public void addLocation(Location location) {
    List<Location> old = new ArrayList<>(locations);
    location.setCampaign(this);
    locations.add(location);
    support.firePropertyChange(LOCATIONS, old, locations);
  }

  public List<GameCharacter> getCharacters() {
    return characters;
  }

  /**
   * Used for serialization/deserialization
   * @param characters
   */
  void setCharacters(List<GameCharacter> characters) {
    support.firePropertyChange(CHARACTERS, this.characters, characters);
    for (GameCharacter character: characters) {
      character.setCampaign(this);
    }
    this.characters = characters;
  }

  public void addCharacter(GameCharacter character) {
    List<GameCharacter> old = new ArrayList<>(characters);
    character.setCampaign(this);
    characters.add(character);
    support.firePropertyChange(CHARACTERS, old, characters);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Campaign campaign = (Campaign) o;
    return Objects.equals(name, campaign.name) &&
        Objects.equals(description, campaign.description) &&
        Objects.equals(locations, campaign.locations) &&
        Objects.equals(characters, campaign.characters);
  }

  @Override
  public int hashCode() {

    return Objects.hash( name, description, locations, characters);
  }

  @Override
  public String toString() {
    return "Campaign{" +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", locations=" + locations +
        ", characters=" + characters +
        '}';
  }
}
