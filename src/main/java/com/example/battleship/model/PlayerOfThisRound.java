package com.example.battleship.model;

public class PlayerOfThisRound {
    private final Player player;
    PlayingField playingField;

    public PlayerOfThisRound(Player player, PlayingField cell) {
        this.player = player;
        this.playingField = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayingField getPlayingField() {
        return playingField;
    }

    public void setPlayingField(PlayingField playingField) {
        this.playingField = playingField;
    }
}
