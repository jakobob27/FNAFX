package com.ing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameOverController {

    @FXML
    private VBox box;

    @FXML
    private Button button;

    @FXML
    private TextField textInput;

    @FXML AnchorPane anchor;


    @FXML
    void enteredText(ActionEvent event) {
        anchor.getChildren().remove(box);
        String name = textInput.getText();
        HighscoreEntry score = new HighscoreEntry(MainSceneController.getLevel(), name);
        HighscoreManagement.writeToFile(score);

        //bruk name for et metodekall for å legge til i highscorelist
        // navnet må ikke inneholde visse tegn, tegnet som splitter informasjonen. (,)
    }

}


