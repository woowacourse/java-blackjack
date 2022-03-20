package blackjack.domain.card;

import java.util.Objects;

public class Card1 {
    private final Symbol symbol;
    private final Number number;

    private Card1(Symbol symbol, Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static Card1 of (Symbol symbol, Number number) {
        return new Card1(symbol, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card1)) {
            return false;
        }
        Card1 card1 = (Card1) o;
        return symbol == card1.symbol && number == card1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }


    public Symbol getSymbol() {
        return symbol;
    }

    public Number getNumber() {
        return number;
    }
}
