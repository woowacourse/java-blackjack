package domain.card;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final domain.card.Number number;

    public Card(Symbol symbol, domain.card.Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public boolean isAce() {
        return number.equals(domain.card.Number.ACE);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return symbol == card.symbol && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }
}
