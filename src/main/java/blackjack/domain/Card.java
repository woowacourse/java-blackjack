package blackjack.domain;

public class Card {
    private final Denomination denomination;
    private final Symbol symbol;

    private Card(final Denomination denomination, final Symbol symbol) {
        this.denomination = denomination;
        this.symbol = symbol;
    }

    public static Card of(final Denomination denomination, final Symbol symbol) {
        return new Card(denomination, symbol);
    }

    public Denomination getDenomination() {
        return denomination;
    }

    @Override
    public String toString() {
        return denomination.getInitial() + symbol.getSymbolName();
    }
}