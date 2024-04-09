package org.example;

public class Tile {
    private final int firstNumber;
    private final int secondNumber;

    public Tile(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }


    @Override
    public String toString() {
        return "(" + firstNumber + ", " + secondNumber + ")";
    }
}
