package domain;

import java.util.Objects;

public class Card {
    private final int number;
    private final CardType type;

    public Card(final int number, final CardType type) {
        this.number = number;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return number == card.number && type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type);
    }
}
