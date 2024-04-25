package org.example;

import java.util.*;

/**
 * Represents a bag of tiles.
 * A bag contains a list of tiles that can be extracted.
 * The bag is filled with tiles that have numbers from 1 to n, where n is the number of tiles in the bag.
 * @author Tudor
 */
public class Bag {
    private final List<Tile> tiles = new LinkedList<>();

    /**
     * Fills the bag with tiles that have numbers from 1 to n.
     * @param n the number of tiles to be added to the bag
     */
    public synchronized void fillBag(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    tiles.add(new Tile(i, j));
                }
            }
        }
        Collections.shuffle(tiles);
    }

    /**
     * Extracts a tile from the bag.
     * @return the extracted tile, or null if the bag is empty
     */
    public synchronized Tile extractTile() {
        if (!tiles.isEmpty()) {
            return tiles.remove(0);
        }
        return null;
    }

    /**
     * Checks if the bag is empty.
     * @return true if the bag is empty, false otherwise
     */
    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}
