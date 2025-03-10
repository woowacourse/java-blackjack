package blackjack.domain;

public enum BlackjackConstants {
    BUST_THRESHOLD(21),
    DEFAULT_CARD_SIZE(2);

    private final int symbol;

    BlackjackConstants(int symbol) {
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }
}
