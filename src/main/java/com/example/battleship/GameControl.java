package com.example.battleship;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.battleship.models.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

public class GameControl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private GridPane cell1;

    @FXML
    private GridPane cell2;

    @FXML
    private Text playerName1;

    @FXML
    private Text playerName2;

    @FXML
    void initialize() {
        playerName1.setText(Game.getPlayer1().getPlayer().getName());
        playerName2.setText(Game.getPlayer2().getPlayer().getName());
        turn(Game.getPlayer1(), cell1);
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
    }

    private void turn(PlayerOfThisRound player, GridPane cell){
        final boolean[] hit = {false};
        for (Node e : cell.getChildren()) {
            e.setOnMouseClicked(event -> {
                e.relocate(e.getLayoutX(), e.getLayoutY());
                int x = (int) (Math.round(e.getLayoutX()) / 27);
                int y = (int) (Math.round(e.getLayoutY()) / 27);
                if (!player.getPlayingField().isShot(x, y)) {
                    player.getPlayingField().addShot(x, y);
                    for (Pair<Integer,Integer> shots: player.getPlayingField().getCellShots()){
                        for (Pair<Integer,Integer> ships: player.getPlayingField().getCellShips()){
                            if (shots.equals(ships)) {
                                hit[0] = true;
                                player.getPlayingField().getCellShips().remove(ships);
                                cell.add(new Circle(5), shots.getKey(), shots.getValue());
                                player.getPlayingField().getCellShots().remove(shots);
                                break;
                            }
                            cell.add(new Circle(5), shots.getKey(), shots.getValue());

                        }
                    }
                }
            });
        }
    }
    private PlayerOfThisRound whichPlayerIsWalking(){
        return Game.getPlayer1();
    }
    private void shipKilled(){}

}

