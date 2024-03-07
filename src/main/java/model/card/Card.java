package model.card;

import java.util.Objects;

public class Card {
    private final CardShape shape;
    private final CardNumber number;

    public Card(CardShape shape, CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public int minimumNumber() {
        return number.minimumNumber();
    }

    public int subtractMaxMinNumber() {
        return number.maximumNumber() - number.maximumNumber();
    }

    public CardShape getShape() {
        return shape;
    }

    public CardNumber getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}
