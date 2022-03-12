package blackjack.model.card;

public class Card {
    private final TrumpNumber number;
    private final TrumpSymbol symbol;

    public Card(TrumpNumber number, TrumpSymbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public TrumpNumber getNumber() {
        return number;
    }

    public TrumpSymbol getSymbol() {
        return symbol;
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
