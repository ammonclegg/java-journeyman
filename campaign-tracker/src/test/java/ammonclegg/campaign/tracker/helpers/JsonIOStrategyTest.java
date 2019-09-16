package ammonclegg.campaign.tracker.helpers;

import ammonclegg.campaign.tracker.models.Campaign;
import ammonclegg.campaign.tracker.models.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author ammonclegg on 8/23/19.
 */
public class JsonIOStrategyTest {
  private static String TEST_FILENAME = "testFile.json";

  private Campaign testCampaign;

  private JsonIOStrategy testModel;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    testCampaign = new Campaign();
    testCampaign.setName("FooBar");

    testModel = new JsonIOStrategy();
  }

  @After
  public void teardown() {
//    Files.delete(new File(TEST_FILENAME));
  }

  @Test
  public void saveAndLoadShouldWorkForCampaigns() throws IOException {
    testModel.save(TEST_FILENAME, testCampaign);
    Campaign result = testModel.load(TEST_FILENAME);

    assertEquals(result, testCampaign);
  }

  @Test
  public void saveCampaignShouldSerializeLocationsProperly() throws IOException {
    Location parentLocation = new Location(testCampaign, "parent");
    testCampaign.addLocation(parentLocation);
    Location location = new Location(testCampaign, "test location", parentLocation);
    testCampaign.addLocation(location);

    testModel.save(TEST_FILENAME, testCampaign);
    Campaign result = testModel.load(TEST_FILENAME);

    assertEquals(result, testCampaign);
  }
}
