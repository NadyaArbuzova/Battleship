package com.example.battleship.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.battleship.view.Battleship;
import com.example.battleship.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button playButton;

    @FXML
    private Button statisticsButton;


    @FXML
    void initialize() {
        playButton.setOnAction(event -> {
            playButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addPlayer.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        statisticsButton.setOnAction(event -> {
            ObservableList<Player> players = FXCollections.observableArrayList();
            players.addAll(Battleship.fileSystemPlayer.getPlayers().values());
            TableView<Player> table = new TableView<Player>();
            TableColumn<Player, String> name = new TableColumn<>("Name");
            name.setMaxWidth(200);
            name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
            TableColumn<Player, String> rounds = new TableColumn<>("Rounds");
            rounds.setMaxWidth(50);
            rounds.setCellValueFactory(new PropertyValueFactory<Player, String>("rounds"));
            TableColumn<Player, String> wins = new TableColumn<>("Wins");
            wins.setMaxWidth(50);
            wins.setCellValueFactory(new PropertyValueFactory<Player, String>("wins"));
            table.getColumns().addAll(name, rounds, wins);
            table.setItems(players);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(table);
            Scene scene = new Scene(vBox);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
        });
    }
}


