package model.card;

public class Card {

    private final CardNumber number;
    private final CardShape shape;

    public Card(CardNumber number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isAce() {
        return number == CardNumber.ACE;
    }

    public int getScore() {
        return number.getScore();
    }

    @Override
    public String toString() {
        return number.getDisplayName() + shape.getDisplayName();
    }
}
