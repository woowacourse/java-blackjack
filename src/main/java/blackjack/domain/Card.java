package blackjack.domain;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final Shape shape;

    public Card(Symbol symbol, Shape shape) {
        this.symbol = symbol;
        this.shape = shape;
    }

    public boolean isAce() {
        return symbol == Symbol.ACE;
    }

    public int getScore(int sum) {
        return symbol.calculateScore(sum);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Shape getShape() {
        return shape;
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
        return symbol == card.symbol && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, shape);
    }

    @Override
    public String toString() {
        return "Card{" +
                "symbol=" + symbol +
                ", shape=" + shape +
                '}';
    }
}
