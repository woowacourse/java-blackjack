package domain.game;

public enum Outcome {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    LOSE(-1.0),
    TIE(0.0);

    private final double profitRate;

    Outcome(double profitRate) {
        this.profitRate = profitRate;
    }

    public int calculateProfit(int amount) {
        return (int) (amount * profitRate);
    }

}
