module com.example.battleship {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.battleship to javafx.fxml;
    opens com.example.battleship.model to javafx.fxml;

    exports com.example.battleship;
    exports com.example.battleship.model;
}