package domain;

import java.util.Objects;

public class Card {
    int number;
    Symbol symbol;

    public Card(final int number, final Symbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return number == card.number && Objects.equals(symbol, card.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, symbol);
    }
}
