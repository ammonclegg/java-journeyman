package ammonclegg.campaign.tracker.helpers;

import ammonclegg.campaign.tracker.models.Campaign;

import java.io.IOException;

/**
 * @author ammonclegg on 8/23/19.
 */
public interface IOStrategy {

  void save(String filename, Campaign gameObject) throws IOException;

  Campaign load(String filename) throws IOException;
}
