package blackjack.domain.game;

public enum WinOrLose {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String symbol;

    WinOrLose(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
