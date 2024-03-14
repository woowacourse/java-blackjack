package domain.score;

public enum Status {

    WIN(RevenueCalculator.EARN),
    TIE(RevenueCalculator.RETURN),
    LOSE(RevenueCalculator.LOSE),
    BLACKJACK(RevenueCalculator.BLACKJACK_EARN);

    private final RevenueCalculator revenueCalculator;

    Status(RevenueCalculator revenueCalculator) {
        this.revenueCalculator = revenueCalculator;
    }

    public RevenueCalculator getRevenueCalculator() {
        return revenueCalculator;
    }
}
