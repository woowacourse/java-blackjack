package blackjack.domain.result;

public enum HandResult {
    BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);

    private final Double ratio;

    HandResult(Double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return this.ratio;
    }
}
