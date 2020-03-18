package domain.result;

public enum PrizeRatio {

    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private final double ratio;

    PrizeRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}
