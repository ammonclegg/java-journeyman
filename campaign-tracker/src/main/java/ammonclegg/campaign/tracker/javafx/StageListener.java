package ammonclegg.campaign.tracker.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * @author ammonclegg on 10/18/19.
 */
@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(StageListener.class);

  private final String applicationTitle;
  private final Resource fxml;
  private final ApplicationContext applicationContext;
  private final StageFactory stageFactory;

  StageListener(@Value("${spring.application.ui.title}") String applicationTitle,
                @Value("classpath:/campaign.fxml") Resource resource,
                ApplicationContext applicationContext,
                StageFactory stageFactory) {
    this.applicationTitle = applicationTitle;
    this.fxml = resource;
    this.applicationContext = applicationContext;
    this.stageFactory = stageFactory;
  }

  @Override
  public void onApplicationEvent(StageReadyEvent stageReadyEvent) {

    try {
      Stage stage = stageReadyEvent.getStage();
      stageFactory.registerStage(stage);
      URL url = fxml.getURL();
      FXMLLoader fxmlLoader = new FXMLLoader(url);
      fxmlLoader.setControllerFactory(applicationContext::getBean);
      Parent root = fxmlLoader.load();
      Scene scene = new Scene(root, 600, 600);
      stage.setScene(scene);
      stage.setTitle(this.applicationTitle);
      stage.show();
    }
    catch (IOException e) {
      LOGGER.error("Encountered error creating stage.", e);
      throw new RuntimeException(e);
    }

  }

}
