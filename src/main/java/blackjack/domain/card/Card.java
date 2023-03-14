package blackjack.domain.card;

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
}
