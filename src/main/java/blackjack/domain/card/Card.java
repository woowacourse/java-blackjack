package blackjack.domain.card;

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

    public boolean isSame(final Card card) {
        return this.shape == card.shape && this.number == card.number;
    }

    @Override
    public String toString() {
        return number.getNumber() + shape.getValue();
    }
}
