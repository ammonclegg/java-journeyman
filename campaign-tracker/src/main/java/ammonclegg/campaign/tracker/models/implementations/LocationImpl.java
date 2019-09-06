package ammonclegg.campaign.tracker.models.implementations;

import ammonclegg.campaign.tracker.models.GameCharacter;
import ammonclegg.campaign.tracker.models.GameEvent;
import ammonclegg.campaign.tracker.models.Location;

import java.util.Objects;
import java.util.Set;

/**
 * @author ammonclegg on 8/2/19.
 */
public class LocationImpl implements Location {
  private String name;
  private String description;
  private Location parentLocation;
  private Set<GameCharacter> characters;
  private Set<GameEvent> gameEvents;

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
  public Location getParentLocation() {
    return parentLocation;
  }

  public void setParentLocation(Location parentLocation) {
    this.parentLocation = parentLocation;
  }

  @Override
  public Set<GameCharacter> getCharacters() {
    return characters;
  }

  public void setCharacters(Set<GameCharacter> characters) {
    this.characters = characters;
  }

  @Override
  public Set<GameEvent> getGameEvents() {
    return gameEvents;
  }

  public void setGameEvents(Set<GameEvent> gameEvents) {
    this.gameEvents = gameEvents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LocationImpl location = (LocationImpl) o;
    return Objects.equals(name, location.name) &&
        Objects.equals(description, location.description) &&
        Objects.equals(parentLocation, location.parentLocation) &&
        Objects.equals(characters, location.characters) &&
        Objects.equals(gameEvents, location.gameEvents);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name, description, parentLocation, characters, gameEvents);
  }

  @Override
  public String toString() {
    return "LocationImpl{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", parentLocation=" + parentLocation +
        ", characters=" + characters +
        ", gameEvents=" + gameEvents +
        '}';
  }
}
