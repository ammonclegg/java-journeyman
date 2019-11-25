package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ammonclegg on 8/2/19.
 */
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "name"
)
public class Campaign {
  private String name;
  private String description;
  private ObservableList<Location> locations = FXCollections.observableList(new ArrayList<>());
  private ObservableList<GameCharacter> characters = FXCollections.observableList(new ArrayList<>());

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
    support.firePropertyChange("name", this.name, name);
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    support.firePropertyChange("description", this.description, description);
    this.description = description;
  }

  public ObservableList<Location> getLocations() {
    return locations;
  }

  /**
   * Used for serialization/deserialization
   * @param locations
   */
  void setLocations(ObservableList<Location> locations) {
    support.firePropertyChange("locations", this.locations, locations);
    for (Location location: locations) {
      location.setCampaign(this);
    }
    this.locations = locations;
  }

  public void addLocation(Location location) {
    Set<Location> old = new TreeSet<>(locations);
    location.setCampaign(this);
    locations.add(location);
    support.firePropertyChange("locations", old, locations);
  }

  public ObservableList<GameCharacter> getCharacters() {
    return characters;
  }

  /**
   * Used for serialization/deserialization
   * @param characters
   */
  void setCharacters(ObservableList<GameCharacter> characters) {
    support.firePropertyChange("characters", this.characters, characters);
    for (GameCharacter character: characters) {
      character.setCampaign(this);
    }
    this.characters = characters;
  }

  public void addCharacter(GameCharacter character) {
    Set<GameCharacter> old = new TreeSet<>(characters);
    character.setCampaign(this);
    characters.add(character);
    support.firePropertyChange("characters", old, characters);
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
