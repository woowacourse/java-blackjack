package domain.blackjack;

public enum GameResult {
    LOSE(-1),
    WIN(1),
    TIE(0),
    WIN_BLACK_JACK(1.5);
    private final double earnMoneyRate;

    GameResult(double earnMoneyRate) {
        this.earnMoneyRate = earnMoneyRate;
    }

    public double getEarnMoneyRate() {
        return earnMoneyRate;
    }
}
