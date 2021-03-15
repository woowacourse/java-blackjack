package blackjack.domain.result;

public enum HandResult {
    STAY(1), BLACKJACK(1.5), BUST(-1);

    private final double profitRate;

    HandResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
