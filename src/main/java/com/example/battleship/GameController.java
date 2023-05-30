package com.example.battleship;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.battleship.model.Round;
import com.example.battleship.model.PlayerOfThisRound;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Pair;

public class GameController {

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
    private String notWalkingNow = Round.getPlayer2().getPlayer().getName();

    @FXML
    void initialize() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cell1.add(new Pane(), i, j);
                cell2.add(new Pane(), i, j);
            }
        }
        playerName1.setText(Round.getPlayer1().getPlayer().getName());
        playerName2.setText(Round.getPlayer2().getPlayer().getName());
        turn(Round.getPlayer1(), cell2);
        turn(Round.getPlayer2(), cell1);
        backButton.setOnAction(event -> {
            closeWindow(backButton.getScene().getWindow());
        });
    }

    private void turn(PlayerOfThisRound player, GridPane cell){
        for (Node e : cell.getChildren()) {
            e.setOnMouseClicked(event -> {
                if (Objects.equals(notWalkingNow, player.getPlayer().getName())) {
                    boolean f = true;
                    e.relocate(e.getLayoutX(), e.getLayoutY());
                    int x = (int) (Math.round(e.getLayoutX()) / 25);
                    int y = (int) (Math.round(e.getLayoutY()) / 25);
                    if (!player.getPlayingField().isShot(x, y)) {
                        player.getPlayingField().addShot(x, y);
                        for (Pair<Integer, Integer> shots : player.getPlayingField().getCellShots()) {
                            cell.add(new ImageView(new Image("C:\\Users\\Nadya\\IdeaProjects\\Battleship\\src\\main\\resources\\com\\example\\battleship\\image\\miss.png", 25, 25, true, false)), shots.getKey(), shots.getValue());
                            for (Pair<Integer, Integer> ships : player.getPlayingField().getCellShips()) {
                                if (shots.equals(ships)) {
                                    f = false;
                                    player.getPlayingField().getCellShips().remove(ships);
                                    cell.add(new ImageView(new Image("C:\\Users\\Nadya\\IdeaProjects\\Battleship\\src\\main\\resources\\com\\example\\battleship\\image\\hit.png", 25, 25, true, false)), shots.getKey(), shots.getValue());
                                    break;
                                }
                            }
                        }
                        player.getPlayingField().getCellShots().removeAll(player.getPlayingField().getCellShots());
                    }
                    if (f) notWalkingNow = nextTurn(player);
                    if (player.getPlayingField().getCellShips().isEmpty()){
                        Battleship.fileSystemPlayer.winner(nextTurn(player));
                        Battleship.fileSystemPlayer.round(playerName1.getText(), playerName2.getText());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("End of the game!");
                        alert.setHeaderText(null);
                        alert.setContentText(nextTurn(player)+", you win! Congratulations");

                        alert.showAndWait();
                        closeWindow(e.getScene().getWindow());
                    }
                    e.setDisable(true);
                }
            });
        }
    }
    private String nextTurn(PlayerOfThisRound player){
        if (player.getPlayer().getName().equals(Round.getPlayer1().getPlayer().getName())) {
            playerName1.setFill(Paint.valueOf("#06a125"));
            playerName2.setFill(Paint.valueOf("#000000"));
            return Round.getPlayer2().getPlayer().getName();
        }
        playerName2.setFill(Paint.valueOf("#06a125"));
        playerName1.setFill(Paint.valueOf("#000000"));
        return Round.getPlayer1().getPlayer().getName();
    }
    private void closeWindow(Window window){
        window.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main-view.fxml"));
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

