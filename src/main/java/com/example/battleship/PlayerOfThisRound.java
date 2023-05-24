package com.example.battleship;

public class PlayerOfThisRound {
    private final Player player;
    Cell cell;

    public PlayerOfThisRound(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
