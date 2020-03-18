package domain.card;

import static domain.util.NullValidator.validateNull;

public class Card {
    private final Type type;
    private final Symbol symbol;

    /* Package private */
    Card(Type type, Symbol symbol) {
        validateNull(type, symbol);
        this.type = type;
        this.symbol = symbol;
    }

    public boolean isSameWith(Type type, Symbol symbol) {
        return this.type == type && this.symbol == symbol;
    }

    public boolean isAce() {
        return symbol == Symbol.ACE;
    }

    public int getValue() {
        return symbol.getScore();
    }

    public String getName() {
        return symbol.getName() + type.getType();
    }
}
