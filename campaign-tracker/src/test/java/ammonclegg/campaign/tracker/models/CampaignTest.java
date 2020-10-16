package ammonclegg.campaign.tracker.models;

import ammonclegg.campaign.tracker.TestUtils.TestListener;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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

  @Test
  public void shouldCreateNewCampaignObjectWithCorrectName() {
    String testObjectName = "test name";

    UUID id = testModel.createCampaignObject(testObjectName);

    Optional<CampaignObject> result = testModel.getCampaignObject(id);
    if (result.isPresent()) {
      assertEquals(testObjectName, result.get().getName());
    }
    else {
      fail("No object with id for created object found.");
    }
  }

  @Test
  public void setCampaignObjectsShouldSetTheListOfObjects() {
    List<CampaignObject> expected = new ArrayList<>();
    expected.add(new CampaignObject());

    testModel.setCampaignObjects(expected);
    List<CampaignObject> result = testModel.getCampaignObjects();

    assertEquals(expected, result);
  }

  @Test
  public void shouldFireChangeEventOnSetCampaignObjects() {
    List<CampaignObject> expected = new ArrayList<>();
    expected.add(new CampaignObject());

    testModel.setCampaignObjects(expected);

    assertEquals("campaignObjects", testListener.getEvent().getPropertyName());
    assertEquals(expected, testListener.getEvent().getNewValue());
  }

  @Test
  public void createCampaignObjectsShouldCreateANewObjects() {
    String expectedName = "test name object";

    UUID id = testModel.createCampaignObject(expectedName);
    List<CampaignObject> result = testModel.getCampaignObjects();

    assertEquals(1, result.size());
    assertEquals(expectedName, result.stream().findFirst().orElse(new CampaignObject()).getName());
  }

  @Test
  public void shouldFireChangeEventOnCreateCampaignObjects() {
    String expectedName = "test name object";

    UUID id = testModel.createCampaignObject(expectedName);
    CampaignObject createdObject = testModel.getCampaignObject(id).orElse(null);
    List<CampaignObject> expectedList = Collections.singletonList(createdObject);

    assertEquals("campaignObjects", testListener.getEvent().getPropertyName());
    assertEquals(expectedList, testListener.getEvent().getNewValue());
  }
}
