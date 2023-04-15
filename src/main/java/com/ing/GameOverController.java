package com.ing;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameOverController {

    @FXML
    private VBox box;

    @FXML
    private Button button;

    @FXML
    private TextField textInput;

    @FXML 
    private AnchorPane anchor;

    @FXML 
    private Label highscoreLabel;

    @FXML 
    private TableView<HighscoreEntry> highscoreTable;

    @FXML 
    private TableColumn<HighscoreEntry, String> leaderboardName;

    @FXML 
    private TableColumn<HighscoreEntry, Integer> leaderboardScore;

    @FXML 
    private TableColumn<HighscoreEntry, String> leaderboardDate;

    @FXML 
    private Label errorText;

    @FXML
    private void enteredText(ActionEvent event) {
        //anchor.getChildren().remove(box); 
        String name = textInput.getText();
        if (name.contains(",") || name.equals(" ")){
            errorText.setVisible(true);
            errorText.setText("Illegal name, please try again!");
            textInput.clear();
        }
        else {
        box.setVisible(false);
        HighscoreEntry score = new HighscoreEntry(MainSceneController.getLevel(), name);
        HighscoreManagement.writeToFile(score);

        leaderboardName.setCellValueFactory(new PropertyValueFactory<>("name"));
        leaderboardScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        leaderboardDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        highscoreTable.setItems(HighscoreManagement.getHighscoreList());
        highscoreTable.setVisible(true);
        highscoreTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        highscoreTable.getColumns().forEach(x -> x.setReorderable(false));
        
        highscoreLabel.setVisible(true);
        System.out.println(HighscoreManagement.getHighscoreList());
        }
        //bruk name for et metodekall for å legge til i highscorelist
        // navnet må ikke inneholde visse tegn, tegnet som splitter informasjonen. (,)
    }

}


