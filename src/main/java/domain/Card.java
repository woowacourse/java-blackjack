package domain;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final Number number;

    public Card(Symbol symbol, Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public boolean isAce() {
        return number == Number.ACE;
    }

    public Number getNumber() {
        return number;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && getNumber() == card.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, getNumber());
    }
}
