package blackjack.domain;

public enum GameResultStatus {

    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);

    private final double profit;

    GameResultStatus(double profit) {
        this.profit = profit;
    }

    public int calculateProfit(int amount) {
        return (int) (amount * profit);
    }
}
