package ammonclegg.campaign.tracker.models;

import ammonclegg.campaign.tracker.TestUtils.TestListener;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ammonclegg on 11/8/19.
 */
public class CampaignTest {

  private TestListener testListener;

  private Campaign testModel;

  @Before
  public void setup() {
    testListener = new TestListener();

    testModel = new Campaign();

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
