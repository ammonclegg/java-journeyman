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
  public static final String CAMPAIGN_OBJECTS = "campaignObjects";

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

  private void addCampaignObjects(List<CampaignObject> objects) {
    List<CampaignObject> oldList = Collections.unmodifiableList(new ArrayList<>(this.campaignObjects));
    campaignObjects.addAll(objects);
    support.firePropertyChange(CAMPAIGN_OBJECTS, oldList, campaignObjects);
  }

  /**
   * Update the campaigns list of objects
   *
   * @param campaignObjects The list of objects to add to the campaign
   */
  public void setCampaignObjects(List<CampaignObject> campaignObjects) {
    List<CampaignObject> oldList = Collections.unmodifiableList(new ArrayList<>(this.campaignObjects));
    this.campaignObjects = new ArrayList<>(campaignObjects);
    support.firePropertyChange(CAMPAIGN_OBJECTS, oldList, campaignObjects);
  }

  public Optional<CampaignObject> getCampaignObject(UUID id) {
    return campaignObjects.stream().filter(campaignObject->campaignObject.getId().equals(id)).findFirst();
  }

  public UUID createCampaignObject(String name) {
    CampaignObject newObject = new CampaignObject();
    newObject.setName(name);
    addCampaignObjects(Collections.singletonList(newObject));
    return newObject.getId();
  }

  public List<CampaignObject> getCampaignObjects() {
    return campaignObjects;
  }

//  public List<CampaignObject> getCampaignObjectsByName(String name) {
//
//  }
//
//  public List<CampaignObject> getCampaignObjectsByTag(String tag) {
//
//  }
//
//  public void removeCampaignObject(UUID id) {
//
//  }

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
