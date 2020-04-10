package ammonclegg.campaign.tracker.models;

import ammonclegg.campaign.tracker.TestUtils.TestListener;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ammonclegg on 4/10/20.
 */
public class CampaignObjectTest {

  private TestListener testListener;

  private CampaignObject testModel;

  @Before
  public void setup() {
    testListener = new TestListener();

    testModel = new CampaignObject();

    testModel.addPropertyChangeListener(testListener);
  }

  @Test
  public void shouldUpdateName() {
    String testName = "New name";

    testModel.setName("New name");

    assertEquals(testName, testModel.getName());
  }

  @Test
  public void shouldFireChangeEventOnNameUpdate() {
    String testName = "New name";

    testModel.setName("New name");

    assertEquals("name", testListener.getEvent().getPropertyName());
    assertEquals(testName, testListener.getEvent().getNewValue());
  }
}
