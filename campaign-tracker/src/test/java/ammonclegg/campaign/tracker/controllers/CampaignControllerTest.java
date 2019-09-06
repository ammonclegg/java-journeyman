package ammonclegg.campaign.tracker.controllers;

import ammonclegg.campaign.tracker.exceptions.BadTypeException;
import ammonclegg.campaign.tracker.helpers.IOStrategy;
import ammonclegg.campaign.tracker.models.implementations.Campaign;
import ammonclegg.campaign.tracker.models.implementations.PlayerCharacter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author ammonclegg on 8/23/19.
 */
public class CampaignControllerTest {
  private static final String CAMPAIGN_FILENAME = "testcampaign";

  @Mock
  private IOStrategy fileUtil;

  @Mock
  private Campaign campaign;

  private CampaignController testModel;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    testModel = new CampaignController(fileUtil, campaign);
  }

  @Test
  public void testSaveShouldSaveTheCampaign() throws IOException {
    testModel.save(CAMPAIGN_FILENAME);

    verify(fileUtil).save(CAMPAIGN_FILENAME, campaign);
  }

  @Test
  public void testLoadShouldLoadCurrentCampaign() throws BadTypeException, IOException {
    Campaign expectedCampaign = new Campaign();
    when(fileUtil.load(CAMPAIGN_FILENAME)).thenReturn(expectedCampaign);

    testModel.loadCampaign(CAMPAIGN_FILENAME);
    
    verify(fileUtil).load(CAMPAIGN_FILENAME);
    assertEquals(expectedCampaign, testModel.getCampaign());
  }

  @Test(expected = BadTypeException.class)
  public void testLoadShouldThrowExceptionIfFileDoesntContainACampaign() throws IOException, BadTypeException {
    PlayerCharacter playerCharacter = new PlayerCharacter();
    when(fileUtil.load(CAMPAIGN_FILENAME)).thenReturn(playerCharacter);

    testModel.loadCampaign(CAMPAIGN_FILENAME);

    verify(fileUtil).load(CAMPAIGN_FILENAME);
  }
}
