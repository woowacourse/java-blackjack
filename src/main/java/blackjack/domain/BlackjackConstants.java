package blackjack.domain;

public enum BlackjackConstants {
    MAX_SCORE(21);

    private final int symbol;

    BlackjackConstants(int symbol) {
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }
}
