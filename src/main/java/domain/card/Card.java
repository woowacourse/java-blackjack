package domain.card;

public class Card {
    private final Symbol symbol;
    private final Shape shape;

    public Card(Symbol symbol, Shape shape) {
        this.symbol = symbol;
        this.shape = shape;
    }

    @Override
    public String toString() {
        return symbol + shape.toString();
    }

    public int getScore() {
        return symbol.getScore();
    }
}
