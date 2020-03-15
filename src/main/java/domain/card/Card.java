package domain.card;

public class Card {
    private Type type;
    private Symbol symbol;

    private Card(Type type, Symbol symbol) {
        this.type = type;
        this.symbol = symbol;
    }

    public static Card of(String type, String symbol) {
        return new Card(Type.of(type), Symbol.of(symbol));
    }

    public boolean isAce() {
        return symbol.isAce();
    }

    public int getPoint() {
        return symbol.getPoint();
    }

    public String getCardInfo() {
        return symbol.getAlias() + type.getName();
    }
}
