package blackjack.domain;

public class Card {

    private final Shape shape;
    private final Symbol symbol;

    public Card(final Shape shape, final Symbol symbol) {
        this.shape = shape;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Shape getShape() {
        return shape;
    }
}
