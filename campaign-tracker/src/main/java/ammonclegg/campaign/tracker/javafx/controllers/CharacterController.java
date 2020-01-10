package ammonclegg.campaign.tracker.javafx.controllers;

import ammonclegg.campaign.tracker.models.GameCharacter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static ammonclegg.campaign.tracker.models.GameCharacter.NAME;

/**
 * @author ammonclegg on 11/22/19.
 */
@Component
public class CharacterController implements PropertyChangeListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(CharacterController.class);

  private GameCharacter character;

  @FXML
  private TextField characterName;

  @FXML
  private TextArea characterDescription;

  @FXML
  public void initialize() {
    characterDescription.textProperty().addListener((observable, oldValue, newValue)->character.setDescription(newValue));
  }

  public void setCharacter(GameCharacter character) {
    if (this.character != null) {
      this.character.removePropertyChangeListener(this);
    }
    this.character = character;
    this.character.addPropertyChangeListener(this);
    loadCharacter();
  }

  private void loadCharacter() {
    updateName();
    updateDescriptionField();
  }

  private void updateName() {
    this.characterName.setText(character.getName());
  }

  private void updateDescriptionField() {
    this.characterDescription.setText(character.getDescription());
  }

//  @FXML
//  private void onCharacterNameEntry() {
//    LOGGER.info("Updating character name");
//    String text = characterName.getText();
//    character.setName(text);
//  }

  @FXML
  private void onUpdateDescription() {
    String text = characterDescription.getText();
    character.setDescription(text);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (NAME.equals(evt.getPropertyName())) {
      updateName();
    }
  }
}
