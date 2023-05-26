package com.example.battleship;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.battleship.models.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlacementOfShips {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private GridPane cell;

    @FXML
    private FlowPane flowPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button nextButton;

    @FXML
    private Button rotateButton;

    @FXML
    private Text text;

    @FXML
    void initialize() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cell.add(new Pane(), i, j);
            }
        }
        placement(Game.getPlayer1());
        nextButton.setOnAction(event -> {
            if (Game.getPlayer1().getPlayingField().getCellShips().toArray().length == 20) {
                cell.getChildren().remove(101, cell.getChildren().toArray().length);
                flowPane.getChildren().removeAll(flowPane.getChildren());
                placement(Game.getPlayer2());
                nextButton.setOnAction(event1 -> {
                    if (Game.getPlayer2().getPlayingField().getCellShips().toArray().length == 20){
                        nextButton.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("game.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    }else information("Arrange all the ships before continuing!");
                });
            } else {
                information("Arrange all the ships before continuing!");
            }
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
    }

    ShipType shipType = null;
    private void placement(PlayerOfThisRound player){
        text.setText(player.getPlayer().getName()+", arrange the ships on the field!");
        shipList(new ShipType[1]);
        shipList(new ShipType[2]);
        shipList(new ShipType[3]);
        shipList(new ShipType[4]);
        final boolean[] rotate = {false};
        rotateButton.setOnAction(event -> {
            if (shipType!=null){
                rotate[0] = !rotate[0];
                if (!rotate[0]) rotateButton.setStyle("-fx-background-color: #b5e2ef");
                else rotateButton.setStyle("-fx-background-color: #76ec9a");
            }
        });
        for (Node e: cell.getChildren()){
            e.setOnMouseClicked(event -> {
                e.relocate(e.getLayoutX(), e.getLayoutY());
                int x = (int) (Math.round(e.getLayoutX())/27);
                int y = (int) (Math.round(e.getLayoutY())/27);
                if (shipType != null) {
                    Ship ship = new Ship(shipType, x, y, rotate[0]);
                    if (player.getPlayingField().putAShip(ship)) {
                        player.getPlayingField().addShip(ship);
                        Rectangle rectangle;
                        if (rotate[0]) {
                            rectangle = new Rectangle(27, 27 * shipType.getSize(), Paint.valueOf("DODGERBLUE"));
                            cell.add(rectangle, x, y, 1, shipType.getSize());

                        } else {
                            rectangle = new Rectangle(27 * shipType.getSize(), 27, Paint.valueOf("DODGERBLUE"));
                            cell.add(rectangle, x, y, shipType.getSize(), 1);

                        }
                        shipType = null;
                    }else {
                        information("You can't put a ship here!");
                    }
                }
            });
        }
    }
    void shipList(ShipType[] shipTypeList){
        int size = 5-shipTypeList.length;
        for (int i = 0; i < shipTypeList.length; i++){
            Rectangle rectangle = new Rectangle(27*size, 27, Paint.valueOf("DODGERBLUE"));
            shipTypeList[i] = new ShipType(size, rectangle);
            //shipTypeList[i].getRectangle().setOnMouseClicked(eventHandler);
            flowPane.getChildren().add(shipTypeList[i].getRectangle());
            ShipType shipType = shipTypeList[i];
            shipType.getRectangle().setOnMouseClicked(event -> {
                if (this.shipType==null) {
                    this.shipType = shipType.copy();
//                pane.getChildren().add(this.shipType.getRectangle());
//                this.shipType.getRectangle().setX(600);
//                this.shipType.getRectangle().setY(200);
                    shipType.getRectangle().setFill(Paint.valueOf("#b5d0ea"));
                    shipType.getRectangle().setStroke(Paint.valueOf("#000000"));
                    shipType.getRectangle().setDisable(true);
                }
            });
        }
    }
    private void information(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }
}


