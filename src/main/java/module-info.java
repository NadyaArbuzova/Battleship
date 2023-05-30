module com.example.battleship {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.battleship to javafx.fxml;
    opens com.example.battleship.models to javafx.fxml;

    exports com.example.battleship;
    exports com.example.battleship.models;
    exports com.example.battleship.controllers;
    opens com.example.battleship.controllers to javafx.fxml;
}