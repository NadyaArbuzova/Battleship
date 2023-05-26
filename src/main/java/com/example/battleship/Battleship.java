package com.example.battleship;

import com.example.battleship.models.FileSystemPlayer;
import com.example.battleship.models.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Battleship extends Application {

    public static Game game;
    public static FileSystemPlayer fileSystemPlayer = new FileSystemPlayer();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(new Scene(root,700, 400));
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
    @Override

    public void stop(){
        try {
            new FileSystemPlayer().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
