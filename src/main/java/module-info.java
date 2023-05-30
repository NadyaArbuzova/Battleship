module com.example.battleship {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.battleship to javafx.fxml;
    opens com.example.battleship.model to javafx.fxml;

    exports com.example.battleship;
    exports com.example.battleship.model;
    exports com.example.battleship.controller;
    opens com.example.battleship.controller to javafx.fxml;
    exports com.example.battleship.view;
    opens com.example.battleship.view to javafx.fxml;
}