package ammonclegg.campaign.tracker.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.stereotype.Component;

/**
 * @author ammonclegg on 11/1/19.
 */
@Component
public class StageFactory {
  private final ObservableList<Stage> openStages = FXCollections.observableArrayList();
  private final ObjectProperty<Stage> currentStage = new SimpleObjectProperty<>(null);

  public ObservableList<Stage> getOpenStages() {
    return openStages;
  }

  public final ObjectProperty<Stage> currentStageProperty() {
    return currentStage;
  }

  public final Stage getCurrentStage() {
    return currentStageProperty().get();
  }

  public final void setCurrentStage(final Stage currentStage) {
    currentStageProperty().set(currentStage);
  }

  public void registerStage(Stage stage) {
    stage.addEventHandler(WindowEvent.WINDOW_SHOWN, e->openStages.add(stage));
    stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, e->openStages.add(stage));
    stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
      if (isNowFocused) {
        currentStage.set(stage);
      }
      else {
        currentStage.set(null);
      }
    });
  }

  public Stage createStage() {
    Stage stage = new Stage();
    registerStage(stage);
    return stage;
  }
}
