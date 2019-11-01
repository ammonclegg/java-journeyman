package ammonclegg.campaign.tracker.javafx;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

/**
 * @author ammonclegg on 10/18/19.
 */
class StageReadyEvent extends ApplicationEvent {
  public StageReadyEvent(Stage source) {
    super(source);
  }

  public Stage getStage() {
    return Stage.class.cast(getSource());
  }
}
