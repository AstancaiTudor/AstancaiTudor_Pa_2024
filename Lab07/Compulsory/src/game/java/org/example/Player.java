package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private String name;
    private Game game;
    private volatile boolean running = true;
    private List<Tile> tiles = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void run() {
        while (running) {
            List<Tile> extractedTiles = game.extractTiles(1);
            if (!extractedTiles.isEmpty()) {
                Tile tile = extractedTiles.getFirst();
                tiles.add(tile);

                System.out.println(name + " extracted " + tile);
            } else {
                running = false;
            }

            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(name + " was interrupted.");
                running = false;
            }
        }
        System.out.println(name + " has finished with tiles: " + tiles);
    }

    public void setGame(Game game) {
        this.game = game;
        this.running = true;
    }

}
