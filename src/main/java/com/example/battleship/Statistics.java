package com.example.battleship;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Statistics {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button button;

    @FXML
    private TableView<Player> table;
    @FXML
    private TableColumn<Player, String> name;

    @FXML
    private TableColumn<Player, String> rounds;

    @FXML
    private TableColumn<Player, String> wins;

    @FXML
    void initialize(){
        ObservableList<Player> players = FXCollections.observableArrayList();
        players.addAll(Battleship.fileSystemPlayer.getPlayers().values());
        table = new TableView<Player>();
        name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        rounds.setCellValueFactory(new PropertyValueFactory<Player, String>("rounds"));
        wins.setCellValueFactory(new PropertyValueFactory<Player, String>("wins"));
        table.getColumns().add(name);
        table.getColumns().add(rounds);
        table.getColumns().add(wins);
        table.setItems(players);
        button.setOnAction(event1 -> {
            button.getScene().getWindow().hide();
        });
    }
}
