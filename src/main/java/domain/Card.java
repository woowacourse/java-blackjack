package domain;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Number number;

    Card(Suit suit, Number number) {
        this.suit = suit;
        this.number = number;
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
        return suit == card.suit && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, number);
    }
}
