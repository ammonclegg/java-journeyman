package ammonclegg.campaign.tracker.models.implementations;

import ammonclegg.campaign.tracker.models.Campaign;
import ammonclegg.campaign.tracker.models.GameCharacter;

import java.util.Objects;

/**
 * @author ammonclegg on 8/2/19.
 */
public class NonPlayerCharacter extends GameCharacter{

  /**
   * For Deserialization/Serialization
   */
  private NonPlayerCharacter() {
    this("Unknown", null);
  }

  public NonPlayerCharacter(String name) {
    this(name, null);
  }

  public NonPlayerCharacter(String name, Campaign campaign) {
    super(name, campaign);
  }

  @Override
  public String toString() {
    return "PlayerCharacter{" +
        "id=" + getId() +
        ", name='" + getName() + '\'' +
        ", description='" + getDescription() + '\'' +
        '}';
  }
}
