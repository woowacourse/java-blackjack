package vo;

public enum GameResult {
    WIN(1.0),
    LOSE(-1.0),
    BLACKJACK(1.5),
    LOSE_BY_BLACKJACK(-1.5),
    DRAW(0.0);

    private final double profitRate;

    GameResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public Money calculateProfit(Money bettingMoney) {
        return bettingMoney.multiply(this.profitRate);
    }
}
