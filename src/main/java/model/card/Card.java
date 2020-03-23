package model.card;

import java.util.Objects;

public final class Card {
    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public boolean isAce() {
        return symbol == Symbol.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return (symbol == card.symbol) && (type == card.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }

    @Override
    public String toString() {
        return symbol.toString() + type.toString();
    }

    public int calculateScore() {
        return symbol.getScore();
    }
}