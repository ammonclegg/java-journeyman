package ammonclegg.campaign.tracker.helpers;

import ammonclegg.campaign.tracker.models.Campaign;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  public JsonIOStrategy() {
    MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  @Override
  public void save(String filename, Campaign campaign) throws IOException {
    LOGGER.info("Saving file: {}, Campaign: {}", filename, campaign);
    try {
      MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(filename), campaign);
    }
    catch (JsonProcessingException e) {
      LOGGER.error("Unable to serialize campaign. campaign: {}", campaign);
      throw new IOException(e);
    }
  }

  @Override
  public Campaign load(String filename) throws IOException {
    LOGGER.info("Loading file: {}", filename);
    return MAPPER.readValue(new File(filename), Campaign.class);
  }
}
