package model.card;

public class Card {

    private final CardNumber number;
    private final CardShape shape;

    public Card(CardNumber number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardShape getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return number.getDisplayName() + shape.getDisplayName();
    }
}
