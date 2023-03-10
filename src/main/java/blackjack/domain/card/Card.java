package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardShape shape;
    private final CardNumber number;

    public Card(final CardShape shape, final CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public CardShape getShape() {
        return shape;
    }

    public CardNumber getNumber() {
        return number;
    }

    public boolean isAce() {
        return number.equals(CardNumber.ACE);
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
