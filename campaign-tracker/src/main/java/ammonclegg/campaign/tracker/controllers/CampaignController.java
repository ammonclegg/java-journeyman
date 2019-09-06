package ammonclegg.campaign.tracker.controllers;

import ammonclegg.campaign.tracker.exceptions.BadTypeException;
import ammonclegg.campaign.tracker.helpers.IOStrategy;
import ammonclegg.campaign.tracker.models.Location;
import ammonclegg.campaign.tracker.models.implementations.Campaign;
import ammonclegg.campaign.tracker.models.GameObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Set;

/**
 * @author ammonclegg on 8/23/19.
 */
public class CampaignController {

  private Campaign campaign;
  private IOStrategy ioStrategy;

  @Autowired
  public CampaignController(IOStrategy ioStrategy) {
    this.ioStrategy = ioStrategy;
  }

  CampaignController(IOStrategy ioStrategy, Campaign campaign) {
    this.ioStrategy = ioStrategy;
    this.campaign = campaign;
  }

  Campaign getCampaign() {
    return campaign;
  }

  public void save(String filename) throws IOException {
    ioStrategy.save(filename, campaign);
  }

  public void loadCampaign(String filename) throws BadTypeException, IOException {
    GameObject gameObject = ioStrategy.load(filename);
    if (gameObject instanceof Campaign) {
      campaign = (Campaign) gameObject;
    }
    else {
      String message = String.format("Expected file to contain a campaign object. Found a %s", gameObject.getClass());
      throw new BadTypeException(message);
    }
  }

  public Set<Location> getLocations() {
    return campaign.getLocations();
  }
}
