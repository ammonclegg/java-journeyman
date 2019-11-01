package ammonclegg.campaign.tracker;

import ammonclegg.campaign.tracker.javafx.JavaFxApplication;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ammonclegg on 8/2/19.
 */
@SpringBootApplication
public class CampaignTrackerApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(CampaignTrackerApplication.class);

  public static void main(String[] args) {
    Application.launch(JavaFxApplication.class, args);
  }
}

