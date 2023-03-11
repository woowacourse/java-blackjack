package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Symbol symbol;
    private final Number number;

    public Card(Symbol symbol, Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public boolean isACE() {
        return this.number == Number.ACE;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Number getNumber() {
        return number;
    }

    public int getNumberValue() {
        return number.getValue();
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
        return symbol == card.symbol && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }
}
