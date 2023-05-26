package com.example.battleship;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.battleship.models.FileSystemPlayer;
import com.example.battleship.models.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddPlayerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private TextField playerName;

    @FXML
    //private ComboBox<String> playerNameList = new ComboBox<>(FXCollections.observableArrayList(new FileSystemPlayer().getPlayers().keySet()));
    private ComboBox<String> playerNameList = new ComboBox<>();
    ObservableList<String> list = FXCollections.observableArrayList(new FileSystemPlayer().getPlayers().keySet());
    @FXML
    private Text text;

    @FXML
    void initialize() {
        playerNameList.setItems(list);
        playerNameList.setOnAction(event -> {
            playerName.setText(playerNameList.getValue());
        });
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
        nextButton.setOnAction(event -> {
            if (playerName.getText().isEmpty() || playerName.getText().contains(";")) {
                information("This name is invalid! Choose another name!");
            }else {
                Battleship.fileSystemPlayer.addPlayer(playerName.getText());
                Player player1 = Battleship.fileSystemPlayer.getPlayer(playerName.getText());
                text.setText("Player 2, enter a name or select from the list");
                nextButton.setOnAction(event1 -> {
                    if (playerName.getText().isEmpty() || playerName.getText().contains(";")) {
                        information("This name is invalid! Choose another name!");
                    } else {
                        if (!playerName.getText().equals(player1.getName())) {
                            Battleship.fileSystemPlayer.addPlayer(playerName.getText());
                            Player player2 = Battleship.fileSystemPlayer.getPlayer(playerName.getText());
                            Battleship.game = new Game(player1, player2);
                            nextButton.getScene().getWindow().hide();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("placement-of-ships.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Parent root = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } else {
                            information("This name is taken by Player 1. Choose another name!");
                        }
                    }
                });
            }
        });
    }
    private void information(String text){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(text);

            alert.showAndWait();
    }
}
