package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardNumber number;
    private final CardShape shape;

    public Card(final CardNumber number, final CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isAce() {
        return number.equals(CardNumber.ACE);
    }

    public int getValue() {
        return number.getValue();
    }

    public CardNumber getNumber() {
        return number;
    }

    public CardShape getShape() {
        return shape;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, shape);
    }

    @Override
    public String toString() {
        return "Card{" + number + ", " + shape + "}";
    }
}
