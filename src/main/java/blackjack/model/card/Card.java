package blackjack.model.card;

public class Card {
    private final TrumpNumber number;
    private final TrumpSymbol symbol;

    public Card(TrumpNumber number, TrumpSymbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public int getNumber() {
        return number.getValue();
    }

    public boolean hasSameNumber(TrumpNumber trumpNumber) {
        return this.number == trumpNumber;
    }

    public String getNumberOfString() {
        return number.getValueOfString();
    }

    public String getSymbol() {
        return symbol.getValue();
    }

    public int sumNumberTo(int otherNumber) {
        return number.sumTo(otherNumber);
    }


    @Override
    public String toString() {
        return this.number.getValue() + this.symbol.getValue();
    }
}
