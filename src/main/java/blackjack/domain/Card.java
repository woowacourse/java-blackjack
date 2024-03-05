package blackjack.domain;

public class Card {
    
    private final int number;
    private final String symbol;

    public Card(final int number, final String symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public int getNumber() {
        return number;
    }

    public String getSymbol() {
        return symbol;
    }
}
