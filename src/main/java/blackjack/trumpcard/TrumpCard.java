package blackjack.trumpcard;

public class TrumpCard {
    private final TrumpNumber number;
    private final TrumpSymbol symbol;

    public TrumpCard(TrumpNumber number, TrumpSymbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public int sumNumberTo(int otherNumber) {
        return number.sumTo(otherNumber);
    }

    public boolean hasSameNumber(TrumpNumber trumpNumber) {
        return this.number == trumpNumber;
    }

    @Override
    public String toString() {
        return this.number.getValue() + this.symbol.getValue();
    }
}
