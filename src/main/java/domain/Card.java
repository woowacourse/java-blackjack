package domain;

public class Card {
    private final Symbol symbol;
    private final Type type;

    Card(Symbol symbol, Type type) {
        this.symbol = symbol;
        this.type = type;
    }

    public boolean isAce() {
        return symbol == Symbol.ACE;
    }

    public int getValue() {
        return symbol.getValue();
    }
}
