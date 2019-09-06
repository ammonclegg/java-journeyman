package ammonclegg.campaign.tracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

/**
 * @author ammonclegg on 8/2/19.
 */
@SpringBootApplication
public class CampaignTrackerApplication extends Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(CampaignTrackerApplication.class);

  private ConfigurableApplicationContext context;
  private Parent rootNode;

  @Override
  public void init() throws Exception {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(CampaignTrackerApplication.class).headless(false);
    context = builder.run(getParameters().getRaw().toArray(new String[0]));

    URL location = getClass().getResource("/main.fxml");
    LOGGER.info("location: {}", location);
    FXMLLoader loader = new FXMLLoader(location);
    loader.setControllerFactory(context::getBean);
    rootNode = loader.load();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
    double width = visualBounds.getWidth();
    double height = visualBounds.getHeight();

    primaryStage.setScene(new Scene(rootNode, width, height));
    primaryStage.centerOnScreen();
    primaryStage.show();
  }

  @Override
  public void stop() throws Exception {
    context.close();
  }
}

