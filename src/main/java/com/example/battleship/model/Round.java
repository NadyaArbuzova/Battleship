package com.example.battleship.model;

public class Round {
    private static PlayerOfThisRound player1;
    private static PlayerOfThisRound player2;

    public Round(Player player1, Player player2) {
        Round.player1 = new PlayerOfThisRound(player1, new PlayingField());
        Round.player2 = new PlayerOfThisRound(player2, new PlayingField());
    }

    public static PlayerOfThisRound getPlayer1() {
        return player1;
    }

    public static PlayerOfThisRound getPlayer2() {
        return player2;
    }
}
