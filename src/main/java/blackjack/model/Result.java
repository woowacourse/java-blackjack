package blackjack.model;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSS("패");

    private final String symbol;

    Result(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}
