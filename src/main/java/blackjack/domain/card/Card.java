package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final Symbol symbol;

    public Card(Shape shape, Symbol symbol) {
        this.shape = shape;
        this.symbol = symbol;
    }

    public boolean isAce() {
        return symbol == Symbol.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && symbol == card.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, symbol);
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
