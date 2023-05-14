package com.example.battleship;

public class Player {
    private String name;
    private int wins;
    private int rounds;

    public Player(String name) {
        this.name = name;
        wins = 0;
        rounds = 0;
    }

    public Player(String name, int wins, int rounds) {
        this.name = name;
        this.wins = wins;
        this.rounds = rounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins() {
        this.wins++;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds() {
        this.rounds++;
    }
}
