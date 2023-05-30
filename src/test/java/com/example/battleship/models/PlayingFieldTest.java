package com.example.battleship.models;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayingFieldTest {
    PlayingField playingField = new PlayingField();
    {
        playingField.addShip(new Ship(4, 0, 0, false));
        playingField.addShip(new Ship(3, 0, 5, false));
        playingField.addShip(new Ship(3, 2, 0, false));
        playingField.addShip(new Ship(2, 2, 4, false));
        playingField.addShip(new Ship(1, 4, 3, false));
        playingField.addShot(0, 0);
        playingField.addShot(1, 1);
        playingField.addShot(0,2);
        playingField.addShot(9,9);
    }

    @Test
    void isShot() {
        assertTrue(playingField.isShot(0, 0));
        assertTrue(playingField.isShot(1, 1));
        assertFalse(playingField.isShot(4, 3));
        assertFalse(playingField.isShot(8, 8));
    }

    @Test
    void putAShip() {
        assertTrue(playingField.putAShip(new Ship(1, 7, 7, false)));
        assertFalse(playingField.putAShip(new Ship(1, 4, 3, false)));
    }

    @Test
    void removeShip() {
        playingField.removeShip(new Ship(1, 4, 3, false));
        assertTrue(playingField.putAShip(new Ship(1, 4, 3, false)));
    }
}