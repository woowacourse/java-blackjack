package domain.money;

public record Profit(int value) {
    private static final double BLACK_JACK_MULTIPLIER = 1.5;

    public Profit change(GameResult gameResult) {
        return times(gameResult.getBenefitRatio());
    }

    public Profit changeByBlackjack() {
        return times(BLACK_JACK_MULTIPLIER);
    }

    private Profit times(double multiplier) {
        return new Profit((int) (this.value * multiplier));
    }
}
