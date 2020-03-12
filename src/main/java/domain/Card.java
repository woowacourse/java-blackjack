package domain;

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
        return symbol.getName() + type.getName();
    }
}
