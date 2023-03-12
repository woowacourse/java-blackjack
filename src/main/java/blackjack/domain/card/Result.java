package blackjack.domain.card;

public enum Result {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    PUSH(0.0),
    LOSE(-1.0);

    private final double ratio;

    Result(final double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}
