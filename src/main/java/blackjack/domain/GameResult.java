package blackjack.domain;

public enum GameResult {
    BLACKJACK_WIN(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double payoutRate;

    GameResult(double payoutRate) {
        this.payoutRate = payoutRate;
    }

    public int calculatePayout(BettingMoney bettingMoney) {
        return (int)(bettingMoney.intValue() * payoutRate);
    }

}
