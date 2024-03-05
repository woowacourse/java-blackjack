package domain;

import java.util.Objects;

public class Card {
    private final Denomination denomination;
    private final Symbol symbol;

    public Card(final Denomination number, final Symbol symbol) {
        this.denomination = number;
        this.symbol = symbol;
    }

    public int getValue(int score){
        return denomination.apply(score);
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return denomination == card.denomination && Objects.equals(symbol, card.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, symbol);
    }
}
