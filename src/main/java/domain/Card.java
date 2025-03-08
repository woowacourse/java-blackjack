package domain;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final Rank number;

    public Card(Symbol symbol, Rank number) {
        this.symbol = symbol;
        this.number = number;
    }

    public boolean isAce() {
        return number.equals(Rank.ACE);
    }

    public Rank getNumber() {
        return number;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && getNumber() == card.getNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, getNumber());
    }
}
