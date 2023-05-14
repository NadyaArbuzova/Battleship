package com.example.battleship.models;

import com.example.battleship.Player;

import java.io.*;
import java.util.HashMap;

interface ModelPlayer {
    void addPlayer(String playerName) throws IOException;
    void deletePlayer(String playerName) throws IOException;
    Player getPlayer(String playerName);
    HashMap<String,Player> getPlayers() throws IOException;

    void winner(String playerName);

    void round(String player1, String player2);

    void close() throws IOException;
}
public class FileSystemPlayer implements ModelPlayer {
    private static final File file = new File("Players");
    private static final HashMap<String,Player> players = new HashMap<>();

    static {
        try {
                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);
                String line = reader.readLine();
                while (line != null) {
                    String[] player = line.split(" ");
                    if (player.length == 3) players.put(player[0], new Player(player[0], Integer.parseInt(player[1]), Integer.parseInt(player[2])));
                    line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPlayer(String playerName) {
        if (!players.containsKey(playerName)) {
            Player player = new Player(playerName);
            players.put(playerName, player);
        }
    }

    @Override
    public void deletePlayer(String playerName) {
        players.remove(playerName);
    }

    @Override
    public Player getPlayer(String playerName) {
        return players.get(playerName);
    }

    @Override
    public HashMap<String,Player> getPlayers() {
        return players;
    }

    @Override
    public void winner(String playerName) {
        players.get(playerName).setWins();
    }

    @Override
    public void round(String player1, String player2) {
        players.get(player1).setRounds();
        players.get(player2).setRounds();
    }

    @Override
    public void close() throws IOException {
        FileWriter writer = new FileWriter(file);
        for (Player player: players.values()) writer.write(player.getName()+" "+ player.getWins()+" "+player.getRounds()+"\n");
        writer.close();
    }
}
