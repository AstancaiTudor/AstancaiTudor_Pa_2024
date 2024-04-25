package org.example;

import java.util.*;

/**
 * Represents a player in the game.
 * A player extracts tiles from the game and tries to form the longest sequence of tiles.
 * The player extracts between 1 and 2 tiles per turn and tries to form the longest possible sequence of tiles.
 * The player is interrupted if the game is over or if there are no more tiles to extract.
 * @author Tudor
 */
public class Player implements Runnable {
    private String name;
    private Game game;
    private volatile boolean running = true;
    private List<Tile> tiles = new ArrayList<>();
    private List<Tile> longestSequence = new ArrayList<>();
    private int tilesExtractedCount = 0;

    /**
     * Creates a new player with the specified name.
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * The player's behavior during the game.
     * The player waits for their turn, extracts tiles, and tries to form the longest sequence of tiles.
     * The player is interrupted if the game is over or if there are no more tiles to extract.
     * The player sleeps for 100 milliseconds between turns.
     * If the player forms the longest possible sequence, the game ends.
     * If the player is interrupted, the player stops running.
     * If the player finishes, the player prints the tiles they extracted.
     */
    @Override
    public void run() {
        Random random = new Random();
        while (running && !Thread.currentThread().isInterrupted()) {
            game.waitForTurn(this);

            if (game.isGameOver()) {
                break;
            }

            int numTilesToExtract = 1 + random.nextInt(2);
            for (int i = 0; i < numTilesToExtract; i++) {
                Tile extractedTile = game.extractTile();
                if (extractedTile == null) {
                    running = false;
                    break;
                }

                tiles.add(extractedTile);
                tilesExtractedCount++;
                System.out.println(name + " extracted " + extractedTile);

                List<Tile> currentSequence = findLongestPath();
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = currentSequence;
                }

                if (longestSequence.size() == game.getN()) {
                    System.out.println(name + " has formed the longest possible sequence: " + longestSequence);
                    game.endGame();
                    break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(name + " was interrupted.");
                running = false;
            } finally {
                game.nextTurn();
            }
        }
        System.out.println(name + " has finished with tiles: " + tiles);
    }

    /**
     * Finds the longest path in the graph of tiles.
     * The graph is represented as an adjacency list.
     * The method uses depth-first search to find the longest path.
     * The method returns the longest path found.
     * @return the longest path in the graph of tiles
     */
    private List<Tile> findLongestPath() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (Tile tile : tiles) {
            graph.computeIfAbsent(tile.getFirstNumber(), k -> new ArrayList<>()).add(tile.getSecondNumber());
        }

        List<Tile> longestPath = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (Integer startNode : graph.keySet()) {
            List<Tile> currentPath = new ArrayList<>();
            findPath(startNode, graph, visited, currentPath, longestPath);
        }

        return longestPath;
    }

    /**
     * Finds the longest path starting from the specified node.
     * The method uses depth-first search to find the longest path.
     * @param current the current node
     * @param graph the graph of tiles
     * @param visited the set of visited nodes
     * @param currentPath the current path
     * @param longestPath the longest path found so far
     */
    private void findPath(int current, Map<Integer, List<Integer>> graph, Set<Integer> visited, List<Tile> currentPath, List<Tile> longestPath) {
        visited.add(current);

        if (graph.containsKey(current)) {
            for (Integer neighbor : graph.get(current)) {
                Tile newTile = new Tile(current, neighbor);
                currentPath.add(newTile);

                if (!visited.contains(neighbor)) {
                    findPath(neighbor, graph, visited, currentPath, longestPath);
                }

                if (currentPath.size() > longestPath.size()) {
                    longestPath.clear();
                    longestPath.addAll(currentPath);
                }

                currentPath.remove(currentPath.size() - 1);
            }
        }

        visited.remove(current);
    }

    /**
     * Returns the number of tiles extracted by the player.
     * @return the number of tiles extracted by the player
     */
    public int getTilesExtractedCount() {
        return tilesExtractedCount;
    }

    /**
     * Returns the longest sequence of tiles formed by the player.
     * @return the longest sequence of tiles formed by the player
     */
    public int getLongestSequenceLength() {
        return longestSequence.size();
    }

    /**
     * Sets the game for the player.
     * @param game the game to be set for the player
     */
    public void setGame(Game game) {
        this.game = game;
        this.running = true;
    }

    /**
     * Returns the name of the player.
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
}
