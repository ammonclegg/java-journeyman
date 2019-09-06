package ammonclegg.campaign.tracker.helpers;

import ammonclegg.campaign.tracker.models.GameObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author ammonclegg on 8/23/19.
 */
@Component
public class JsonIOStrategy implements IOStrategy {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonIOStrategy.class);

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public void save(String filename, GameObject gameObject) throws IOException {
    try {
      MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(filename), gameObject);
    }
    catch (JsonProcessingException e) {
      LOGGER.error("Unable to serialize game object. gameObject: {}", gameObject);
      throw new IOException(e);
    }
  }

  @Override
  public GameObject load(String filename) throws IOException {
    return MAPPER.readValue(new File(filename), GameObject.class);
  }
}
