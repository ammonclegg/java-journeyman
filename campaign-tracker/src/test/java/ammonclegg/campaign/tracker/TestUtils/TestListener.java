package ammonclegg.campaign.tracker.TestUtils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author ammonclegg on 11/8/19.
 */
public class TestListener  implements PropertyChangeListener {
  private PropertyChangeEvent event;

  public void propertyChange(PropertyChangeEvent event) {
    this.event = event;
  }

  public PropertyChangeEvent getEvent() {
    return event;
  }
}
