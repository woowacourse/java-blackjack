package blackjack.domain.player;

public enum BetPoint {

    WIN(1.0),
    LOSE(-1.0),
    BLACKJACK(1.5),
    BOTH_BLACKJACK(0.0);

    private final double ratio;

    BetPoint(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}
