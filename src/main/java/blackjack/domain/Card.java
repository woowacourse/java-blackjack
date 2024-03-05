package blackjack.domain;

public class Card {

    private final int number;
    private final Symbol symbol;

    public Card(final int number, final Symbol symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public int getNumber() {
        return number;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
