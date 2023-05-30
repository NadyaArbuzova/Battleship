package com.example.battleship.model;

public class Game {
    private static PlayerOfThisRound player1;
    private static PlayerOfThisRound player2;

    public Game(Player player1, Player player2) {
        Game.player1 = new PlayerOfThisRound(player1, new PlayingField());
        Game.player2 = new PlayerOfThisRound(player2, new PlayingField());
    }

    public static PlayerOfThisRound getPlayer1() {
        return player1;
    }

    public static PlayerOfThisRound getPlayer2() {
        return player2;
    }
}
