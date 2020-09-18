package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * @author ammonclegg on 8/2/19.
 */
public class Campaign {
  // Constants for Property change listeners
  public static final String NAME = "name";

  private String name;
  private List<CampaignObject> campaignObjects = new ArrayList<>();

  @JsonIgnore
  private PropertyChangeSupport support;

  public Campaign() {
    support = new PropertyChangeSupport(this);
  }

  public void addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

  public void removePropertyChangeListener(PropertyChangeListener pcl) {
    support.removePropertyChangeListener(pcl);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    support.firePropertyChange(NAME, this.name, name);
    this.name = name;
  }

  /**
   * Update the campaigns list of objects
   * @param campaignObjects The list of objects to add to the campaign
   */
  public void setCampaignObjects(List<CampaignObject> campaignObjects) {
    this.campaignObjects = campaignObjects;
  }

  public Optional<CampaignObject> getCampaignObject(UUID id) {
    return campaignObjects.stream().filter(campaignObject->campaignObject.getId().equals(id)).findFirst();
  }

  public UUID createCampaignObject(String name) {
    CampaignObject newObject = new CampaignObject();
    newObject.setName(name);
    campaignObjects.add(newObject);
    return newObject.getId();
  }

  public List<CampaignObject> getCampaignObjects() {
    return campaignObjects;
  }

  // TODO: Add a filtered campaign objects get

  public void removeCampaignObject(UUID id) {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Campaign campaign = (Campaign) o;
    return Objects.equals(name, campaign.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "Campaign{" +
        "name='" + name + '\'' +
        '}';
  }
}
