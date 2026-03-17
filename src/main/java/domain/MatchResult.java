package domain;

public enum MatchResult {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0);

    private final double ratio;

    MatchResult(double ratio) {
        this.ratio = ratio;
    }

    public int calculateIncome(BettingMoney bettingMoney) {
        return (int) (this.ratio * bettingMoney.getMoney());
    }
}
