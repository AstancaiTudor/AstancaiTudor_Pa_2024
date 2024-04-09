package org.example;
import java.util.*;

public class Bag {
    private final List<Tile> tiles = Collections.synchronizedList(new LinkedList<>());

    public void fillBag(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    tiles.add(new Tile(i, j));
                }
            }
        }
        Collections.shuffle(tiles);
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        int tilesToExtract = Math.min(howMany, tiles.size());
        for (int i = 0; i < tilesToExtract; i++) {
            extracted.add(tiles.removeFirst());
        }
        return extracted;
    }

}
