package org.example;

/**
 * Represents a tile in the game.
 * A tile has two numbers, firstNumber and secondNumber.
 * @author Tudor
 */
public class Tile {
    private final int firstNumber;
    private final int secondNumber;

    /**
     * Creates a new tile with the specified numbers.
     * @param firstNumber the first number of the tile
     * @param secondNumber the second number of the tile
     */
    public Tile(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }


    /**
     * Returns the sum of the two numbers on the tile.
     * @return the sum of the two numbers on the tile
     */
    @Override
    public String toString() {
        return "(" + firstNumber + ", " + secondNumber + ")";
    }

    /**
     * Returns the first number of the tile.
     * @return the first number of the tile
     */
    public int getFirstNumber() {
        return firstNumber;
    }

    /**
     * Returns the second number of the tile.
     * @return the second number of the tile
     */
    public int getSecondNumber() {
        return secondNumber;
    }
}