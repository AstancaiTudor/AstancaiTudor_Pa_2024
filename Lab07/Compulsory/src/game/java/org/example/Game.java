package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private final Bag bag = new Bag();
    private final List<Player> players = new ArrayList<>();

    public Game(int n)
    {
        bag.fillBag(n);
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        return bag.extractTiles(howMany);
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        for (Player player : players) {
            new Thread(player).start();
        }
    }

    public static void main(String args[]) {
        Game game = new Game(3);
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }
}
