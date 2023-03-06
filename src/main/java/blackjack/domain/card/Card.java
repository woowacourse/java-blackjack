package blackjack.domain.card;

public class Card {

    private final Shape shape;
    private final Symbol symbol;

    public Card(Shape shape, Symbol symbol) {
        this.shape = shape;
        this.symbol = symbol;
    }

    public boolean isAce() {
        return symbol.equals(Symbol.ACE);
    }

    public Shape getShape() {
        return shape;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getPoint() {
        return symbol.getValue();
    }
}
