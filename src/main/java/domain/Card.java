package domain;

import java.util.Objects;

public class Card {
    private final Denomination number;
    private final Symbol symbol;

    public Card(final Denomination number, final Symbol symbol) {
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
