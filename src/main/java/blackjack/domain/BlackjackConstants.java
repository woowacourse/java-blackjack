package blackjack.domain;

public enum BlackjackConstants {
    BUST_THRESHOLD(21);

    private final int symbol;

    BlackjackConstants(int symbol) {
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }
}
