package ammonclegg.campaign.tracker.models;

import ammonclegg.campaign.tracker.TestUtils.TestListener;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

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
  public void shouldSetTags() {
    Set<String> testTags = Stream.of("Test tag1").collect(Collectors.toSet());

    testModel.setTags(Stream.of("Test tag1").collect(Collectors.toSet()));

    assertEquals(testTags, testModel.getTags());
  }

  @Test
  public void shouldFireChangeEventOnTagSetting() {
    Set<String> testTags = Stream.of("Test tag1").collect(Collectors.toSet());

    testModel.setTags(Stream.of("Test tag1").collect(Collectors.toSet()));

    assertEquals("tags", testListener.getEvent().getPropertyName());
    assertEquals(testTags, testListener.getEvent().getNewValue());
  }

  @Test
  public void shouldAddATag() {
    Set<String> expectedTags = Stream.of("Test tag1").collect(Collectors.toSet());

    testModel.addTag("Test tag1");

    assertEquals(expectedTags, testModel.getTags());
  }

  @Test
  public void shouldFireChangeEventOnTagAdd() {
    Set<String> expectedTags = Stream.of("Test tag1").collect(Collectors.toSet());

    testModel.addTag("Test tag1");

    assertEquals("tags", testListener.getEvent().getPropertyName());
    assertEquals(expectedTags, testListener.getEvent().getNewValue());
  }

  @Test
  public void shouldAddAllTags() {
    Set<String> expectedTags = Stream.of("Test tag1", "Test tag2").collect(Collectors.toSet());

    testModel.addTags(Stream.of("Test tag1", "Test tag2").collect(Collectors.toSet()));

    assertEquals(expectedTags, testModel.getTags());
  }

  @Test
  public void shouldFireChangeEventOnAddTags() {
    Set<String> expectedTags = Stream.of("Test tag1", "Test tag2").collect(Collectors.toSet());

    testModel.addTags(Stream.of("Test tag1", "Test tag2").collect(Collectors.toSet()));

    assertEquals("tags", testListener.getEvent().getPropertyName());
    assertEquals(expectedTags, testListener.getEvent().getNewValue());
  }

  @Test
  public void shouldRemoveATag() {
    Set<String> existingTags = Stream.of("Test tag1").collect(Collectors.toSet());
    testModel.setTags(existingTags);

    testModel.removeTag("Test tag1");

    assertEquals(Collections.emptySet(), testModel.getTags());
  }

  @Test
  public void shouldFireChangeEventOnTagRemove() {
    Set<String> existingTags = Stream.of("Test tag1").collect(Collectors.toSet());
    testModel.setTags(existingTags);

    testModel.removeTag("Test tag1");

    assertEquals("tags", testListener.getEvent().getPropertyName());
    assertEquals(Collections.emptySet(), testListener.getEvent().getNewValue());
  }

  @Test
  public void shouldRemoveAllTagsSpecified() {
    Set<String> existingTags = Stream.of("Test tag1", "Test tag2", "Test tag3").collect(Collectors.toSet());
    testModel.setTags(existingTags);

    testModel.removeTags(Stream.of("Test tag2", "Test tag3").collect(Collectors.toSet()));

    Set<String> expectedTags = Stream.of("Test tag1").collect(Collectors.toSet());
    assertEquals(expectedTags, testModel.getTags());
  }

  @Test
  public void shouldFireChangeEventOnRemoveAllTags() {
    Set<String> existingTags = Stream.of("Test tag1", "Test tag2", "Test tag3").collect(Collectors.toSet());
    testModel.setTags(existingTags);

    testModel.removeTags(Stream.of("Test tag2", "Test tag3").collect(Collectors.toSet()));

    Set<String> expectedTags = Stream.of("Test tag1").collect(Collectors.toSet());
    assertEquals("tags", testListener.getEvent().getPropertyName());
    assertEquals(expectedTags, testListener.getEvent().getNewValue());
  }
}
