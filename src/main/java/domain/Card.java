package domain;

import java.util.Objects;

public class Card {
    private final Symbol symbol;
    private final Number number;

    public Card(final Symbol symbol, final Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }
}
