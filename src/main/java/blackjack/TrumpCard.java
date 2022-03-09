package blackjack;

import java.util.Objects;

public class TrumpCard {
    private final TrumpSymbol symbol;
    private final TrumpNumber number;

    public TrumpCard(TrumpSymbol symbol, TrumpNumber number) {
        this.symbol = symbol;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrumpCard trumpCard = (TrumpCard) o;
        return symbol == trumpCard.symbol && number == trumpCard.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }
}
