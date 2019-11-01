package ammonclegg.campaign.tracker.javafx;

import ammonclegg.campaign.tracker.CampaignTrackerApplication;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ammonclegg on 10/18/19.
 */
public class JavaFxApplication extends Application {
  private static final Logger LOGGER = LoggerFactory.getLogger(JavaFxApplication.class);

  private ConfigurableApplicationContext context;

  @Override
  public void init() {
    ApplicationContextInitializer<GenericApplicationContext> initializer =
        genericApplicationContext->{

          genericApplicationContext.registerBean(Application.class, () -> JavaFxApplication.this);
          genericApplicationContext.registerBean(Parameters.class, this::getParameters);
          genericApplicationContext.registerBean(HostServices.class, this::getHostServices);
        };

    context = new SpringApplicationBuilder()
        .sources(CampaignTrackerApplication.class)
        .initializers(initializer)
        .run(getParameters().getRaw().toArray(new String[0]));
  }

  @Override
  public void start(Stage primaryStage) {
    context.publishEvent(new StageReadyEvent(primaryStage));
  }

  @Override
  public void stop() {
    context.close();
    Platform.exit();
  }
}

