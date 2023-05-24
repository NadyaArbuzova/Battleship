package com.example.battleship;

public class Game {
    private static PlayerOfThisRound player1;
    private static PlayerOfThisRound player2;

    public Game(Player player1, Player player2) {
        Game.player1 = new PlayerOfThisRound(player1, new Cell());
        Game.player2 = new PlayerOfThisRound(player2, new Cell());
    }
}
