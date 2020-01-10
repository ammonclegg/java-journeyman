package ammonclegg.campaign.tracker.models;

import ammonclegg.campaign.tracker.TestUtils.TestListener;
import ammonclegg.campaign.tracker.models.implementations.NonPlayerCharacter;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ammonclegg on 11/8/19.
 */
public class CampaignTest {

  private static final String TEST_LOCATION_NAME = "TestLocation";
  private static final String TEST_CHARACTER_NAME = "TestCharacter";
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
  public void shouldUpdateDescription() {
    String testDescription = "New description";

    testModel.setDescription("New description");

    assertEquals(testDescription, testModel.getDescription());
  }

  @Test
  public void shouldFireChangeEventOnDescriptionUpdate() {
    String testDescription = "New description";

    testModel.setDescription("New description");

    assertEquals("description", testListener.getEvent().getPropertyName());
    assertEquals(testDescription, testListener.getEvent().getNewValue());
  }

  @Test
  public void shouldUpdateLocationsIfSet() {
    Location newLocation = new Location(TEST_LOCATION_NAME);
    List<Location> locations = Collections.singletonList(newLocation);

    testModel.setLocations(locations);

    assertEquals(locations, testModel.getLocations());
  }

  @Test
  public void setLocationsShouldSetCampaignForLocations() {
    Location newLocation = new Location(TEST_LOCATION_NAME);
    List<Location> locations = Collections.singletonList(newLocation);

    testModel.setLocations(locations);

    List<Location> resultingLocations = testModel.getLocations();
    resultingLocations.forEach((location) -> assertEquals(testModel, location.getCampaign()));
  }

  @Test
  public void shouldFireChangeEventOnLocationsUpdate() {
    Location newLocation = new Location(TEST_LOCATION_NAME);
    List<Location> locations = Collections.singletonList(newLocation);

    testModel.setLocations(locations);

    assertEquals("locations", testListener.getEvent().getPropertyName());
    assertEquals(Collections.singletonList(newLocation), testListener.getEvent().getNewValue());
  }

  @Test
  public void addLocationShouldAddANewLocation() {
    Location newLocation = new Location(TEST_LOCATION_NAME);

    testModel.addLocation(newLocation);

    assertTrue("Expected testModel to contain new location", testModel.getLocations().contains(newLocation));
  }

  @Test
  public void addLocationShouldSetCampaignForLocation() {
    Location newLocation = new Location(TEST_LOCATION_NAME);

    testModel.addLocation(newLocation);

    assertEquals(testModel, newLocation.getCampaign());
  }

  @Test
  public void addLocationShouldFireChangeEvent() {
    Location newLocation = new Location(TEST_LOCATION_NAME);

    testModel.addLocation(newLocation);

    assertEquals("locations", testListener.getEvent().getPropertyName());
    assertEquals(Collections.singletonList(newLocation), testListener.getEvent().getNewValue());
  }

  @Test
  public void shouldUpdateCharactersIfSet() {
    GameCharacter newCharacter = new NonPlayerCharacter(TEST_CHARACTER_NAME);
    List<GameCharacter> characterList = Collections.singletonList(newCharacter);

    testModel.setCharacters(characterList);

    assertEquals(characterList, testModel.getCharacters());
  }

  @Test
  public void setCharactersShouldSetCampaignForCharacters() {
    GameCharacter newCharacter = new NonPlayerCharacter(TEST_CHARACTER_NAME);
    List<GameCharacter> characterList = Collections.singletonList(newCharacter);

    testModel.setCharacters(characterList);

    List<GameCharacter> characters = testModel.getCharacters();
    characters.forEach((character) -> assertEquals(testModel, character.getCampaign()));
  }

  @Test
  public void shouldFireChangeEventOnCharactersUpdate() {
    GameCharacter newCharacter = new NonPlayerCharacter(TEST_CHARACTER_NAME);
    List<GameCharacter> characterList = Collections.singletonList(newCharacter);

    testModel.setCharacters(characterList);

    assertEquals("characters", testListener.getEvent().getPropertyName());
    assertEquals(Collections.singletonList(newCharacter), testListener.getEvent().getNewValue());
  }

  @Test
  public void addCharacterShouldAddANewCharacter() {
    GameCharacter newCharacter = new NonPlayerCharacter(TEST_CHARACTER_NAME);

    testModel.addCharacter(newCharacter);

    assertTrue("Expected testModel to contain new character", testModel.getCharacters().contains(newCharacter));
  }

  @Test
  public void addCharacterShouldSetCampaignForCharacter() {
    GameCharacter newCharacter = new NonPlayerCharacter(TEST_CHARACTER_NAME);

    testModel.addCharacter(newCharacter);

    assertEquals(testModel, newCharacter.getCampaign());
  }

  @Test
  public void addCharacterShouldFireChangeEvent() {
    GameCharacter newCharacter = new NonPlayerCharacter(TEST_CHARACTER_NAME);

    testModel.addCharacter(newCharacter);

    assertEquals("characters", testListener.getEvent().getPropertyName());
    assertEquals(Collections.singletonList(newCharacter), testListener.getEvent().getNewValue());
  }
}
