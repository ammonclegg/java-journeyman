package ammonclegg.campaign.tracker.javafx.controllers;

import ammonclegg.campaign.tracker.models.GameCharacter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static ammonclegg.campaign.tracker.models.GameCharacter.NAME;

/**
 * @author ammonclegg on 11/22/19.
 */
@Component
public class CharacterController implements PropertyChangeListener {

  private GameCharacter character;

  @FXML
  private Label characterName;

  @FXML
  public void initialize() {

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
  }

  private void updateName() {
    this.characterName.setText(character.getName());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (NAME.equals(evt.getPropertyName())) {
      updateName();
    }
  }
}
