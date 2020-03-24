package domain.card;

import java.util.Objects;

public class Card {
    private static final String TYPE_OR_SYMBOL_NULL_EXCEPTION_MESSAGE = "Type or symbol null exception.";

    private Type type;
    private Symbol symbol;

    public Card(Type type, Symbol symbol) {
        validateNotNull(type, symbol);
        this.type = type;
        this.symbol = symbol;
    }

    private void validateNotNull(Type type, Symbol symbol) {
        if (Objects.isNull(type) || Objects.isNull(symbol)) {
            throw new NullPointerException(TYPE_OR_SYMBOL_NULL_EXCEPTION_MESSAGE);
        }
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
