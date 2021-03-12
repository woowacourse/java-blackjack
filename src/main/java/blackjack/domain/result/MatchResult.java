package blackjack.domain.result;

public enum MatchResult {
    STAY(1), BLACKJACK(1.5), BUST(-1);

    private final double profitRate;

    MatchResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
