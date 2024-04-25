package org.example;

import java.util.*;

/**
 * Represent a game.
 * A game has a bag of tiles, a list of players and a number of tiles n.
 * The game is played by the players, who take turns extracting tiles from the bag.
 * The game ends when the bag is empty or when a time limit is reached.
 * The winner is the player with the longest sequence of tiles or the player with the most tiles extracted.
 * @author Tudor
 */
public class Game {
    private final Bag bag = new Bag();
    private final List<Player> players = new ArrayList<>();
    private final int n;
    private volatile boolean gameOver = false;
    private int currentPlayerIndex = 0;
    private final Object turnLock = new Object();
    private final long startTime = System.currentTimeMillis();
    private final long timeLimit = 300000;

    /**
     * Creates a new game with the specified number of tiles.
     * @param n the number of tiles in the game
     */
    public Game(int n) {
        this.n = n;
        bag.fillBag(n);
    }

    /**
     * Extracts a tile from the bag.
     * @return the extracted tile, or null if the bag is empty
     */
    public Tile extractTile() {
        synchronized (this) {
            if (gameOver || bag.isEmpty()) return null;
            return bag.extractTile();
        }
    }

    /**
     * Adds a player to the game.
     * @param player the player to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    /**
     * Plays the game.
     * The game is played by the players, who take turns extracting tiles from the bag.
     * The game ends when the bag is empty or when a time limit is reached.
     * The winner is the player with the longest sequence of tiles or the player with the most tiles extracted.
     */
    public void play() {
        startDaemonTimekeeper();
        List<Thread> threads = new ArrayList<>();
        for (Player player : players) {
            Thread thread = new Thread(player);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted: " + thread.getName());
            }
        }

        declareWinner();
    }

    /**
     * Starts a daemon timekeeper thread that ends the game when the time limit is reached.
     * The timekeeper thread prints the time elapsed every second.
     */
    private void startDaemonTimekeeper() {
        Thread timekeeper = new Thread(() -> {
            try {
                while (!gameOver && (System.currentTimeMillis() - startTime) < timeLimit) {
                    Thread.sleep(1000);
                    System.out.println("Game running for " + (System.currentTimeMillis() - startTime) / 1000 + " seconds.");
                }
                if (!gameOver) {
                    System.out.println("Time limit exceeded. Ending game...");
                    endGame();
                }
            } catch (InterruptedException e) {
                System.out.println("Timekeeper thread interrupted.");
            }
        });
        timekeeper.setDaemon(true);
        timekeeper.start();
    }

    /**
     * Ends the game.
     * Notifies all players that the game is over.
     */
    public void endGame() {
        gameOver = true;
        synchronized (turnLock) {
            turnLock.notifyAll();
        }
    }

    /**
     * Gets the number of tiles in the game.
     * @return the number of tiles in the game
     */
    public int getN() {
        return n;
    }

    /**
     * Waits for the player's turn.
     * @param player the player whose turn is waited for
     */
    public void waitForTurn(Player player) {
        synchronized (turnLock) {
            while (!gameOver && players.get(currentPlayerIndex) != player) {
                try {
                    turnLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(player.getName() + " was interrupted.");
                }
            }
        }
    }

    /**
     * Moves to the next player's turn.
     * Notifies all players that the turn has ended.
     */
    public void nextTurn() {
        synchronized (turnLock) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            turnLock.notifyAll();
        }
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Declares the winner of the game.
     * The winner is the player with the longest sequence of tiles or the player with the most tiles extracted.
     */
    public static void main(String args[]) {
        Game game = new Game(7);
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }

    /**
     * Declares the winner of the game.
     * The winner is the player with the longest sequence of tiles or the player with the most tiles extracted.
     * If no player has a sequence of tiles, the winner is the player with the most tiles extracted.
     * If no player has extracted any tiles, there is no winner.
     */
    public void declareWinner() {
        Player winner = null;
        int maxSequenceLength = 0;
        int maxTilesExtracted = 0;

        for (Player player : players) {
            int length = player.getLongestSequenceLength();
            if (length > maxSequenceLength) {
                maxSequenceLength = length;
                winner = player;
            }
        }

        if (maxSequenceLength == 0) {
            for (Player player : players) {
                int tilesCount = player.getTilesExtractedCount();
                if (tilesCount > maxTilesExtracted) {
                    maxTilesExtracted = tilesCount;
                    winner = player;
                }
            }
        }

        if (winner != null) {
            if (maxSequenceLength > 0) {
                System.out.println(winner.getName() + " wins with a sequence length of " + maxSequenceLength);
            } else {
                System.out.println(winner.getName() + " wins with the most tiles extracted: " + maxTilesExtracted);
            }
        } else {
            System.out.println("No winner!");
        }
    }

}
