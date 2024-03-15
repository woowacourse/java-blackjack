package domain.score;

public enum Outcome {

    WIN(RevenueCalculator.EARN),
    TIE(RevenueCalculator.RETURN),
    LOSE(RevenueCalculator.LOSE),
    BLACKJACK(RevenueCalculator.BLACKJACK_EARN);

    private final RevenueCalculator revenueCalculator;

    Outcome(RevenueCalculator revenueCalculator) {
        this.revenueCalculator = revenueCalculator;
    }

    public RevenueCalculator getRevenueCalculator() {
        return revenueCalculator;
    }
}
