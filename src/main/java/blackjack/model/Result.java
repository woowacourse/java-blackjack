package blackjack.model;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String symbol;

    Result(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public Result opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
