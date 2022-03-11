package blackjack.model.trumpcard;

import java.util.Objects;

public class TrumpCard {
    private final TrumpNumber number;
    private final TrumpSymbol symbol;

    public TrumpCard(TrumpNumber number, TrumpSymbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public int sumNumberTo(int value) {
        return number.sumTo(value);
    }

    public boolean isNumber(TrumpNumber trumpNumber) {
        return this.number == trumpNumber;
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
        return number == trumpCard.number && symbol == trumpCard.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, symbol);
    }

    @Override
    public String toString() {
        return this.number.toString() + this.symbol.getValue();
    }
}
