package com.example.battleship.models;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemPlayerTest {
    FileSystemPlayer fileSystemPlayer = new FileSystemPlayer();

    @BeforeEach
    void addPlayer() {
        fileSystemPlayer.addPlayer("name1");
        fileSystemPlayer.addPlayer("name2");
    }
    @AfterEach
    void delete() throws IOException {
        File file = new File("Players");
        FileWriter writer = new FileWriter(file);
        writer.write("");
        writer.close();
    }

    @Test
    void deletePlayer() {
        fileSystemPlayer.deletePlayer("name2");
        assertEquals("[name1]", fileSystemPlayer.getPlayers().keySet().toString());
    }

    @Test
    void getPlayer() {
        Player player = fileSystemPlayer.getPlayer("name1");
        assertEquals("name1 0 0", player.getName() + " " + player.getWins() + " " + player.getRounds());
    }

    @Test
    void winnerAndRound() {
        fileSystemPlayer.round("name1", "name2");
        fileSystemPlayer.winner("name1");
        assertEquals(1, fileSystemPlayer.getPlayers().get("name1").getRounds());
        assertEquals(1, fileSystemPlayer.getPlayers().get("name1").getWins());
    }

    @Test
    void close() throws IOException {
        fileSystemPlayer.close();
        File file = new File("Players");
        String s = Files.readString(Paths.get(file.getPath()));
        assertEquals("name2 0 0\n" +
                "name1 0 0\n", s);
        fileSystemPlayer.addPlayer("name3");
        fileSystemPlayer.close();
        s = Files.readString(Paths.get(file.getPath()));
        assertEquals("name3 0 0\n" +
                "name2 0 0\n" +
                "name1 0 0\n", s);
    }
}