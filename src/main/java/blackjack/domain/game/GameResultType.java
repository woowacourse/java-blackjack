package blackjack.domain.game;

public enum GameResultType {

    WIN_WITH_INITIAL_HAND_BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double profitRate;

    GameResultType(double profitRate) {
        this.profitRate = profitRate;
    }

    public static GameResultType parse(int playerPoint, int dealerPoint) {
        playerPoint = GameRule.applyBustToPoint(playerPoint);
        dealerPoint = GameRule.applyBustToPoint(dealerPoint);

        if (playerPoint > dealerPoint) {
            return GameResultType.WIN;
        }
        if (playerPoint < dealerPoint) {
            return GameResultType.LOSE;
        }
        return GameResultType.DRAW;
    }

    public int calculateProfit(int amount) {
        return (int) (amount * profitRate);
    }

    public double getProfitRate() {
        return profitRate;
    }
}
