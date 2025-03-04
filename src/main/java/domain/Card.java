package domain;

public class Card {

    private final Symbol symbol;
    private final Number number;

    public Card(Symbol symbol, Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    public boolean isAce() {
        return number.equals(Number.ACE);
    }
}
