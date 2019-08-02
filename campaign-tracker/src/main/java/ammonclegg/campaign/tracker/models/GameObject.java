package ammonclegg.campaign.tracker.models;

/**
 * Interface common to all campaign items
 *
 * @author ammonclegg on 8/2/19.
 */
public interface GameObject {

  /**
   * Gets the type of object referred.
   * @return String with game type
   */
  String getType();

  String getName();

  String getDescription();
}
