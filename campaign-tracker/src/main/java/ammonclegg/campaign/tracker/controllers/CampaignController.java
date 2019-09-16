package ammonclegg.campaign.tracker.controllers;

import ammonclegg.campaign.tracker.helpers.IOStrategy;
import ammonclegg.campaign.tracker.models.Campaign;
import ammonclegg.campaign.tracker.models.Location;
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

  public void loadCampaign(String filename) throws IOException {
    campaign = ioStrategy.load(filename);
  }

  public Set<Location> getLocations() {
    return campaign.getLocations();
  }
}
