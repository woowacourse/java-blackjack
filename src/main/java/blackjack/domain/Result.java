package blackjack.domain;

public enum Result {

    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(1),
    LOSE(-1);

    private final double amplification;

    Result(final double amplification) {
        this.amplification = amplification;
    }

    public double getAmplification() {
        return this.amplification;
    }
}
