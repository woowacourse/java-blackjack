package blackjackgame.domain;

public enum GameOutcome implements RevenueCalculator {
    BLACKJACK_WIN("승", (money -> (int) (money * 1.5))),
    WIN("승", money -> money),
    LOSE("패", money -> money * -1),
    DRAW("무", money -> 0);

    private final String outcome;
    private final RevenueCalculator revenueCalculator;

    GameOutcome(final String outcome, RevenueCalculator revenueCalculator) {
        this.outcome = outcome;
        this.revenueCalculator = revenueCalculator;
    }

    @Override
    public int calculateRevenue(int money) {
        return revenueCalculator.calculateRevenue(money);
    }

    public String getOutcome() {
        return outcome;
    }
}
