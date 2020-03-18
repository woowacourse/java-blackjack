package domain;

import java.util.Objects;

public class Card {
    private Type type;
    private Symbol symbol;

    public Card(Type type, Symbol symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public boolean isAce() {
        return this.symbol == Symbol.ACE;
    }

    public int getValue() {
        return symbol.getValue();
    }

    @Override
    public String toString() {
        return symbol.getSymbol() + type.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type &&
                symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, symbol);
    }
}
