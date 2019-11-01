package ammonclegg.campaign.tracker.javafx.controllers;

import ammonclegg.campaign.tracker.javafx.StageFactory;
import ammonclegg.campaign.tracker.helpers.IOStrategy;
import ammonclegg.campaign.tracker.models.Campaign;
import ammonclegg.campaign.tracker.models.GameObject;
import ammonclegg.campaign.tracker.models.Location;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ammonclegg on 8/23/19.
 */
@Component
public class CampaignController {
  private static final Logger LOGGER = LoggerFactory.getLogger(CampaignController.class);

  private final FileChooser fileChooser = new FileChooser();
  private Campaign campaign;
  private IOStrategy ioStrategy;
  private String filename;

  @Autowired
  private StageFactory stageFactory;

  @FXML
  private Button addCharacterButton;

  @FXML
  private ScrollPane characterScrollPane;

  @FXML
  private ListView characterListView;

  @FXML
  private MenuBar menuBar;

  @Autowired
  public CampaignController(IOStrategy ioStrategy) {
    this(ioStrategy, new Campaign());
  }

  CampaignController(IOStrategy ioStrategy, Campaign campaign) {
    this.ioStrategy = ioStrategy;
    this.campaign = campaign;
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Campaigns", ".cmp"));
  }

  @FXML
  public void initialize() {
    if (campaign != null) {
      menuBar.setFocusTraversable(true);
      characterListView.getItems().addAll(getCharacterNames());
    }
  }

  @FXML
  private void handleKeyInput(final InputEvent event) {
    if (event instanceof KeyEvent) {
      final KeyEvent keyEvent = (KeyEvent) event;
      if (keyEvent.isShortcutDown()) {
        switch(keyEvent.getCode()) {
          case N:
            newCampaign();
            break;
          case S:
            saveCampaign(event);
            break;
          case O:
            loadCampaign(event);
            break;
          case Q:
            Platform.exit();
          default:
        }
      }
    }
  }

  @FXML
  private void newCampaign() {
    campaign = new Campaign();
  }

  @FXML
  private void saveCampaignAs(final Event event) {
    LOGGER.info("Event for saveCampaign: {}", event);
    try {
      String file = getSaveFileFromUser();
      if (file != null) {
        save(file);
      }
    }
    catch (IOException e) {
      LOGGER.error("Failed to save campaign.", e);
    }
  }

  @FXML
  private void saveCampaign(final Event event) {
    LOGGER.info("Event for saveCampaign: {}", event);
    try {
      if (filename == null || filename.isEmpty()) {
        String file = getSaveFileFromUser();
        if (file != null) {
          save(file);
        }
      }
      else {
        save(filename);
      }
    }
    catch (IOException e) {
      LOGGER.error("Failed to save campaign.", e);
    }
  }

  private String getSaveFileFromUser() {
    fileChooser.setTitle("Save Campaign");
    File file = fileChooser.showSaveDialog(stageFactory.getCurrentStage());
    if (file != null) {
      return file.getAbsolutePath();
    }
    else {
      return null;
    }
  }

  @FXML
  private void loadCampaign(final Event event) {
    try {
      fileChooser.setTitle("Open Campaign");
      File file = fileChooser.showOpenDialog(stageFactory.getCurrentStage());
      if (file != null) {
        loadCampaign(file.getAbsolutePath());
      }
    }
    catch (IOException e) {
      LOGGER.error("Unable to load campaign.", e);
    }
  }

  @FXML
  private void exit() {
    Platform.exit();
  }

  private List<String> getCharacterNames() {
    return campaign.getCharacters().stream().map(GameObject::getName).collect(Collectors.toList());
  }

  Campaign getCampaign() {
    return campaign;
  }

  public void save(String filename) throws IOException {
    this.filename = filename;
    ioStrategy.save(filename, campaign);
  }

  public void loadCampaign(String filename) throws IOException {
    campaign = ioStrategy.load(filename);
  }

  public Set<Location> getLocations() {
    return campaign.getLocations();
  }
}
