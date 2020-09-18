package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * @author ammonclegg on 4/10/20.
 */
public class CampaignObject {
  // Constants for Property change listeners
  public static final String NAME = "name";
  public static final String DESCRIPTION = "description";
  public static final String TAGS = "tags";

  private UUID id;
  private String name;
  private String description;
  private Set<String> tags = new HashSet<>();

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

  /**
   * Get the name of the CampaignObject
   *
   * @return the name of the CampaignObject
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the campaign object
   * This fires a property change
   *
   * @param name The new name of the object
   */
  public void setName(String name) {
    support.firePropertyChange(NAME, this.name, name);
    this.name = name;
  }

  /**
   * Get the description of the CampaignObject
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the description of the campaign object
   * This fires a property change
   *
   * @param description The new description of the object
   */
  public void setDescription(String description) {
    support.firePropertyChange(DESCRIPTION, this.description, description);
    this.description = description;
  }

  /**
   * Get an unmodifiable set of tags for the Object
   *
   * @return an unmodifiable set of current tags for the object
   */
  public Set<String> getTags() {
    return Collections.unmodifiableSet(tags);
  }

  /**
   * Add a tag to the object
   *
   * @param tag the tag to add to the object
   */
  public void addTag(String tag) {
    Set<String> oldTags = Collections.unmodifiableSet(new HashSet<>(tags));
    tags.add(tag);
    support.firePropertyChange(TAGS, oldTags, tags);
  }

  /**
   * Remove a tag from the object
   *
   * @param tag the tag to remove from the object
   */
  public void removeTag(String tag) {
    Set<String> oldTags = Collections.unmodifiableSet(new HashSet<>(tags));
    tags.remove(tag);
    support.firePropertyChange(TAGS, oldTags, tags);
  }

  /**
   * Set the tags for an object
   *
   * @param tags the tags for the object
   */
  public void setTags(Set<String> tags) {
    Set<String> oldTags = Collections.unmodifiableSet(new HashSet<>(this.tags));
    this.tags = new HashSet<>(tags);
    support.firePropertyChange(TAGS, oldTags, this.tags);
  }

  /**
   * Add a set of tags to the campaign object
   *
   * @param tags the tags to add.
   */
  public void addTags(Collection<String> tags) {
    Set<String> oldTags = Collections.unmodifiableSet(new HashSet<>(this.tags));
    this.tags.addAll(tags);
    support.firePropertyChange(TAGS, oldTags, this.tags);
  }

  /**
   * Removes a set of tags to the campaign object
   *
   * @param tags the tags to remove.
   */
  public void removeTags(Collection<String> tags) {
    Set<String> oldTags = Collections.unmodifiableSet(new HashSet<>(this.tags));
    this.tags.removeAll(tags);
    support.firePropertyChange(TAGS, oldTags, this.tags);
  }
}
