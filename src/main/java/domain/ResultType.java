package domain;

public enum ResultType {
    BLACKJACK_WIN("블랙잭 승", 1.5),
    WIN("승", 1.0),
    DRAW("무", 0.0),
    LOSE("패", -1.0);

    private final String type;
    private final double profitRate;

    ResultType(String type, double profitRate) {
        this.type = type;
        this.profitRate = profitRate;
    }

    public String getType() {
        return type;
    }

    public int calculateProfit(int bettingAmount) {
        return (int) (bettingAmount * profitRate);
    }
}
