package ammonclegg.campaign.tracker.models;

import java.util.Objects;
import java.util.Set;

/**
 * @author ammonclegg on 8/2/19.
 */
public class Campaign implements GameObject {
  private String type;
  private String name;
  private String description;
  private Set<Location> locations;
  private Set<GameCharacter> characters;
  private Set<GameEvent> events;

  @Override
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public Set<Location> getLocations() {
    return locations;
  }

  public void setLocations(Set<Location> locations) {
    this.locations = locations;
  }

  public Set<GameCharacter> getCharacters() {
    return characters;
  }

  public void setCharacters(Set<GameCharacter> characters) {
    this.characters = characters;
  }

  public Set<GameEvent> getEvents() {
    return events;
  }

  public void setEvents(Set<GameEvent> events) {
    this.events = events;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Campaign campaign = (Campaign) o;
    return Objects.equals(type, campaign.type) &&
        Objects.equals(name, campaign.name) &&
        Objects.equals(description, campaign.description) &&
        Objects.equals(locations, campaign.locations) &&
        Objects.equals(characters, campaign.characters) &&
        Objects.equals(events, campaign.events);
  }

  @Override
  public int hashCode() {

    return Objects.hash(type, name, description, locations, characters, events);
  }

  @Override
  public String toString() {
    return "Campaign{" +
        "type='" + type + '\'' +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", locations=" + locations +
        ", characters=" + characters +
        ", events=" + events +
        '}';
  }
}
