package blackjack.domain.card;

import java.util.Objects;

public class Card {
    private final CardShape shape;
    private final CardNumber number;

    public Card(final CardShape shape, final CardNumber cardNumber) {
        this.shape = shape;
        this.number = cardNumber;
    }

    public CardNumber getNumber() {
        return number;
    }

    public int getCardValue() {
        return number.getValue();
    }

    @Override
    public String toString() {
        return number.getNumber() + shape.getShape();
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