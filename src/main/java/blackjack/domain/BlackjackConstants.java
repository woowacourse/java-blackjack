package blackjack.domain;

public enum BlackjackConstants {
    BURST_THRESHOLD(21);

    private final int symbol;

    BlackjackConstants(int symbol) {
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }
}
