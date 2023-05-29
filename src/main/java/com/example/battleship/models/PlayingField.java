package com.example.battleship.models;

import com.example.battleship.PlayerOfThisRound;
import com.example.battleship.Ship;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.ArrayList;

public class PlayingField {
    private static class Cell {

        int x;
        int y;
        Ship ship = null;
        boolean putAShip = true;
        boolean shot = false;

        public boolean isPutAShip() {
            return putAShip;
        }

        public void setPutAShip(boolean putAShip) {
            this.putAShip = putAShip;
        }

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Cell(int x, int y, Ship ship) {
            this.x = x;
            this.y = y;
            this.ship = ship;
        }

        public Ship isShip() {
            return ship;
        }

        public void setShip(Ship ship) {
                this.ship = ship;
        }

        public boolean isShot() {
            return shot;
        }

        public void setShot(boolean shot) {
            this.shot = shot;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
    private final ArrayList<Pair<Integer, Integer>> cellShips = new ArrayList<>();
    private final ArrayList<Pair<Integer, Integer>> cellShots = new ArrayList<>();
    private final Cell[][] cellList = new  Cell[10][10];
    {
        for (int i = 0; i < 10; i++){
            cellList[i] = new  Cell[10];
            for (int j = 0; j < 10; j++){
                cellList[i][j] = new Cell(i, j);
            }
        }
    }


    public boolean isShot(int x, int y){ return cellList[x][y].isShot();}

    public ArrayList<Pair<Integer, Integer>> getCellShips() {
        return cellShips;
    }

    public ArrayList<Pair<Integer, Integer>> getCellShots() {
        return cellShots;
    }

    private void shot(int x, int y) {
        System.out.println(x+" "+y);
        if (!cellList[x][y].isShot()) {
            cellShots.add(new Pair<>(x, y));
            cellList[x][y].setShot(true);
            if (cellList[x][y].isShip()!=null){
                cellList[x][y].isShip().addHits();
            }
        }
    }
    public void addShot(int x, int y){
        shot(x, y);
        if (cellList[x][y].isShip() != null && cellList[x][y].isShip().shipKilled()){
            for (Pair<Integer,Integer> xy: cellList[x][y].isShip().getCellsAroundTheShipXY()){
                if(xy.getKey() < 10 && xy.getKey() >= 0 && xy.getValue() < 10 && xy.getValue() >= 0) shot(xy.getKey(),xy.getValue());
            }
        }
    }

    public boolean putAShip(Ship ship){
        for (Pair<Integer, Integer> xy : ship.getShipCellsXY()) {
            if ( xy.getKey() < 0 || xy.getKey() > 9 || xy.getValue() < 0 || xy.getValue() > 9
            || !cellList[xy.getKey()][xy.getValue()].putAShip)
                return false;
        }
        return true;
    }
    private void addShip(int x, int y ) {
        cellShips.add(new Pair<>(x, y));
    }

    public void addShip(Ship ship) {
        for (Pair<Integer, Integer> xy : ship.getShipCellsXY()) {
            addShip(xy.getKey(), xy.getValue());
            cellList[xy.getKey()][xy.getValue()].setShip(ship);
        }
        for (Pair<Integer, Integer> xy: ship.getCellsAroundTheShipXY()){
            if (xy.getKey()>=0 && xy.getKey()<10 && xy.getValue()>=0 && xy.getValue()<10) {
                cellList[xy.getKey()][xy.getValue()].setPutAShip(false);
            }
        }
    }

    private void removeShip(int x, int y) {
        cellShips.remove(new Pair<>(x, y));
    }

    public void removeShip(Ship ship) {
        for (Pair<Integer, Integer> xy : ship.getShipCellsXY()) {
            removeShip(xy.getKey(), xy.getValue());
            cellList[xy.getKey()][xy.getValue()].setShip(ship);
        }
    }
    public void removeAll(PlayerOfThisRound player) {
        player.setPlayingField(new PlayingField());
    }
//
//    public void removeAllShips(ArrayList<Pair<Integer, Integer>> shipCellsXY) {
//        cellShips = new ArrayList<>();
//    }

    //    public ArrayList<ArrayList<String>> getCell() {
//        return cell;
//    }
//
//    public void addShot(int x, int y) {
//        cell.get(x).set(y, "1" + cell.get(x).get(y).charAt(1));
//    }
//    public void addShip(int x, int y) {
//        cell.get(x).set(y, cell.get(x).get(y).charAt(0) + "1");
//    }
//    public void addShip(ArrayList<Pair<Integer,Integer>> shipCellsXY) {
//        for (Pair<Integer, Integer> xy: shipCellsXY){
//            addShip(xy.getKey(), xy.getValue());
//        }
//    }
//
//    public void removeShip(int x, int y) {
//        cell.get(x).set(y, cell.get(x).get(y).charAt(0) + "0");
//    }
//    public void removeShip(ArrayList<Pair<Integer,Integer>> shipCellsXY) {
//        for (Pair<Integer, Integer> xy: shipCellsXY){
//            addShip(xy.getKey(), xy.getValue());
//        }

}
