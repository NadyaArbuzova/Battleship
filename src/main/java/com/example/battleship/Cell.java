package com.example.battleship;

import javafx.util.Pair;

import java.util.ArrayList;

public class Cell {
    private final ArrayList<ArrayList<String>> cell = new ArrayList<>();
    {
        for (int i = 0; i < 10; i ++){
            cell.add(new ArrayList<>());
            for (int j = 0; j<10; j++){
                cell.get(i).add("00"); //the first flag is a shot, the second flag is a ship
            }
        }
    }

    public ArrayList<ArrayList<String>> getCell() {
        return cell;
    }

    public void addShot(int x, int y) {
        cell.get(x).set(y, "1" + cell.get(x).get(y).charAt(1));
    }
    public void addShip(int x, int y) {
        cell.get(x).set(y, cell.get(x).get(y).charAt(0) + "1");
    }
    public void addShip(ArrayList<Pair<Integer,Integer>> shipCellsXY) {
        for (Pair<Integer, Integer> xy: shipCellsXY){
            addShip(xy.getKey(), xy.getValue());
        }
    }
}
