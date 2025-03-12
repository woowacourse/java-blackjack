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

    public static GameResult reverse(GameResult gameResult) {
        if (gameResult == GameResult.WIN || gameResult == GameResult.BLACKJACK_WIN) {
            return GameResult.LOSE;
        }
        if (gameResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public int calculatePayout(BettingMoney bettingMoney) {
        return (int)(bettingMoney.intValue() * payoutRate);
    }

}
