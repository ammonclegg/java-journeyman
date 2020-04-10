package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * @author ammonclegg on 4/10/20.
 */
public class CampaignObject {
  // Constants for Property change listeners
  public static final String NAME = "name";

  private UUID id;
  private String name;

  @JsonIgnore
  private PropertyChangeSupport support;

  public CampaignObject() {
    this(UUID.randomUUID());
  }

  public CampaignObject(UUID id) {
    this.id = id;
    this.support = new PropertyChangeSupport(this);
  }

  public UUID getId() {
    return id;
  }

  /**
   * This method exists for deserialization from json
   * @param id The id of the object. Will be used in links.
   */
  private void setId(UUID id) {
    this.id = id;
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

  public String getDescription() {
    return null;
  }

  public void setDescription(String description) {

  }

  public Set<String> getTags() {
    return null;
  }

  public void addTag(String tag) {

  }

  public void setTags(Set<String> tags) {

  }

  public void addTags(Collection<String> tags) {

  }
}
