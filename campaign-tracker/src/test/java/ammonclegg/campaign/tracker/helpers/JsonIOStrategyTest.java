package ammonclegg.campaign.tracker.helpers;

import ammonclegg.campaign.tracker.models.implementations.Campaign;
import ammonclegg.campaign.tracker.models.GameObject;
import ammonclegg.campaign.tracker.models.implementations.PlayerCharacter;
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

  private JsonIOStrategy testModel;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    testModel = new JsonIOStrategy();
  }

  @After
  public void teardown() {
//    Files.delete(new File(TEST_FILENAME));
  }

  @Test
  public void saveAndLoadShouldWorkForCampaigns() throws IOException {
    Campaign campaign = new Campaign();
    campaign.setName("FooBar");

    testModel.save(TEST_FILENAME, campaign);
    GameObject result = testModel.load(TEST_FILENAME);

    assertEquals(campaign, result);
  }

  @Test
  public void saveAndLoadShouldWorkForPC() throws IOException {
    PlayerCharacter playerCharacter = new PlayerCharacter();
    playerCharacter.setName("Joe Bag'ODonuts");

    testModel.save(TEST_FILENAME, playerCharacter);

    GameObject result = testModel.load(TEST_FILENAME);
    assertEquals(playerCharacter, result);
  }
}
