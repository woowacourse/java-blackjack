package domain.game;

public enum Outcome {
    BLACKJACK(1.5), WIN(1), DRAW(0), LOSE(-1);

    private final double profitRate;

    Outcome(final double profitRate) {
        this.profitRate = profitRate;
    }

    public double profitRate() {
        return profitRate;
    }
}
