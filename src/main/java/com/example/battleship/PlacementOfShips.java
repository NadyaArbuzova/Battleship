package com.example.battleship;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.battleship.model.Round;
import com.example.battleship.model.PlayerOfThisRound;
import com.example.battleship.model.Ship;
import com.example.battleship.model.ShipType;
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
    private Button nextButton;

    @FXML
    private Button rotateButton;

    @FXML
    private Button randomButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Text text;

    ShipType shipType = null;
    ArrayList<ShipType> shipTypeList = new ArrayList<>();


    @FXML
    void initialize() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cell.add(new Pane(), i, j);
            }
        }
        placement(Round.getPlayer1());
        nextButton.setOnAction(event -> {
            if (Round.getPlayer1().getPlayingField().getCellShips().toArray().length == 20) {
                placement(Round.getPlayer2());
                nextButton.setOnAction(event1 -> {
                    if (Round.getPlayer2().getPlayingField().getCellShips().toArray().length == 20){
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

    private void placement(PlayerOfThisRound player){
        text.setText(player.getPlayer().getName()+", arrange the ships on the field!");
        clear(player);
        final boolean[] rotate = {false};
        rotateButton.setOnAction(event -> {
            if (shipType!=null){
                rotate[0] = !rotate[0];
                if (!rotate[0]) rotateButton.setStyle("-fx-background-color: #b5e2ef");
                else rotateButton.setStyle("-fx-background-color: #76ec9a");
            }
        });

        randomButton.setOnAction(event -> {
            clear(player);
            flowPane.getChildren().removeAll(flowPane.getChildren());
            for (ShipType s: shipTypeList) {
                shipType = s;
                Rectangle rectangle = shipType.copy().getRectangle();
                int x;
                int y;
                Ship ship;
                do {
                    x = (int) (Math.random() * 10);
                    y = (int) (Math.random() * 10);
                    ship = new Ship(shipType, x, y, new Random().nextBoolean());
                } while (!player.getPlayingField().putAShip(ship));
                addShip(ship.getX(), ship.getY(), player, ship.getRotate());
                rectangle.setFill(Paint.valueOf("#b5d0ea"));
                rectangle.setStroke(Paint.valueOf("#000000"));
                flowPane.getChildren().add(rectangle);
            }
        });

        deleteButton.setOnAction(event -> clear(player));

        for (Node e: cell.getChildren()){
            e.setOnMouseClicked(event -> {
                e.relocate(e.getLayoutX(), e.getLayoutY());
                int x = (int) (Math.round(e.getLayoutX())/27);
                int y = (int) (Math.round(e.getLayoutY())/27);
                addShip(x, y, player, rotate[0]);
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
            this.shipTypeList.add(shipTypeList[i]);
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

    private void clear(PlayerOfThisRound player){
        cell.getChildren().remove(101, cell.getChildren().toArray().length);
        flowPane.getChildren().removeAll(flowPane.getChildren());
        shipType = null;
        shipTypeList = new ArrayList<>();
        player.getPlayingField().removeAll(player);
        shipList(new ShipType[1]);
        shipList(new ShipType[2]);
        shipList(new ShipType[3]);
        shipList(new ShipType[4]);
    }

    private void addShip(int x, int y, PlayerOfThisRound player, boolean rotate){
        if (shipType != null) {
            Ship ship = new Ship(shipType, x, y, rotate);
            if (player.getPlayingField().putAShip(ship)) {
                player.getPlayingField().addShip(ship);
                Rectangle rectangle;
                if (rotate) {
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
    }

}


