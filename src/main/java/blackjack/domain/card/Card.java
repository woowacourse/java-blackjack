package blackjack.domain.card;

import java.util.Objects;

public record Card(CardNumber number, CardShape shape) {

    public int getValue() {
        return number.getValue();
    }

    public boolean isAce() {
        return number.equals(CardNumber.ACE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, shape);
    }
}
