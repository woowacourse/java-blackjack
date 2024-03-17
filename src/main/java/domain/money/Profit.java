package domain.money;

public record Profit(int value) {
    private static final double BLACK_JACK_MULTIPLIER = 1.5;

    public Profit change(GameResult gameResult) {
        return new Profit(gameResult.timesBenefitRatio(value));
    }

    public Profit changeByBlackjack() {
        return new Profit((int) (this.value * BLACK_JACK_MULTIPLIER));
    }
}
