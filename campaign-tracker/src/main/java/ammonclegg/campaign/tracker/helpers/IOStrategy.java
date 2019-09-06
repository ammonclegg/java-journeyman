package ammonclegg.campaign.tracker.helpers;

import ammonclegg.campaign.tracker.models.GameObject;

import java.io.IOException;

/**
 * @author ammonclegg on 8/23/19.
 */
public interface IOStrategy {

  void save(String filename, GameObject gameObject) throws IOException;

  GameObject load(String filename) throws IOException;
}
