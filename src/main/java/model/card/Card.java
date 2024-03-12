package model.card;

import java.util.Objects;

public class Card {

    private static final String ACE_NUMBER = "A";

    private final CardNumber number;
    private final CardShape shape;

    public Card(CardNumber number, CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isAce() {
        return Objects.equals(number.getNumber(), ACE_NUMBER);
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardShape getShape() {
        return shape;
    }
}
