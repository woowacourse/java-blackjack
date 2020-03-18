package domain.card;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final Type type;

    public Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public boolean isAce() {
        return type.isAce();
    }

    public int getPoint() {
        return type.getPoint();
    }

    public String getSymbol() {
        return symbol.getName();
    }

    public String getType() {
        return type.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Card))
            return false;
        Card card = (Card)o;
        return symbol == card.symbol &&
                type == card.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, type);
    }
}
