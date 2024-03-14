package domain.money;

public record Money(int value) {
    private static final double BLACK_JACK_MULTIPLIER = 1.5;

    public Money change(GameResult gameResult) {
        return times(gameResult.getBenefitRatio());
    }

    public Money changeByBlackjack() {
        return times(BLACK_JACK_MULTIPLIER);
    }

    private Money times(double multiplier) {
        return new Money((int) (this.value * multiplier));
    }
}
