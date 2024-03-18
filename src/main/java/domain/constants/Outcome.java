package domain.constants;

public enum Outcome {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    TIE(0);

    private final double earningRates;

    Outcome(final double earningRates) {
        this.earningRates = earningRates;
    }

    public double earningRates() {
        return earningRates;
    }
}
