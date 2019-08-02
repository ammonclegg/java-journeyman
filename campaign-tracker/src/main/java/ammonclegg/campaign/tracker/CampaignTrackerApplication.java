package ammonclegg.campaign.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author ammonclegg on 8/2/19.
 */
@SpringBootApplication
public class CampaignTrackerApplication implements CommandLineRunner{

  private static final Logger LOGGER = LoggerFactory.getLogger(CampaignTrackerApplication.class);

  public static void main(String[] args) {
    new SpringApplicationBuilder(CampaignTrackerApplication.class).headless(false).run(args);
  }

  @Override
  public void run(String... args) throws Exception {

  }
}

