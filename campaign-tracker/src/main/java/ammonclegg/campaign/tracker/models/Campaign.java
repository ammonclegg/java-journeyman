package ammonclegg.campaign.tracker.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.collections.FXCollections;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 * @author ammonclegg on 8/2/19.
 */
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "name"
)
public class Campaign {
  // Constants for Property change listeners
  public static final String NAME = "name";

  private String name;

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
