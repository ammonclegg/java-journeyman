package ammonclegg.campaign.tracker.models;

import java.util.Set;

/**
 * @author ammonclegg on 8/2/19.
 */
public interface Location extends GameObject {
  Location getParentLocation();

  Set<GameCharacter> getCharacters();

  Set<GameEvent> getGameEvents();
}
