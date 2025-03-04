package model;

public class Card {

    private final int number;

    private final String shape;
    public Card(final int number, final String shape) {
        this.number = number;
        this.shape = shape;
    }

    public int getNumber() {
        return number;
    }

}
