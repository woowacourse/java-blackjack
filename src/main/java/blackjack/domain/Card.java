package blackjack.domain;

import java.util.Objects;

public class Card {
    private final CardShape shape;
    private final CardNumber number;

    public Card(final CardShape shape, final CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public CardNumber getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        final Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}
