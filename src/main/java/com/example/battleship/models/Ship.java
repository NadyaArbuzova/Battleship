package com.example.battleship.models;


import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.ArrayList;

public class Ship {
    private final ShipType shipType;
    private final int x;
    private final int y;
    private final boolean rotate;
    private int hits = 0;
    public int getHits() {
        return hits;
    }
    public void addHits() {
        this.hits++;
    }

    private final ArrayList<Pair<Integer,Integer>> shipCellsXY;
    private final ArrayList<Pair<Integer,Integer>> cellsAroundTheShipXY;

    public Ship(int size, Rectangle rectangle, int x, int y, boolean rotate) {
        this.x = x;
        this.y = y;
        this.rotate = rotate;
        shipType = new ShipType(size, rectangle);
        shipCellsXY = new ArrayList<>();
        cellsAroundTheShipXY = new ArrayList<>();
        if (!rotate) {
            for (int i = 0; i<size; i++){
                shipCellsXY.add(new Pair<>(x+i, y));
            }
            for (int i = -1; i <=size; i++){
                cellsAroundTheShipXY.add(new Pair<>(x+i, y-1));
                cellsAroundTheShipXY.add(new Pair<>(x+i, y));
                cellsAroundTheShipXY.add(new Pair<>(x+i, y+1));
            }
        }
        else  {
            for (int i = 0; i<size; i++){
                shipCellsXY.add(new Pair<>(x, y+i));
            }
            for (int i = -1; i <=size; i++){
                cellsAroundTheShipXY.add(new Pair<>(x-i, y-1));
                cellsAroundTheShipXY.add(new Pair<>(x-i, y));
                cellsAroundTheShipXY.add(new Pair<>(x-i, y+1));
            }
        }

//        if (angle == 90 && y + size <= 9) {
//            for (int i = 0; i<size; i++){
//                shipCellsXY.add(new Pair<>(x, y+i));
//            }
//            for (int i = -1; i <=size; i++){
//                cellsAroundTheShipXY.add(new Pair<>(x-1, y+i));
//                cellsAroundTheShipXY.add(new Pair<>(x, y+i));
//                cellsAroundTheShipXY.add(new Pair<>(x+1, y+i));
//            }
//        }
//        if (angle == 270 && y - size>=0) {
//            for (int i = 0; i<size; i++){
//                shipCellsXY.add(new Pair<>(x, y-i));
//            }
//            for (int i = -1; i <=size; i++){
//                cellsAroundTheShipXY.add(new Pair<>(x-1, y-i));
//                cellsAroundTheShipXY.add(new Pair<>(x, y-i));
//                cellsAroundTheShipXY.add(new Pair<>(x+1, y-i));
//            }
//        }

    }
    public Ship(ShipType shipType, int x, int y, boolean rotate) {
        this.x = x;
        this.y = y;
        this.rotate = rotate;
        this.shipType = shipType;
        shipCellsXY = new ArrayList<>();
        cellsAroundTheShipXY = new ArrayList<>();
        int size = shipType.getSize();
        if (!rotate) {
            for (int i = 0; i < size; i++) {
                shipCellsXY.add(new Pair<>(x + i, y));
            }
            for (int i = -1; i <= size; i++) {
                cellsAroundTheShipXY.add(new Pair<>(x + i, y - 1));
                cellsAroundTheShipXY.add(new Pair<>(x + i, y));
                cellsAroundTheShipXY.add(new Pair<>(x + i, y + 1));
            }
        } else {
            for (int i = 0; i < size; i++) {
                shipCellsXY.add(new Pair<>(x, y+i));
            }
            for (int i = -1; i <= size; i++) {
                cellsAroundTheShipXY.add(new Pair<>(x - 1, y + i));
                cellsAroundTheShipXY.add(new Pair<>(x, y + i));
                cellsAroundTheShipXY.add(new Pair<>(x + 1, y + i));
            }
        }
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean getRotate() {
        return rotate;
    }

    public ArrayList<Pair<Integer, Integer>> getShipCellsXY() {
        return shipCellsXY;
    }

    public ArrayList<Pair<Integer, Integer>> getCellsAroundTheShipXY() {
        return cellsAroundTheShipXY;
    }

    public ShipType getShipType() {
        return shipType;
    }
    public boolean shipKilled(){
        return hits==shipType.getSize();
    }
}
