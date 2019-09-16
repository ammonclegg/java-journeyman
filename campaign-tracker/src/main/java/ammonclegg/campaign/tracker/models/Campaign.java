package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
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
  private Set<Location> locations = new TreeSet<>();
  private Set<GameCharacter> characters = new TreeSet<>();
  private Set<GameEvent> events = new HashSet<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Location> getLocations() {
    return locations;
  }

  /**
   * Used for serialization/deserialization
   * @param locations
   */
  void setLocations(Set<Location> locations) {
    this.locations = locations;
  }

  public void addLocation(Location location) {
    location.setCampaign(this);
    locations.add(location);
  }

  public Set<GameCharacter> getCharacters() {
    return characters;
  }

  void setCharacters(Set<GameCharacter> characters) {
    this.characters = characters;
  }

  public void addCharacter(GameCharacter character) {
    character.setCampaign(this);
    characters.add(character);
  }

  public Set<GameEvent> getEvents() {
    return events;
  }

  void setEvents(Set<GameEvent> events) {
    this.events = events;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Campaign campaign = (Campaign) o;
    return Objects.equals(name, campaign.name) &&
        Objects.equals(description, campaign.description) &&
        Objects.equals(locations, campaign.locations) &&
        Objects.equals(characters, campaign.characters) &&
        Objects.equals(events, campaign.events);
  }

  @Override
  public int hashCode() {

    return Objects.hash( name, description, locations, characters, events);
  }

  @Override
  public String toString() {
    return "Campaign{" +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", locations=" + locations +
        ", characters=" + characters +
        ", events=" + events +
        '}';
  }
}
