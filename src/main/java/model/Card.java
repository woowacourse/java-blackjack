package model;

public class Card {

    private final CardNumber number;
    private final String shape;

    public Card(CardNumber number, String shape) {
        this.number = number;
        this.shape = shape;
    }

    public CardNumber getNumber() {
        return number;
    }
}
