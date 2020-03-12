package domain.deck;

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

    public String getName() {
        return type.getName() + symbol.getName();
    }
}
