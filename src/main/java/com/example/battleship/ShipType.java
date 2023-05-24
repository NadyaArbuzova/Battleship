package com.example.battleship;

import javafx.scene.image.Image;

import java.util.Objects;

public class ShipType {
    private final int size;
    private final Image image;

    public ShipType(int size) {
        this.size = size;
        String file = switch (size) {
            case 1 -> Objects.requireNonNull(getClass().getResource("c1.png")).toString();
            case 2 -> Objects.requireNonNull(getClass().getResource("c2.png")).toString();
            case 3 -> Objects.requireNonNull(getClass().getResource("c3.png")).toString();
            case 4 -> Objects.requireNonNull(getClass().getResource("c4.png")).toString();
            default -> null;
        };
        image = new Image(Objects.requireNonNull(file));
    }

    public int getSize() {
        return size;
    }

    public Image getImage() {
        return image;
    }
}
