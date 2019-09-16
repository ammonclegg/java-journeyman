package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Objects;

/**
 * @author ammonclegg on 8/2/19.
 */
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public abstract class GameCharacter implements GameObject, Comparable<GameCharacter> {
  private Integer id;

  private Campaign campaign;

  /**
   * For Deserialization/Serialization
   */
  private GameCharacter() {}

  public GameCharacter(Campaign campaign) {
    this.campaign = campaign;
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
   * @param id The id of the character
   */
  private void setId(Integer id) {
    this.id = id;
  }

  @Override
  public int compareTo(GameCharacter o) {
    int compareResults = getName().compareTo(o.getName());
    if (compareResults != 0) {
      return compareResults;
    }
    return id.compareTo(o.id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GameCharacter that = (GameCharacter) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
