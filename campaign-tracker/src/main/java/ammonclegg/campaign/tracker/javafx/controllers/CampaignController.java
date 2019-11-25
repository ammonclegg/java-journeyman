package ammonclegg.campaign.tracker.javafx.controllers;

import ammonclegg.campaign.tracker.javafx.StageFactory;
import ammonclegg.campaign.tracker.helpers.IOStrategy;
import ammonclegg.campaign.tracker.models.Campaign;
import ammonclegg.campaign.tracker.models.GameCharacter;
import ammonclegg.campaign.tracker.models.GameObject;
import ammonclegg.campaign.tracker.models.Location;
import ammonclegg.campaign.tracker.models.implementations.NonPlayerCharacter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
  private Button addLocationButton;

  private ObservableList<Location> locations;

  @FXML
  private ListView<Location> locationListView;

  @FXML
  private Button addCharacterButton;

  private ObservableList<GameCharacter> characters;

  @FXML
  private ListView<GameCharacter> characterListView;

  @FXML
  private AnchorPane characterView;

  @FXML
  private CharacterController characterController;

  @FXML
  private MenuBar menuBar;

  @Autowired
  public CampaignController(IOStrategy ioStrategy) {
    this(ioStrategy, new Campaign());
  }

  CampaignController(IOStrategy ioStrategy, Campaign campaign) {
    this.ioStrategy = ioStrategy;
    setCampaign(campaign);
    characters = FXCollections.observableArrayList(campaign.getCharacters());
    locations = FXCollections.observableArrayList(campaign.getLocations());
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Campaigns", ".cmp"));
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
    textInputDialog = new TextInputDialog();
    characterListView.setCellFactory(param->new ListCell<GameCharacter>() {
      @Override
      public void updateItem(GameCharacter item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        }
        if (item != null) {
          setText(item.getName());
        }
      }
    });

    locationListView.setCellFactory(param->new ListCell<Location>() {
      @Override
      public void updateItem(Location item, boolean empty) {
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
      characterListView.setItems(campaign.getCharacters());
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

  private Optional<String> getStringValueFromUser(String header, String content) {
    textInputDialog.setHeaderText(header);
    textInputDialog.setContentText(content);
    textInputDialog.setGraphic(null);
    return textInputDialog.showAndWait();
  }

  @FXML
  private void addCharacter() {
    LOGGER.info("Adding new character");
    Optional<String> name = getStringValueFromUser("Create Character", "Enter a name");
    if (name.isPresent() && !name.get().isEmpty()) {
      LOGGER.info("Adding character to campaign.");
      campaign.addCharacter(new NonPlayerCharacter(name.get()));
    }
    else {
      LOGGER.info("Aborting add new character. Name not provided.");
    }
  }

  @FXML
  private void addLocation() {
    LOGGER.info("Adding new location");
    Optional<String> name = getStringValueFromUser("Create Location", "Enter a name");
    if (name.isPresent() && !name.get().isEmpty()) {
      LOGGER.info("Adding location to campaign.");
      campaign.addLocation(new Location(name.get()));
    }
    else {
      LOGGER.info("Aborting add new location. Name not provided.");
    }

  }

  private List<String> getCharacterNames() {
    if (campaign != null) {
      return campaign.getCharacters().stream().map(GameObject::getName).collect(Collectors.toList());
    }
    else {
      return Collections.emptyList();
    }
  }

  public void save(String filename) throws IOException {
    this.filename = filename;
    ioStrategy.save(filename, campaign);
  }

  public void loadCampaign(String filename) throws IOException {
    setCampaign(ioStrategy.load(filename));
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
  }
}
