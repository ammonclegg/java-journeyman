package ammonclegg.campaign.tracker.javafx.controllers;

import ammonclegg.campaign.tracker.javafx.StageFactory;
import ammonclegg.campaign.tracker.helpers.IOStrategy;
import ammonclegg.campaign.tracker.models.Campaign;
import ammonclegg.campaign.tracker.models.CampaignObject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author ammonclegg on 8/23/19.
 */
@Component
public class CampaignController implements PropertyChangeListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(CampaignController.class);

  private final FileChooser fileChooser = new FileChooser();
  private TextInputDialog textInputDialog;
  private Campaign campaign;
  private IOStrategy ioStrategy;
  private String filename;

  @Autowired
  private StageFactory stageFactory;

  @FXML
  private MenuBar menuBar;

  private ObservableList<CampaignObject> objectObservableList;

  @FXML
  private ListView<CampaignObject> objectListView;

  @Autowired
  public CampaignController(IOStrategy ioStrategy) {
    this(ioStrategy, new Campaign());
  }

  CampaignController(IOStrategy ioStrategy, Campaign campaign) {
    LOGGER.info("Starting campaign controller");
    this.ioStrategy = ioStrategy;
    setCampaign(campaign);
    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Campaigns", "*.cmp");
    fileChooser.getExtensionFilters().addAll(extensionFilter);
    fileChooser.setSelectedExtensionFilter(extensionFilter);
    fileChooser.setInitialFileName("campaign.cmp");
    objectObservableList = FXCollections.observableArrayList(campaign.getCampaignObjects());
  }

  private void setCampaign(Campaign campaign) {
    if (this.campaign != null) {
      this.campaign.removePropertyChangeListener(this);
    }
    this.campaign = campaign;
    this.campaign.addPropertyChangeListener(this);
  }

  Campaign getCampaign() {
    return campaign;
  }

  @FXML
  public void initialize() {
    LOGGER.info("Initializing campaign.");
    textInputDialog = new TextInputDialog();

    objectListView.setCellFactory(param->new ListCell<CampaignObject>() {
      @Override
      public void updateItem(CampaignObject item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        }
        if (item != null) {
          setText(item.getName());
        }
      }
    });

    if (campaign != null) {
      menuBar.setFocusTraversable(true);
    }
  }

  @FXML
  private void handleKeyInput(final InputEvent event) {
    LOGGER.info("Received key press event. event={}", event);
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
    LOGGER.info("Creating new campaigin");
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
    LOGGER.info("Getting save file name from user.");
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
    LOGGER.info("Opening dialog box for Loading the campaign");
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

  private void updateCampaignObjectList() {
    LOGGER.info("Updating list of campaign objects.");
    if (objectListView != null) {
      if (campaign != null) {
        objectListView.setItems(FXCollections.observableList(campaign.getCampaignObjects()));
      }
      else {
        objectListView.setItems(FXCollections.observableArrayList());
      }
    }
  }

  @FXML
  private void addObject() {

  }

  private Optional<String> getStringValueFromUser(String header, String content) {
    LOGGER.debug("Getting value from user");
    textInputDialog.setHeaderText(header);
    textInputDialog.setContentText(content);
    textInputDialog.setGraphic(null);
    return textInputDialog.showAndWait();
  }

  public void save(String filename) throws IOException {
    LOGGER.info("Saving campaign as {}", filename);
    this.filename = filename;
    ioStrategy.save(filename, campaign);
  }

  public void loadCampaign(String filename) throws IOException {
    LOGGER.info("Loading campaign from {}", filename);
    setCampaign(ioStrategy.load(filename));
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
  }
}
