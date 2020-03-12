package blackjack.domain.card;

public class Card {

    private final Type type;
    private final Symbol symbol;

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

    public String getInfo() {
        return symbol.getName() + type.getName();
    }
}
