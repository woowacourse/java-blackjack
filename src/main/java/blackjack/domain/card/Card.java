package blackjack.domain.card;

public class Card {

    private final Shape shape;
    private final Symbol symbol;

    public Card(final Shape shape, final Symbol symbol) {
        this.shape = shape;
        this.symbol = symbol;
    }

    boolean isAce() {
        return symbol.isAce();
    }

    int getScore() {
        return symbol.getScore();
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Shape getShape() {
        return shape;
    }
}
