package domain.game;

public enum Winning {
    BLACKJACK(1.5),
    LOSE(-1),
    PUSH(0),
    WIN(1);

    private final double profitRate;

    Winning(double profitRate) {
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
