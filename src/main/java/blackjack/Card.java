package blackjack;

public class Card {

    private final CardSymbol symbol;
    private final CardValue value;

    private Card(final CardSymbol symbol, final CardValue value) {
        this.symbol = symbol;
        this.value = value;
    }

    public static Card create(final CardSymbol symbol, final CardValue value) {
        return new Card(symbol, value);
    }
}